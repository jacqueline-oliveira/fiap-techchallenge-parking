FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/techchallenge-parking-0.0.1-SNAPSHOT.jar techchallengeparking.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar techchallengeparking.jar
