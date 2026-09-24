FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY . /workspace
RUN mvn -pl backend/banking-operations-service -am package -DskipTests
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/backend/banking-operations-service/target/banking-operations-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]