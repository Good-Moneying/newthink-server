#!/bin/bash

# Docker install
if ! command -v docker &> /dev/null; then
    echo "Docker is not installed..."
    echo "Docker install start..."
    sudo apt-get update -y
    sudo apt-get install -y apt-transport-https ca-certificates curl gnupg-agent software-properties-common
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
    sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
    sudo apt-get update -y
    sudo apt-get install -y docker-ce docker-ce-cli containerd.io
    echo "Docker install complete"
else
    echo "Docker is already installed"
fi

# Docker-compose install
if ! command -v docker-compose &> /dev/null; then
    echo "Docker-compose is not installed..."
    echo "Docker-compose install start..."
    sudo curl -L "https://github.com/docker/compose/releases/download/1.28.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose
    sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
    echo "Docker-compose install complete!"
else
    echo "Docker-compose is already installed"
fi

#RUNNING_CONTAINER=$(docker ps | grep blue)
#NGINX_CONF="/home/ec2-user/data/nginx/app.conf"
RUNNING_NGINX=$(docker ps | grep nginx)
BATCH_CONTAINER="batch"

#if [ -z "$RUNNING_CONTAINER" ]; then
#    TARGET_SERVICE="blue-api"
#    OTHER_SERVICE="green-api"
#    TARGET_PORT="8082"
#    OTHER_PORT="8081"
#else
#    TARGET_SERVICE="green-api"
#    OTHER_SERVICE="blue-api"
#    TARGET_PORT="8081"
#    OTHER_PORT="8082"
#fi


#echo "$TARGET_SERVICE Deploy..."
#docker-compose -f /home/ec2-user/docker-compose.yml up -d $TARGET_SERVICE $BATCH_CONTAINER

# Wait for the target service to be healthy before proceeding
sleep 10

#if [ -z "$RUNNING_NGINX" ]; then
#    echo "Starting Nginx... Changing port to $TARGET_PORT"
#    docker-compose -f /home/ec2-user/docker-compose.yml up -d nginx
#fi
#
## Update the nginx config and reload
#sed -i "s/$OTHER_SERVICE/$TARGET_SERVICE/" $NGINX_CONF
#sed -i "s/$OTHER_PORT/$TARGET_PORT/" $NGINX_CONF
#docker-compose -f /home/ec2-user/docker-compose.yml restart nginx
#
## Stop the other service
#docker-compose -f /home/ec2-user/docker-compose.yml stop $OTHER_SERVICE

# docker-compose down & up
#nohup docker-compose -f /home/ec2-user/docker-compose.yml down > /dev/null 2>&1 &
#nohup docker-compose -f /home/ec2-user/docker-compose.yml up -d > /dev/null 2>&1 &
