# Etapa 1: Build do projeto
FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Realiza o build do projeto, empacotando-o como um arquivo WAR
RUN mvn clean package -DskipTests

# Fase 2: Execução com Tomcat
FROM tomcat:10.1.19-jdk11

# Copia o arquivo WAR gerado para o diretório de deploy do Tomcat
COPY --from=build /app/target/CRUD_HESTIA-JDBC_Servlet-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/app.war

# Expõe a porta 8080 para acesso ao aplicativo
EXPOSE 8080

# Define o comando para iniciar o Tomcat
CMD ["catalina.sh", "run"]
