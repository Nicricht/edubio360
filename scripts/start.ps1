$ErrorActionPreference = "Stop"
if (-not (Test-Path ".env")) {
    Copy-Item ".env.example" ".env"
    Write-Host "Se creó .env desde .env.example. Cambia JWT_SECRET antes de usar un entorno compartido."
}
docker compose up --build
