FROM openjdk:25-jdk-slim
WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} usuarios_microservicios.jar

EXPOSE 9001 

ENTRYPOINT ["java", "-jar", "usuarios_microservicios.jar"]