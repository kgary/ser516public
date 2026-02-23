FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B package

FROM eclipse-temurin:17-jre

COPY --from=build /workspace/target/docker-jenkins-demo-1.0.0.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
