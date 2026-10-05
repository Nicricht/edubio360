$ErrorActionPreference = "Stop"
mvn -B clean verify
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
