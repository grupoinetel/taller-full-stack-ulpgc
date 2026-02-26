FROM tomcat:10.1-jdk17

# Remove default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR
COPY ./gestor-tareas-web/target/full-stack-web-1.0.war /usr/local/tomcat/webapps/app.war

EXPOSE 8080
