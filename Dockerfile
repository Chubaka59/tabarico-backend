# --------------------
# Étape 1 : Build Maven
# --------------------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# --------------------
# Étape 2 : JRE léger pour exécuter le jar
# --------------------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Créer le répertoire d’upload
RUN mkdir -p /app/uploads/images/identityCards
VOLUME ["/app/uploads/images/identityCards"]

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
