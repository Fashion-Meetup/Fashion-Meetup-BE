# OpenJDK 17 베이스 이미지 사용 -> 도커 이미지를 만들때 상요할 베이스 이미지
FROM openjdk:17-jdk-slim

# 컨테이너 내에서 애플리케이션을 실행할 디렉토리 설정
ARG JAR_FILE_PATH=target/*.jar

# 호스트 머신에서 애플리케이션 JAR 파일을 컨테이너로 복사
COPY /build/libs/meetup-0.0.1-SNAPSHOT.jar app.jar

# 컨테이너가 시작될 때 실행할 명령어 지정
ENTRYPOINT ["java", "-jar", "app.jar"]