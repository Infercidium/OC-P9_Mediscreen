FROM openjdk:8-jdk-alpine
ADD target/mediscreen_info.jar
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081/tcp
ENTRYPOINT ["java","-jar","/app.jar"]