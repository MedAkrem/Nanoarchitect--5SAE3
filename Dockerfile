FROM openjdk:17
EXPOSE 8087
ADD target/Pointage-Service-0.0.1-SNAPSHOT.jar pointage-service.jar
ENTRYPOINT ["java", "-jar", "pointage-service.jar"]
