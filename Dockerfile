FROM eclipse-temurin:17-jdk-jammy AS builder

# create dependency cache for repeated build during local development.
COPY gradlew gradlew
COPY gradle/ gradle/
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
RUN ./gradlew getDeps

# build the app.
COPY ./src ./src
RUN ./gradlew build

# runtime
FROM eclipse-temurin:17-jre-jammy

RUN groupadd -g 61000 jload && \
    useradd -g 61000 -l -M -s /bin/false -u 61000 jload

COPY --from=builder --chown=jload:jload ./build/libs/jload.jar .

USER jload
ENTRYPOINT ["java", "-jar", "./jload.jar"]
EXPOSE 8080
