FROM openjdk:21
VOLUME /app
WORKDIR /app
COPY ./target/mounirmasterclass-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "mounirmasterclass-0.0.1-SNAPSHOT.jar"]