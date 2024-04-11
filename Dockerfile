# Base image
FROM amazoncorretto:17-alpine

# Set working directory
WORKDIR /app

# Copy JAR file to the container
COPY build/libs/ /app/

# Define entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]