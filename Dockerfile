FROM eclipse-temurin/temurin:17-jdk-alpine
 # CREA UN DIRECTORIO APP donde copiara todo lo de target
WORKDIR /app

COPY target/ejemplo-0.0.1-SNAPSHOT.jar ejemplo-0.0.1-SNAPSHOT.jar
    # el primer jar es origen el siguiente destino (ejemplo-0.0.1-SNAPSHOT.jar) donde se copiara en app
    # EJECUTA EL JAR, los datos ejemplo-0.0.1-SNAPSHOT.jar es el nombre, son los datos que se generan al hacer el build. bienen de pom.xml
EXPOSE 8080
CMD ["java", "-jar", "ejemplo-0.0.1-SNAPSHOT.jar"]
