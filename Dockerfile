FROM openjdk:java-25-openjdk
WORKDIR /microservicios
ARG JAR_FILE=target/microservicio-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} microservicios.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "microservicios.jar"]

