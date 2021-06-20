# The webapp layer requires a packaged orm layer, 
# so we can use it as the base image
# The orm layer already contains maven and tomcat too
ARG orm_version=latest

FROM jpbulloch5/revature_p1_orm:${orm_version} AS builder

# setup a working directory for the application
# the working directory for the orm layer was /app
# jar file location ==> /app/target/eorm-1.o.jar
WORKDIR /app/web

# Copy the cucrrent directory into the container
COPY . .

# Create a clean package from the pom.xml file and install it
# maven expects to find the orm jar file at 
#RUN mvn install
RUN mvn clean package && mvn install
RUN mv /app/web/target/p1-webapp-0.9.war /app/web/target/webapp.war

######### Multi-stage build: Switch to Tomcat
FROM tomcat:8-jdk8-corretto
WORKDIR /usr/local/tomcat/webapps/

# All we need from the build is the .war file and the test file
COPY --from=builder /app/web/target/webapp.war ./webapp.war
RUN mkdir /app/web 
COPY --from=builder /app/web/P1_Local_Postman_Collection.json /app/web/

# I guess the tomcat image is purposely broken for "security"?
# anyway, I found online that this helped people, so I am trying it
#RUN mv webapps webapps2 && mv webapps.dist/ webapps
# needs to run after catalina.sh starts, so moved to CMD

EXPOSE 9052

# # Trying to bring Newman for Postman into this container
# RUN apt-get update
# RUN apt-get install nodejs npm -y
# RUN npm install -g newman

# # Run some API tests using newman
# RUN touch postman_log.txt
# RUN newman run P1_Local_Postman_Collection.json >> postman_log.txt

CMD ["catalina.sh", "run", "&&", "mv", "webapps", "webapps2", "&&", "mv", "webapps.dist/", "webapps"]