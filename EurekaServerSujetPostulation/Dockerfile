FROM openjdk:17-jdk-alpine

EXPOSE 8761

ADD target/EurekaServerSujetPostulation-0.0.1-SNAPSHOT.jar sujeteureka.jar

ENTRYPOINT ["java", "-jar", "sujeteureka.jar"]
