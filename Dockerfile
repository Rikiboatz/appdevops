# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace
# คัดลอกไฟล์ pom และ preload dependencies ก่อน เพื่อ cache dependency
COPY pom.xml .
RUN mvn -q -B dependency:go-offline
# copy source แล้ว build
COPY src ./src
RUN mvn -q -e -DskipTests package


# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/target/appdevops-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]