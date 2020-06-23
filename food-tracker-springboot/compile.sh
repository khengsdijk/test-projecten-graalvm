#!/usr/bin/env bash

ARTIFACT=food-tracker-springboot
MAINCLASS=org.acme.foodtrackerspringboot.FoodTrackerSpringbootApplication
VERSION=1
#FEATURE='file:///home/koen/Documents/hva/afstudeeropdracht/testprojectenGraal/spring-graal-native-0.6.0.RELEASE.jar'

GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

rm -rf target
mkdir -p target/native-image

echo "Packaging $ARTIFACT with Maven"
mvn -DskipTests package > target/native-image/output.txt

JAR="$ARTIFACT-$VERSION.jar"
rm -f $ARTIFACT
echo "Unpacking $JAR"
cd target/native-image
jar -xvf ../$JAR >/dev/null 2>&1
cp -R META-INF BOOT-INF/classes

LIBPATH=`find BOOT-INF/lib | tr '\n' ':'`

CP=BOOT-INF/classes:$LIBPATH
#CP=BOOT-INF/classes:$LIBPATH:$FEATURE

GRAALVM_VERSION=`native-image --version`


echo "Compiling $ARTIFACT with $GRAALVM_VERSION"
{ time native-image \
  --verbose \
  --no-server \
  -H:Name=$ARTIFACT \
  --no-fallback \
  --allow-incomplete-classpath \
  --report-unsupported-elements-at-runtime \
  -H:-UseServiceLoaderFeature \
  -H:+ReportExceptionStackTraces \
  -H:+TraceClassInitialization \
  -Dspring.graal.remove-unused-autoconfig=true \
  -Dspring.graal.skip-logback=true \
  -Ddebug=true \
  -Dspring.graal.verbose=true \
  --initialize-at-build-time=org.springframework.util.unit,net.bytebuddy.implementation.bind.annotation,net.bytebuddy.description.type,net.bytebuddy.ClassFileVersion,net.bytebuddy.matcher \
  -cp $CP $MAINCLASS >> output.txt ; } 2>> output.txt

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


