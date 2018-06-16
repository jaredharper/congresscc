FROM maven:latest
COPY . .
RUN mvn clean install
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dspring.profiles.active=${ENV} -Djava.security.egd=file:/dev/./urandom -jar /target/ct1-0.0.1-SNAPSHOT.jar"]
