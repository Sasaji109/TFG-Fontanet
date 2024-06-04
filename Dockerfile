FROM maven:3.9-sapmachine-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests




FROM openjdk:20-jdk-oracle

COPY --from=build /app/target/TFG-Fontanet-0.0.1-SNAPSHOT.jar /TFG-Fontanet-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/TFG-Fontanet-0.0.1-SNAPSHOT.jar"]