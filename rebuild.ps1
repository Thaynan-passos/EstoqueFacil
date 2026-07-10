# rebuild.ps1
$ErrorActionPreference = "Stop"

function Step($msg) {
    Write-Host "`n=== $msg ===" -ForegroundColor Cyan
}

try {
    Step "1/5 - Build do JAR (mvnw clean package)"
    ./mvnw clean package -DskipTests
    if ($LASTEXITCODE -ne 0) { throw "Falha no build do Maven" }

    Step "2/5 - Derrubando containers e volumes (docker compose down -v)"
    docker compose down -v
    if ($LASTEXITCODE -ne 0) { throw "Falha no docker compose down" }

    Step "3/5 - Rebuild da imagem sem cache (docker compose build --no-cache)"
    docker compose build --no-cache
    if ($LASTEXITCODE -ne 0) { throw "Falha no docker compose build" }

    Step "4/5 - Subindo containers (docker compose up -d)"
    docker compose up -d
    if ($LASTEXITCODE -ne 0) { throw "Falha no docker compose up" }

    Step "5/5 - Logs do app (Ctrl+C para sair)"
    docker compose logs -f app
}
catch {
    Write-Host "`nERRO: $_" -ForegroundColor Red
    exit 1
}