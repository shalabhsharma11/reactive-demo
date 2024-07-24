# Stage 1: Build stage

FROM maven:3.9.8-eclipse-temurin-22 AS BUILD

WORKDIR /opt/app
COPY src ./src
COPY pom.xml ./

RUN mvn clean package

# Stage 2: Runtime stage
FROM openjdk:22-slim
WORKDIR /opt/app
COPY --from=BUILD /opt/app/target/*.jar app.jar
WORKDIR /opt/app

ENTRYPOINT ["java", "-jar", "app.jar"]


# Stage 1: Build stage
#FROM maven:3.9.8-eclipse-temurin-22-alpine AS build
#WORKDIR /app
#COPY pom.xml ./
#COPY src ./src
#RUN mvn clean package
#COPY . /opt/app
#WORKDIR /opt/app
#RUN mvn clean package

# Stage 2: Runtime stage
#FROM openjdk:22-slim
#WORKDIR /app
#COPY --from=build /app/target/*.jar app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]