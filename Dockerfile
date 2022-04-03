FROM openjdk:15-jdk-alpine
RUN mkdir /app
WORKDIR /app
EXPOSE 8080
ADD ./build/libs/testingApi-0.0.1.jar .
CMD ["java", "-jar", "TestSpringApi.jar"]