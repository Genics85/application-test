FROM openjdk:17-jdk

WORKDIR /app

COPY ./target/rancard-0.0.1-SNAPSHOT.jar ./app.jar

COPY ./src/main/resources/application.properties ./application.properties

EXPOSE  8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
