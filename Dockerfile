FROM eclipse-temurin:11-jre

WORKDIR /app

# Copia el JAR generado por Jenkins
COPY target/*.jar app.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
