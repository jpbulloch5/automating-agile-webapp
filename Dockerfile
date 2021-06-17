# The webapp layer requires a packaged orm layer, 
# so we can use it as the base image
FROM jpbulloch5/revature_p1_orm:initial

# setup a working directory for the application
# the working directory for the orm layer was /app
# jar file location ==> /app/target/eorm-1.o.jar
WORKDIR /webApp

# Copy the cucrrent directory into the container
COPY . .

# Create a clean package from the pom.xml file and install it
# maven expects to find the orm jar file at 
RUN mvn clean package && mvn install

CMD ["/bin/bash"]