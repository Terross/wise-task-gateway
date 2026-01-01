#! /bin/bash
username="Terross"

echo "Input token:"
read token

echo "Input service name"
read service

USERNAME=$username TOKEN=$token docker compose up --build -d $service

docker image prune -f