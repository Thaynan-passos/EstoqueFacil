# Run application: if a packaged JAR exists use it, otherwise use the Maven Wrapper to run the app
$target = Join-Path $PSScriptRoot "target"
if (-Not (Test-Path $target)) { New-Item -ItemType Directory -Path $target | Out-Null }
$jar = Get-ChildItem -Path $target -Filter *.jar -File -ErrorAction SilentlyContinue | Sort-Object LastWriteTime -Descending | Select-Object -First 1
if ($jar) {
    Write-Host "Found JAR: $($jar.Name) - starting with java -jar"
    & java -jar $jar.FullName
} else {
    Write-Host "No JAR found. Starting via Maven Wrapper (spring-boot:run)."
    if (Test-Path "${PSScriptRoot}\mvnw.cmd") {
        & "${PSScriptRoot}\mvnw.cmd" spring-boot:run
    } else {
        Write-Error "mvnw.cmd not found in project root. Run 'mvn package' or create the Maven Wrapper."
        exit 1
    }
}
