@echo off
mvn clean test -Pchrome
if %ERRORLEVEL% EQU 0 (
    mvn allure:serve
)
