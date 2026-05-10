# ═══════════════════════════════════
# Build Stage
# ═══════════════════════════════════
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ═══════════════════════════════════
# Runtime Stage
# ═══════════════════════════════════
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Porta dinâmica (Render define via $PORT)
EXPOSE 8080

ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]
