FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/servicediscovery-0.0.1-SNAPSHOT.jar service_discovery.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /service_discovery.jar
EXPOSE 8761
