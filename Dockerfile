FROM maven:3-eclipse-temurin-17 AS build

COPY ["MiniBank", "/app/MiniBank"]

COPY MiniBank/pom.xml /app/MiniBank

WORKDIR /app/MiniBank

RUN mvn clean install -DskipTests

FROM openjdk:17-oracle

COPY --from=build /app/MiniBank/target/pitang-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app/MiniBank

EXPOSE 8081

CMD ["java", "-jar", "/app/app.jar"]