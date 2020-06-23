#!/usr/bin/env bash

ARTIFACT=food-tracker-micronaut-community
MAINCLASS=org.acme.Application
VERSION=1

GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

rm -rf target
mkdir -p target/native-image

echo "Packaging $ARTIFACT with Maven"
mvn -DskipTests package > target/native-image/output.txt

GRAALVM_VERSION=`native-image --version`
cd target/native-image

echo "Compiling $ARTIFACT with $GRAALVM_VERSION"
{ time native-image \
  -H:Name=$ARTIFACT \
  --no-server \
  --no-fallback \
  -cp ../food-tracker-micronaut-0.1.jar >> output.txt ; } 2>> output.txt

if [[ -f $ARTIFACT ]]
then
  printf "${GREEN}SUCCESS${NC}\n"
  mv ./$ARTIFACT ..
  exit 0
else
  cat output.txt
  printf "${RED}FAILURE${NC}: an error occurred when compiling the native-image.\n"
  exit 1
fi


