@echo off
REM call set-env.bat
cd back
mvn clean verify sonar:sonar "-Dsonar.projectKey=cpierres_P10cicd-backend" "-Dsonar.projectName=P10cicd-backend" "-Dsonar.host.url=http://localhost:9000" -Dsonar.token=%SONAR_TOKEN_LOCAL_BACKEND%