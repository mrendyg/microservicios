FROM eclipse-temurin:25-jdk
WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eureka_server.jar

EXPOSE 8761 

ENTRYPOINT ["java", "-jar", "eureka_server.jar"]