FROM maven:latest
VOLUME /tmp
COPY . .
RUN mvn clean install
COPY target/ct1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
