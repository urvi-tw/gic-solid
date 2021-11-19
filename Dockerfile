FROM openjdk:alpine
ADD build/libs/solid-0.0.1-SNAPSHOT.jar solid.jar
EXPOSE $PORT
ENTRYPOINT ["java","-jar","-Dserver.port=${PORT}","-Xms256m", "-Xmx512m","solid.jar"]
