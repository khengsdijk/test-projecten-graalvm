
# to build: 
# docker build -f docker/Dockerfile -t micronaut/foodtracker-micronaut-community .
# docker build -f docker/Dockerfile -t micronaut/foodtracker-micronaut-enterprise .
#
# to run:
# docker run -i --rm -p 8083:8083 micronaut/foodtracker-micronaut-enterprise
# docker run -i --rm -p 8083:8083 micronaut/foodtracker-micronaut-community

FROM registry.access.redhat.com/ubi8/ubi-minimal:8.1
WORKDIR /work/
# RUN apk update && apk add libstdc++
COPY target/food-tracker-micronaut-enterprise /work/food-tracker-micronaut

# set up permissions for user `1001`
RUN chmod 775 /work /work/food-tracker-micronaut \
  && chown -R 1001 /work \
  && chmod -R "g+rwX" /work \
  && chown -R 1001:root /work

EXPOSE 8080
USER 1001

CMD ["./food-tracker-micronaut"]

