FROM amazoncorretto:17

WORKDIR /app

COPY /target/*.jar notifire.jar

CMD ["java", "-jar", "notifire.jar"]
