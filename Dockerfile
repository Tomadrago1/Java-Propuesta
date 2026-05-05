# ETAPA 1: Compilación
# eclipse-temurin:11-jdk es el sucesor oficial de openjdk:11 (eliminado de Docker Hub)
FROM eclipse-temurin:11-jdk AS builder

# Tomcat 10.1 provee jakarta.servlet.* (la rama principal está migrada a Tomcat 10)
COPY --from=tomcat:10.1-jdk11-temurin /usr/local/tomcat/lib/ /tomcat-lib/

WORKDIR /app

# Frontend (JSPs, CSS, recursos): viene del WebContent/ de la rama main
COPY WebContent/ ./webapp/

# 1. Backend Java de la rama recetas-recomendadas (trae los archivos nuevos de recetas recomendadas)
COPY Jartraining/src/main/java/ ./src/

# 2. Sobreescribir encima con el backend completo y actualizado de la rama main (contiene las versiones más nuevas de todas las clases)
COPY src/ ./src/

# Migración al vuelo: Convertir todos los "import javax.servlet" a "import jakarta.servlet" para Tomcat 10
RUN find src -name "*.java" -exec sed -i 's/import javax.servlet/import jakarta.servlet/g' {} +

# Directorio de salida de los .class (dentro de la webapp que se desplegará)
RUN mkdir -p webapp/WEB-INF/classes

# Classpath: JARs del proyecto (mysql, mail, etc.) + JARs de Tomcat (jakarta.servlet, jsp-api)
# Se excluyen los JARs obsoletos de servlet del proyecto para evitar conflictos de versiones con Tomcat 10
RUN CP=$(find webapp/WEB-INF/lib -name "*.jar" ! -name "*servlet*.jar" | tr '\n' ':') && \
    CP="$CP$(find /tomcat-lib -name "*.jar" | tr '\n' ':')" && \
    javac -cp "$CP" \
          -d webapp/WEB-INF/classes \
          $(find src -name "*.java")

# ETAPA 2: Ejecución con Tomcat 10.1 (Jakarta EE 9+, jakarta.servlet.*)
FROM tomcat:10.1-jdk11-temurin
RUN rm -rf /usr/local/tomcat/webapps/*

# Desplegamos la webapp completa (frontend viejo + backend unificado y compilado)
COPY --from=builder /app/webapp/ /usr/local/tomcat/webapps/ROOT/

EXPOSE 8080
CMD ["catalina.sh", "run"]