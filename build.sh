#! /bin/bash
username="Terross"

echo "Введите токен:"
read token

USERNAME=$username TOKEN=$token docker-compose up --build