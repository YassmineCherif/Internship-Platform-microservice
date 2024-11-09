# Use an OpenJDK base image
FROM openjdk:17
# Set the working directory
WORKDIR /app
EXPOSE 8085
ADD target/MSGestionJournal-0.0.1-SNAPSHOT.jar msgestionjournal.jar
ENTRYPOINT ["java", "-jar", "msgestionjournal.jar"]