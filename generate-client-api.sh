#!/usr/bin/env bash

OPENAPI_FILE=./api/openapi.yaml
rm -rf .openapi-generator
rm -rf ./docs
rm -rf ./src/main/java/io/streamthoughts/kafka/connect/client/openapi

[ -f $OPENAPI_FILE ] && rm $OPENAPI_FILE

./mvnw clean generate-sources