FROM kalibrate/automation-env as build-env

# copy over source code for selenium tests
WORKDIR /framework
COPY . .
# update kalibrate test runner cucumber tags
COPY docker/tagreplace.ps1 tagreplace.ps1
RUN powershell -File tagreplace.ps1
RUN mvn compile
RUN mvn test-compile
# copy configuration file
ADD docker/*.properties ./

FROM kalibrate/automation-env
WORKDIR /app
COPY --from=build-env /framework .
ENTRYPOINT ["mvn","-Dtest=**/Kalibrate_Runner_Test", "test"]

