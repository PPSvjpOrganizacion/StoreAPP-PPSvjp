# ===== Etapa 1: Build con Maven + JDK 11 =====
FROM maven:3.8.7-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Etapa 2: Runtime con JRE 11 =====
FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/target/classes ./target/classes
COPY --from=build /app/src ./src
COPY --from=build /app/pom.xml ./pom.xml
# Copia Maven ligero para sql-maven-plugin (usa el de build)
COPY --from=build /usr/share/maven /usr/share/maven
ENV PATH="/usr/share/maven/bin:${PATH}"

EXPOSE 8888
VOLUME ["/app/work", "/app/work/logs"]

# ENTRYPOINT: crea BD con sql-maven-plugin (usa src/main/resources/*.sql del pom)
ENTRYPOINT ["sh", "-c", "mvn sql:execute -Dsql.format=false && java -jar app.jar"]
