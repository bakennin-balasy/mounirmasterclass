FROM eclipse-temurin:21-jdk
VOLUME /app
WORKDIR /app
COPY ./target/mounirmasterclass-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "mounirmasterclass-0.0.1-SNAPSHOT.jar"]