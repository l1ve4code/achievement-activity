FROM maven:3.9.9-amazoncorretto-23 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml package

FROM amazoncorretto:23-alpine-jdk
COPY --from=build /home/app/target/activity-service-1.0-jar-with-dependencies.jar /usr/local/lib/application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/application.jar"]