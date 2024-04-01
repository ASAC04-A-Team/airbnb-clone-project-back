FROM amazoncorretto:17-alpine
# Base Images에 따라 크기가 달라짐
# ./gradlew clean build -> jar 파일 생성됨

# jar 파일 생성되는 위치 지정
ARG JAR_FILE=build/libs/*.jar

# 위의 위치의 jar 파일을 가지고 app.jar라는 이미지 생성
COPY ${JAR_FILE} app.jar
# Github action 인스턴스가 여기서의 호스트

# 난 자바를 실행할꺼야docker build -t sosadday:0328 .
ENTRYPOINT ["java", "-jar", "./app.jar"]