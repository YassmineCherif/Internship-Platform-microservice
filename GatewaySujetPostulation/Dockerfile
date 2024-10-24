
FROM openjdk:17-jdk-alpine

EXPOSE 8093

ADD target/GatewaySujetPostulation-0.0.1-SNAPSHOT.jar sujetgateway.jar

ENTRYPOINT ["java", "-jar", "sujetgateway.jar"]