# Base Alpine Linux based image with OpenJDK JRE only
FROM adoptopenjdk/openjdk11:alpine-slim
EXPOSE 8080/tcp
COPY ./target/collector-0.0.1-SNAPSHOT.jar /collector.jar
CMD ["/usr/bin/java", "-jar", "/collector.jar"]