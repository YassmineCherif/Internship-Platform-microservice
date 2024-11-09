# Use an OpenJDK base image
FROM openjdk:17
# Set the working directory
WORKDIR /app
EXPOSE 8087
ADD target/SujetPostulation-0.0.1-SNAPSHOT.jar sujetpostulation.jar
ENTRYPOINT ["java", "-jar", "sujetpostulation.jar"]
