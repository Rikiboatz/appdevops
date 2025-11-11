# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
# คัดลอกไฟล์ pom และ preload dependencies ก่อน เพื่อ cache dependency
COPY pom.xml .
RUN mvn -q -B dependency:go-offline
# copy source แล้ว build
COPY src ./src
RUN mvn -q -e -DskipTests package


# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/appdevops-1.0.0.jar app.jar
# ใช้ prod profile ตอนรันจริง; ปรับได้ตาม infra ของคุณ
# ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]