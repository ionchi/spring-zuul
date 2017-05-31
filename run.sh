#!/usr/bin/env bash
(cd ./gateway && sudo sh deploy.sh)
(cd ./movie && sudo sh deploy.sh)

docker-compose up -d
