#! /bin/bash

cd ..
git clone git@github.com:Terross/wise-task-task.git
git clone git@github.com:Terross/wise-task-profile.git
git clone git@github.com:Terross/wise-task-graph.git
git clone git@github.com:Terross/wise-task-plugin.git

cd wise-task-gateway
username="Terross"

echo "Введите токен:"
read token

USERNAME=$username TOKEN=$token docker-compose up --build
