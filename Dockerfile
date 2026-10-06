# ---- Build stage ----
FROM gradle:8.10-jdk17 AS build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .
RUN gradle installDist --no-daemon

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre
RUN useradd --system --uid 10001 app
COPY --from=build /home/gradle/src/build/install/java-gradle-app /opt/app
USER app
EXPOSE 8080
ENTRYPOINT ["/opt/app/bin/java-gradle-app"]
