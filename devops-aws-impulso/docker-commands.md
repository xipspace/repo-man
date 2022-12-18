
# Intro

	docker -v
	docker images
	docker ps -a

# Executando aplicações

conteiner

	docker run -dti --name ubuntu1 ubuntu

config do conteiner

	docker inspect ubuntu1

executar bash do conteiner

	docker exec -it ubuntu1 /bin/bash

parar e remover

	docker kill $(docker ps -q)
	docker rm $(docker ps -a -q)
	docker rmi $(docker images -q)

# Processamento, logs e redes

	docker stats
	docker run --name <name> -dti -m 128M --cpus 0.2 <image>

	docker info

	docker logs <name>

	docker network ls
	docker network inspect bridge

# Web Server

	docker pull php:7.4-apache
	docker run --name apache1 -d -p 80:80 --volume=/mnt/storage/data:/var/www/html php:7.4-apache

# Imagens

dockerfile

	docker build . -t <name> -f dockerfile
	docker run -ti --name <name1> <name>
	docker start [-i] <name1>

# Docker compose

	docker-compose up -d -f composefile
	docker-compose down

# Secrets

# Vagrant

cluster swarm

	vagrant init
	vagrant up
	vagrant ssh <name>
	vagrant destroy -f

	docker swarm init --advertise-addr 0.0.0.0

	docker service

	docker node update --availability drain <name>

vagrantfile

> config.vm.box = "bento/ubuntu-22.04"
> config.vm.provision "shell", path: "docker.sh"

# Minikube

kubectl
