FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/complain-service-0.0.1-SNAPSHOT.jar complain-service-0.0.1-SNAPSHOT.jar
EXPOSE 4011
ENTRYPOINT ["java","-jar","/complain-service-0.0.1-SNAPSHOT.jar"]
