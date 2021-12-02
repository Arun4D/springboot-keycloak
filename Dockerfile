FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine AS build
WORKDIR /workspace/app

COPY . /workspace/app
RUN chmod +x gradlew
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build -xtest
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","in.co.ad.customer.portal.backend.CustomerPortalBackendApplication"]
