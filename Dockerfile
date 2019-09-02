FROM adoptopenjdk/openjdk12
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} smart-mocks.jar
ENTRYPOINT ["java","-jar","/smart-mocks.jar"]
EXPOSE 8085