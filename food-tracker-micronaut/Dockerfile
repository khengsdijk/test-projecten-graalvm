FROM oracle/graalvm-ce:20.0.0-java8 as graalvm
# For JDK 11
#FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/food-tracker-micronaut
WORKDIR /home/app/food-tracker-micronaut

RUN native-image --no-server --initialize-at-build-time=org.postgresql.Driver,org.postgresql.util.SharedTimer -cp target/food-tracker-micronaut-*.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/food-tracker-micronaut/food-tracker-micronaut /app/food-tracker-micronaut
ENTRYPOINT ["/app/food-tracker-micronaut"]
