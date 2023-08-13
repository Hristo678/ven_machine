FROM adoptopenjdk/openjdk17:alpine-jre
WORKDIR /app
COPY target/ven_machine-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]