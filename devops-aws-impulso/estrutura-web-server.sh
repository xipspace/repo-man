#!/bin/bash

# devops-aws-impulso
# Infraestrutura como Código - Script de Provisionamento de um Servidor Web (Apache)

# pacotes
apt-get update
apt-get upgrade -y
apt-get install apache2 -y
apt-get install unzip -y

# home
cd /tmp
wget https://github.com/denilsonbonatti/linux-site-dio/archive/refs/heads/main.zip
unzip main.zip
cd linux-site-dio-main
cp -R * /var/www/html/

echo "fim"
