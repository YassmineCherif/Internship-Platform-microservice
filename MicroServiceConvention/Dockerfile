# Use an OpenJDK base image
FROM openjdk:17
# Set the working directory
WORKDIR /app
EXPOSE 8083
ADD target/MicroServiceConvention-0.0.1-SNAPSHOT.jar microserviceconvention.jar
ENTRYPOINT ["java", "-jar", "microserviceconvention.jar"]
