FROM gradle:7.0.2-jdk8 AS build
COPY . /home/gradle
WORKDIR /home/gradle
RUN gradle build


FROM openjdk:8
RUN mkdir /app
RUN mkdir /script
COPY --from=build /home/gradle/build/libs/*.jar /app/issue-tracker.jar
COPY --from=build /home/gradle/start.sh /script/start.sh

RUN apt-get update
RUN apt-get install redis-tools -y

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

ENTRYPOINT ["/bin/bash", "/script/start.sh"]
