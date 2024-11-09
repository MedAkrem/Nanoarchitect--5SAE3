FROM openjdk:17
EXPOSE 8020
ADD target/salaireMS-0.0.1-SNAPSHOT.jar salaire-docker.jar
ENTRYPOINT ["java", "-jar", "salaire-docker.jar"]
