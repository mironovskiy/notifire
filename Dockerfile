FROM amazoncorretto:17

WORKDIR /app

COPY /build/libs/*.jar notifire.jar

CMD ["java", "-jar", "notifire.jar"]
