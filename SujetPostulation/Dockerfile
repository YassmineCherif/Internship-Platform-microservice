FROM openjdk:17-jdk-alpine

EXPOSE 8082

ADD target/SujetPostulation-0.0.1-SNAPSHOT.jar sujetpostulation.jar

ENTRYPOINT ["java", "-jar", "sujetpostulation.jar"]
