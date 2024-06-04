FROM maven:3.9-sapmachine-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests




FROM openjdk:20-jdk-oracle

COPY --from=build /app/out/artifacts/TFG_Fontanet_jar/TFG-Fontanet.jar /TFG-Fontanet.jar
ENTRYPOINT ["java","-jar","/TFG-Fontanet.jar"]