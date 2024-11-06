# Etapa de construção: criar o arquivo .war
FROM maven:latest AS warfile
WORKDIR /hestia
COPY pom.xml . 
RUN mvn -B -f pom.xml dependency:resolve
COPY . .
RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package
 
# Etapa de execução: Tomcat para rodar o .war
FROM tomcat:9.0-jre8-alpine
# Definindo o diretório de trabalho no Tomcat
WORKDIR /usr/local/tomcat/bin
COPY run.sh run.sh
RUN chmod +x run.sh
 
# Copiar o arquivo .war gerado na etapa de construção para o diretório de aplicativos do Tomcat com um nome único
WORKDIR /usr/local/tomcat/webapps
COPY  --from=warfile /hestia/target/CRUD_HESTIA-JDBC_Servlet-1.0-SNAPSHOT.war Hestia.war
 
# Inicializando o Tomcat
# Expose ports
ENV JPDA_ADDRESS="8000"
ENV JPDA_TRANSPORT="dt_socket"
EXPOSE 8080
WORKDIR /usr/local/tomcat/bin
CMD ["run.sh"]
# -------------------------------------------------------------------------------------------------
