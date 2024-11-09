# Use an OpenJDK base image
FROM openjdk:17
# Set the working directory
WORKDIR /app
EXPOSE 9090
ADD target/MicroServiceReclamation-0.0.1-SNAPSHOT.jar reclamation.jar
ENTRYPOINT ["java","-jar","reclamation.jar"]
