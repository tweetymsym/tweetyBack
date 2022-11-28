# in order to increase the performance and minimize the size of the image, we will perform the image building in multiple steps
# the first the the jar building step:
FROM openjdk:11-jre-slim-stretch as naming-server-builder
# here we get the name of the jar file
ARG JAR_FILE=target/*.jar
# we copy the jar file into the image storage
COPY $JAR_FILE naming-server.jar
# here we extract the files in the jar file, this is in order to be able to separate our image into multiple layers
# not that it has to be specified in the POM.xml file so that the jar build can be transformed to a layered application
RUN java -Djarmode-layertools -jar naming-server.jar extract

# next is where we will build the layers of the image:
FROM openjdk:11-jre-slim-stretch

# the first layer is the dependencies' layer:
COPY --from=naming-server-builder dependencies/ ./
RUN true
# the second layer is the snapshot dependencies layer:
COPY --from=naming-server-builder snapshot-dependencies/ ./
RUN true
# the third layer is for spring boot loader
COPY --from=naming-server-builder spring-boot-loader/ ./
RUN true
# the fourth layer is for our application:
COPY --from=naming-server-builder application/ ./

# finally the entry point:
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]