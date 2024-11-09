# Use an OpenJDK base image
FROM openjdk:17
# Set the working directory
WORKDIR /app
EXPOSE 8084
ADD target/MicroServiceForum-0.0.1-SNAPSHOT.jar microserviceforum.jar
ENTRYPOINT ["java","-jar","microserviceforum.jar"]