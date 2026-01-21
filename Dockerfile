FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/articulos-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]