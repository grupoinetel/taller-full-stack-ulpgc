FROM eclipse-temurin:21-jdk AS build
WORKDIR /home/app

COPY .mvn .mvn/
COPY mvnw api/pom.xml ./
COPY api/gestor-tareas-core/pom.xml gestor-tareas-core/pom.xml
COPY api/gestor-tareas-web/pom.xml gestor-tareas-web/pom.xml

RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

COPY api/ .
RUN ./mvnw -pl gestor-tareas-web -am clean package -DskipTests

FROM tomcat:10.1-jdk21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /home/app/gestor-tareas-web/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
