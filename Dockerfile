# syntax=docker/dockerfile:1
FROM gradle:latest AS build
COPY . /home/gradle/app
WORKDIR /home/gradle/app

RUN gradle build --no-daemon

FROM openjdk:8

EXPOSE 8080

COPY --from=build /home/gradle/app/build/libs/spaceeco-backend-0.0.1-SNAPSHOT.jar spaceeco-backend.jar
CMD ["java", "-jar", "spaceeco-backend.jar"]
