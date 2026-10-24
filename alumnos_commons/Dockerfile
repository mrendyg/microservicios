FROM openjdk:21-jdk-slim
WORKDIR /alumnos_commons
# Toma el jar generado dentro del target de ese microservicio
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} alumnos_commons.jar
EXPOSE 8080 
# (Cambia el 8080 por el puerto específico de tu microservicio, ej: 8090, 8761, etc.)
ENTRYPOINT ["java", "-jar", "app.jar"]