#
# Build stage
#
FROM maven:3.9-amazoncorretto-20 AS build
COPY MeAjude/src /home/app/src
COPY MeAjude/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:20
COPY --from=build /home/app/target/meAjude-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]