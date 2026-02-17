# Stage 1: Build the application
FROM maven:3.8-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Run the application
FROM tomcat:11-jdk21-temurin
WORKDIR /usr/local/tomcat/webapps/
COPY --from=build /app/internal-admin-webapp/target/internal-admin.war .
COPY --from=build /app/www-images-webapp/target/www-images.war images.war
CMD ["catalina.sh", "run"]