FROM openjdk:11

VOLUME /app

ADD target/major-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT exec java -jar app.jar