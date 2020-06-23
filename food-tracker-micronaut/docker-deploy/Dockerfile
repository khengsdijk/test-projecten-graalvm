FROM frolvlad/alpine-glibc
WORKDIR /work/
RUN apk update && apk add libstdc++
COPY target/food-tracker-micronaut/ /work/food-tracker-micronaut

# set up permissions for user `1001`
RUN chmod 775 /work /work/application \
  && chown -R 1001 /work \
  && chmod -R "g+rwX" /work \
  && chown -R 1001:root /work

EXPOSE 8080
USER 1001

CMD ["./food-tracker-micronaut"]

