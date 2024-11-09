# Use an OpenJDK base image
FROM openjdk:17
# Set the working directory
WORKDIR /app
EXPOSE 8081
ADD target/Gateway-0.0.1-SNAPSHOT.jar gateway.jar
ENTRYPOINT ["java","-jar","gateway.jar"]