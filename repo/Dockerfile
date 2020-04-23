FROM adoptopenjdk/openjdk11:alpine-slim
EXPOSE 8888/tcp
COPY ./target/repo-0.0.1-SNAPSHOT.jar /repo.jar
CMD ["/usr/bin/java", "-jar", "/repo.jar"]