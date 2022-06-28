FROM openjdk:8-jre-alpine
ENV APP_FILE app.jar
ENV APP_ROOT /opt/app-root/bin
COPY target/*.jar $APP_ROOT/$APP_FILE
RUN chown 1001:0 ${APP_ROOT} && \
    chgrp -R 0 ${APP_ROOT} && \
    chmod -R g=u ${APP_ROOT}
    
WORKDIR $APP_ROOT
USER 1001
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]