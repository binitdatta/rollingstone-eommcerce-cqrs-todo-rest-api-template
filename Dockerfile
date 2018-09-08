FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
WORKDIR /app
ADD $WORKSPACE/build/libs/solstice-eommcerce-cqrs-todo-rest-api-template-1.0.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 8082
EXPOSE 9082
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar" ]
