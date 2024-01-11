FROM openjdk:17
ARG backend
EXPOSE 8080
WORKDIR /backend
COPY /build/libs/*.jar /backend/app.jar
CMD [ "java", "-jar", "/backend/app.jar" ]