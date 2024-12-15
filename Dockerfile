FROM openjdk:17-jdk-slim

ENV TZ=Asia/Seoul

COPY build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]