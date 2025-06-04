# Use a base image with Java (JRE only or JDK)
FROM eclipse-temurin:21-jdk-alpine

VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose port
EXPOSE 8080

LABEL authors="Phil Fernandez"

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]