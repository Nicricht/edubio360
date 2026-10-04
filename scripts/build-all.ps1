$ErrorActionPreference = "Stop"
mvn -B clean package -DskipTests
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
