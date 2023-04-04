# syntax=docker/dockerfile:1

FROM openjdk:17-jdk-alpine

ARG APP_DIR='/app'
ARG JAVA_OPTS

ENV JAVA_OPTS=$JAVA_OPTS

WORKDIR APP_DIR

COPY ./target/programming-languages-api-0.0.1-SNAPSHOT.jar ./programming-languages-api.jar

EXPOSE 8000

ENTRYPOINT exec java $JAVA_OPTS -jar ./programming-languages-api.jar
