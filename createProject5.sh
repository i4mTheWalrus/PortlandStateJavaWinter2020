#!/bin/bash

source checkForJava.sh

if ! checkForJava; then
    echo "** Java is not configured correctly in your environment"
    exit 1
fi

if ! checkForMavenSettings; then
    echo "** Your Maven settings are not configured correctly in your environment"
    exit 1
fi

if [ "$#" -ne 1 ]; then
    echo "** Missing your student id"
    exit 1
fi

studentId=race3

./mvnw --batch-mode archetype:generate \
  -DinteractiveMode=false \
  -DarchetypeGroupId=edu.pdx.cs410J \
  -DarchetypeArtifactId=airline-web-archetype \
  -DarchetypeVersion=Winter2020 \
  -DgroupId=edu.pdx.cs410J.${studentId} \
  -DartifactId=airline-web \
  -Dpackage=edu.pdx.cs410J.${studentId} \
  -Dversion=Winter2020
