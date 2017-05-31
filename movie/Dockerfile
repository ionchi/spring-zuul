FROM tomcat:8.0.20-jre8
ADD tomcat-users.xml $CATALINA_HOME/conf

COPY ./target/movie-0.0.1.war /usr/local/tomcat/webapps/movie.war