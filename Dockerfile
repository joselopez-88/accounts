#Start with a base image containing Java runtime
FROM eclipse-temurin:21.0.7_6-jre-ubi9-minimal

# Add Maintainer Info
LABEL \
  maintainer="Jose Alberto <alberto.esteva88@gmail.com>" \
  org.opencontainers.image.title="Accounts MicroService" \
  org.opencontainers.image.description="Spring Boot microservice for account management" \
  org.opencontainers.image.authors="Jose Alberto <alberto.esteva88@gmail.com>" \
  org.opencontainers.image.version="0.0.1-SNAPSHOT"


#Define working directory
WORKDIR /app

# Copy the application's jar to the container in working directory
COPY target/accounts-0.0.1-SNAPSHOT.jar .

# Define default command to execute when docker container starts, (this will run the jar file)
ENTRYPOINT [ "java", "-jar", "accounts-0.0.1-SNAPSHOT.jar" ]