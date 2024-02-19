FROM eclipse-temurin:17.0.10_7-jre-alpine as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:17.0.10_7-jre-alpine
MAINTAINER Rami-Bahrouni
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

RUN apk --no-cache add curl=8.5.0-r0 && \
    addgroup -g 1000 -S ingredients && adduser -u 1000 -S ingredients -G ingredients && \
    chown -R ingredients:ingredients /usr/
EXPOSE 8080 8443
USER ingredients
# Define the health check
HEALTHCHECK --interval=45s --timeout=15s CMD curl -k --fail http://localhost:8080/ || exit 1

ENV EUREKA_HOSTNAME=place_holder
ENV EUREKA_PORT=place_holder

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]