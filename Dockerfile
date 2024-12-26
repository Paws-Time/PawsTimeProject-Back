# 1. 베이스 이미지 설정
FROM openjdk:17-jdk-slim

# 2. 시간대 설정
ENV TZ=Asia/Seoul

# 3. 애플리케이션 JAR 복사
COPY build/libs/*.jar app.jar

# 4. 프로파일 설정
ENV SPRING_PROFILES_ACTIVE=dev

# 5. 컨테이너 실행 명령
CMD ["java", "-jar", "app.jar"]
