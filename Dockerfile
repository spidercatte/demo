FROM openjdk:11-jre-slim
MAINTAINER catb_23@hotmail.com
WORKDIR /app

ARG JAR_FILE
COPY target/${JAR_FILE} /app/etl-application.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "etl-application.jar"]
