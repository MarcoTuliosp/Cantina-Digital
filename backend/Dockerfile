# ═══════════════════════════════════
# Build Stage
# ═══════════════════════════════════
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .mvn ./.mvn
COPY mvnw .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# ═══════════════════════════════════
# Runtime Stage
# ═══════════════════════════════════
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Porta dinâmica (Render define via $PORT)
EXPOSE 8080

ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]
