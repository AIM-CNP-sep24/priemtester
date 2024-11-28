# Use the official OpenJDK image as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built application and frontend
COPY target/*.jar ./app.jar
COPY priemtester-ui/dist ./src/main/resources/static

# Expose the port the app runs on
EXPOSE 8090

# Run the application
CMD ["java", "-jar", "app.jar"]