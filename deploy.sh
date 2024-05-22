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

# Load environment variables
if [ -f /home/ec2-user/.env ]; then
    echo "Loading environment variables from .env file..."
    export $(cat /home/ec2-user/.env | xargs)
else
    echo ".env file not found!"
    exit 1
fi

# Define container names
NGINX_CONF="/home/ec2-user/data/nginx/app.conf"
RUNNING_NGINX=$(docker ps | grep nginx)
BATCH_CONTAINER="batch"
SERVER_CONTAINER="server"
SELENIUM_CONTAINER="selenium"

# Start services
echo "Starting services..."
docker-compose -f /home/ec2-user/docker-compose.yml up -d $SELENIUM_CONTAINER $SERVER_CONTAINER $BATCH_CONTAINER

# Wait for the services to be healthy before proceeding
sleep 10

if [ -z "$RUNNING_NGINX" ]; then
    echo "Starting Nginx..."
    docker-compose -f /home/ec2-user/docker-compose.yml up -d nginx
fi

# Restart nginx and selenium to ensure they're using the latest configuration
docker-compose -f /home/ec2-user/docker-compose.yml restart nginx
docker-compose -f /home/ec2-user/docker-compose.yml restart selenium
