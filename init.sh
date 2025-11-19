#! /bin/bash

cd ..
git clone git@github.com:Terross/wise-task-task.git
git clone git@github.com:Terross/wise-task-profile.git
git clone git@github.com:Terross/wise-task-graph.git
git clone git@github.com:Terross/wise-task-plugin.git

cd wise-task-gateway
cd src/main/resources
mkdir certs
cd certs

openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048 # приватный ключ
openssl rsa -in private.pem -pubout -out public.pem # публичный ключ

cd ../../../..

username="Terross"

echo "Введите токен:"
read token

USERNAME=$username TOKEN=$token docker-compose up --build
