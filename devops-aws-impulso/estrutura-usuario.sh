#!/bin/bash

# devops-aws-impulso
# Infraestrutura como Código: Script de Criação de Estrutura de Usuários, Diretórios e Permissões
# adaptado

# pastas
mkdir /publico
mkdir /adm

# grupos
groupadd GROUP_ADM

# users
useradd convidado1 -m -s /bin/bash -p $(openssl passwd -crypt Senha123) -G GROUP_ADM
useradd convidado2 -m -s /bin/bash -p $(openssl passwd -crypt Senha123) -G GROUP_ADM
useradd convidado3 -m -s /bin/bash -p $(openssl passwd -crypt Senha123) -G GROUP_ADM

# permissoes
chown root:GROUP_ADM /adm
chmod 770 /adm
chmod 777 /publico

# reset senha
passwd -e convidado1
passwd -e convidado2
passwd -e convidado3

echo "fim"
