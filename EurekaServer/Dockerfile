# Use an OpenJDK base image
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the JAR file from your target directory into the container
COPY target/EurekaServer-0.0.1-SNAPSHOT.jar eurekaserver.jar

# Expose the port that Eureka runs on
EXPOSE 8761

# Run the JAR file
ENTRYPOINT ["java", "-jar", "eurekaserver.jar"]
