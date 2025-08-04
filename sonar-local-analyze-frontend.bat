@echo off
cd front
REM npm test -- --no-watch --no-progress --browsers=ChromeHeadless --code-coverage
npm run test-coverage
sonar-scanner "-Dsonar.host.url=http://localhost:9000" -Dsonar.token=%SONAR_TOKEN_LOCAL_FRONTEND% -Dsonar.projectKey=cpierres_P10cicd-frontend