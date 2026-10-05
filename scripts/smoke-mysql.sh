#!/usr/bin/env bash
set -euo pipefail

mkdir -p artifacts

cleanup() {
  docker compose logs --no-color > artifacts/docker-compose.log 2>&1 || true
  docker compose down -v >/dev/null 2>&1 || true
}
trap cleanup EXIT

echo "[EP02] Construyendo y levantando MySQL + microservicios..."
docker compose up -d --build   mysql rabbitmq discovery-server   auth-service academic-service guidance-service   notification-service analytics-service import-service api-gateway

wait_for() {
  local name="$1"
  local url="$2"
  local max_attempts="${3:-90}"
  for i in $(seq 1 "$max_attempts"); do
    if curl -fsS "$url" >/dev/null 2>&1; then
      echo "[OK] $name"
      return 0
    fi
    sleep 2
  done
  echo "[FAIL] $name no respondió en $url" >&2
  docker compose ps >&2 || true
  return 1
}

wait_for "Auth" "http://localhost:8081/actuator/health"
wait_for "Academic" "http://localhost:8082/actuator/health"
wait_for "Guidance" "http://localhost:8083/actuator/health"
wait_for "Notification" "http://localhost:8084/actuator/health"
wait_for "Analytics" "http://localhost:8085/actuator/health"
wait_for "Import" "http://localhost:8086/actuator/health"
wait_for "Gateway" "http://localhost:8080/actuator/health"

echo "[EP02] Verificando bases MySQL..."
for db in authdb academicdb guidancedb notificationdb analyticsdb importdb; do
  docker compose exec -T mysql mysql -uroot -proot -Nse     "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME='${db}'"     | grep -qx "$db"
  echo "[OK] $db"
done

post_json() {
  local url="$1"
  local payload="$2"
  shift 2
  curl -fsS -X POST "$url" -H "Content-Type: application/json" "$@" -d "$payload"
}

put_json() {
  local url="$1"
  local payload="$2"
  shift 2
  curl -fsS -X PUT "$url" -H "Content-Type: application/json" "$@" -d "$payload"
}

assert_status() {
  local expected="$1"
  shift
  local actual
  actual="$(curl -sS -o /tmp/ep02-response.json -w "%{http_code}" "$@")"
  if [ "$actual" != "$expected" ]; then
    echo "[FAIL] HTTP esperado=$expected actual=$actual" >&2
    cat /tmp/ep02-response.json >&2 || true
    return 1
  fi
}

echo "[EP02] Gateway + JWT..."
gateway_token="$(curl -fsS -X POST "http://localhost:8080/api/auth/login"   -H "Content-Type: application/json"   -d '{"email":"student@edubio.local","password":"Student123!"}' | jq -r '.token')"
test "$gateway_token" != "null"
test -n "$gateway_token"
assert_status 401 "http://localhost:8080/api/ofertas"
assert_status 200 "http://localhost:8080/api/ofertas" -H "Authorization: Bearer $gateway_token"

echo "[EP02] CRUD Auth..."
auth_created="$(post_json "http://localhost:8081/api/usuarios"   '{"email":"ep02-ci@example.test","password":"sample-value-123","role":"STUDENT","active":true}')"
auth_id="$(echo "$auth_created" | jq -r '.id')"
test "$auth_id" != "null"
put_json "http://localhost:8081/api/usuarios/$auth_id"   '{"email":"ep02-ci-updated@example.test","password":"sample-value-456","role":"ORIENTADOR","active":true}' >/dev/null
assert_status 404 "http://localhost:8081/api/usuarios/999999"
assert_status 400 -X POST "http://localhost:8081/api/usuarios" -H "Content-Type: application/json" -d '{}'
assert_status 204 -X DELETE "http://localhost:8081/api/usuarios/$auth_id"

echo "[EP02] CRUD Academic..."
academic_created="$(post_json "http://localhost:8082/api/ofertas"   '{"carrera":"Ingeniería de Datos CI","modalidad":"Presencial","jornada":"Diurna","arancel":3100000,"matricula":175000,"sedeId":1}')"
academic_id="$(echo "$academic_created" | jq -r '.id')"
test "$academic_id" != "null"
put_json "http://localhost:8082/api/ofertas/$academic_id"   '{"carrera":"Ingeniería de Datos CI","modalidad":"Presencial","jornada":"Vespertina","arancel":3200000,"matricula":180000,"sedeId":1}' >/dev/null
assert_status 404 "http://localhost:8082/api/ofertas/999999"
assert_status 400 -X POST "http://localhost:8082/api/ofertas" -H "Content-Type: application/json" -d '{}'

echo "[EP02] CRUD Guidance..."
future_date="$(date -u -d '+2 day' '+%Y-%m-%dT%H:%M:%S')"
guidance_created="$(post_json "http://localhost:8083/api/solicitudes"   "{\"ofertaId\":1,\"motivo\":\"Orientación CI\",\"fechaHora\":\"$future_date\"}"   -H "X-User-Email: student@example.test" -H "X-User-Role: STUDENT")"
guidance_id="$(echo "$guidance_created" | jq -r '.id')"
test "$guidance_id" != "null"
put_json "http://localhost:8083/api/solicitudes/$guidance_id"   "{\"motivo\":\"Orientación CI actualizada\",\"fechaHora\":\"$future_date\"}"   -H "X-User-Email: student@example.test" -H "X-User-Role: STUDENT" >/dev/null
assert_status 404 "http://localhost:8083/api/solicitudes/999999"
assert_status 400 -X POST "http://localhost:8083/api/solicitudes"   -H "Content-Type: application/json" -H "X-User-Email: student@example.test" -H "X-User-Role: STUDENT" -d '{}'

echo "[EP02] CRUD Notification..."
notification_created="$(post_json "http://localhost:8084/api/notificaciones"   '{"tipo":"ORIENTACION","destinatario":"student@example.test","mensaje":"Notificación CI"}')"
notification_id="$(echo "$notification_created" | jq -r '.id')"
test "$notification_id" != "null"
put_json "http://localhost:8084/api/notificaciones/$notification_id"   '{"tipo":"ORIENTACION","destinatario":"student@example.test","mensaje":"Notificación CI actualizada"}' >/dev/null
assert_status 404 "http://localhost:8084/api/notificaciones/999999"
assert_status 400 -X POST "http://localhost:8084/api/notificaciones" -H "Content-Type: application/json" -d '{}'
assert_status 204 -X DELETE "http://localhost:8084/api/notificaciones/$notification_id"

echo "[EP02] CRUD Analytics..."
analytics_created="$(post_json "http://localhost:8085/api/metricas"   '{"nombre":"arancel_promedio_ci","descripcion":"Métrica CI","puntos":[{"etiqueta":"Tecnología","valor":3200000}]}')"
analytics_id="$(echo "$analytics_created" | jq -r '.id')"
test "$analytics_id" != "null"
put_json "http://localhost:8085/api/metricas/$analytics_id"   '{"nombre":"arancel_mediana_ci","descripcion":"Métrica CI actualizada","puntos":[{"etiqueta":"Tecnología","valor":3150000}]}' >/dev/null
assert_status 404 "http://localhost:8085/api/metricas/999999"
assert_status 400 -X POST "http://localhost:8085/api/metricas" -H "Content-Type: application/json" -d '{}'
assert_status 204 -X DELETE "http://localhost:8085/api/metricas/$analytics_id"

echo "[EP02] CRUD Import..."
import_created="$(post_json "http://localhost:8086/api/importaciones"   '{"archivo":"matriculas-ci.csv","estado":"VALIDADO","errores":[]}')"
import_id="$(echo "$import_created" | jq -r '.id')"
test "$import_id" != "null"
put_json "http://localhost:8086/api/importaciones/$import_id"   '{"archivo":"matriculas-ci.csv","estado":"PROCESADO","errores":[{"fila":2,"mensaje":"Fila controlada"}]}' >/dev/null
assert_status 404 "http://localhost:8086/api/importaciones/999999"
assert_status 400 -X POST "http://localhost:8086/api/importaciones" -H "Content-Type: application/json" -d '{}'
assert_status 204 -X DELETE "http://localhost:8086/api/importaciones/$import_id"

echo "[EP02] Verificando tablas JPA en MySQL..."
for spec in   "authdb:users"   "academicdb:ofertas_academicas"   "guidancedb:solicitudes_orientacion"   "notificationdb:notificaciones"   "analyticsdb:metricas"   "importdb:importaciones"; do
  db="${spec%%:*}"
  table="${spec##*:}"
  docker compose exec -T mysql mysql -uroot -proot -Nse     "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='${db}' AND TABLE_NAME='${table}'"     | grep -qx "$table"
  echo "[OK] $db.$table"
done

assert_status 204 -X DELETE "http://localhost:8082/api/ofertas/$academic_id"
assert_status 204 -X DELETE "http://localhost:8083/api/solicitudes/$guidance_id"   -H "X-User-Email: student@example.test" -H "X-User-Role: STUDENT"

echo "[SUCCESS] Smoke MySQL EP02 completado."
