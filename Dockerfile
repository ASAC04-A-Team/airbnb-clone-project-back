FROM amazoncorretto:17-alpine

# Gradle Wrapper 스크립트와 프로젝트 파일 복사
COPY gradlew /app/
COPY gradle /app/gradle/
COPY build.gradle /app/
COPY settings.gradle /app/

# 의존성 다운로드를 위해 초기 빌드 실행
WORKDIR /app
RUN ./gradlew build || return 0

# 애플리케이션 코드 및 리소스 복사
COPY src /app/src

# 실제로 빌드된 JAR 파일 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
