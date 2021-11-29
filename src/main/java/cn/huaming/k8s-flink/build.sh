#!/usr/bin/env bash
################################################
# Build docker image with Dockerfile
# Author Galen Luo
# Version 1.0.4
# Date 2021-06-23 11:50:00
################################################

################################################
# Define constants
################################################
VERSION=stream
BUILDER=zhm
NAMESPACE=scloud
DOCKER_FILE_PATH=.
SERVICE_NAME=demo-flink-app
REGISTRY_URL=hb.shencom.cn

################################################
# Define member variables
################################################
CURRENT_DATE=$(date +'%Y%m%d')
TAG="$BUILDER"-"$CURRENT_DATE"-"$VERSION"
IMAGE="$REGISTRY_URL"/"$NAMESPACE"/"$SERVICE_NAME":"$TAG"

################################################
# Define functions
################################################

build() {
  docker -v
  docker build -t "$IMAGE" $DOCKER_FILE_PATH
}

push() {
  docker push "$IMAGE"
}

print_image() {
  echo -e "\e[93m$IMAGE\e[0m"
}

################################################
# The Main function
################################################
main() {
  build
  push
  print_image
}

main
