FROM openjdk:17
EXPOSE 8020
ADD target/department_Microservice-0.0.1-SNAPSHOT.jar department-docker.jar
ENTRYPOINT ["java", "-jar", "department-docker.jar"]