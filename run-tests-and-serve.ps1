mvn clean test -Pchrome
if ($LASTEXITCODE -eq 0) {
    mvn allure:serve
}
