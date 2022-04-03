Create jar:
    ./gradlew build && java -jar build/libs/testingApi-0.0.1.jar

Docker:
    docker build -t kotlin .; docker run -p 4000:8080 kotlin
    docker build -t kotlin .; docker run -p 4000:8080
