# Utiliza la imagen correcta de Eclipse Temurin
FROM eclipse-temurin:17-jdk-alpine

# Crea un directorio de trabajo
WORKDIR /app

# Copia el archivo JAR al directorio de trabajo
COPY target/ejemplo-0.0.1-SNAPSHOT.jar ejemplo-0.0.1-SNAPSHOT.jar

# Expone el puerto 8080
EXPOSE 8080

# Define el comando por defecto para ejecutar el JAR
CMD ["java", "-jar", "ejemplo-0.0.1-SNAPSHOT.jar"]
