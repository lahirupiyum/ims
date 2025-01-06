# Use a base image with Java
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the target directory
COPY target/ims.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]