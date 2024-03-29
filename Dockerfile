FROM openjdk:17-jdk
LABEL authors="someone"

COPY src /airbnb-back-end
WORKDIR /airbnb-back-end

CMD ["./gradlew", "bootRun"]