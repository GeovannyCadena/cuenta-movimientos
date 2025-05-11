FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/cuenta-movimientos-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} cuenta-movimientos.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cuenta-movimientos.jar"]