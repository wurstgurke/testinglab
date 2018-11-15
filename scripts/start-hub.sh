docker network create grid
docker run -d -p 4444:4444 --net grid --name selenium-hub selenium/hub:3.141.0-actinium
docker run -d --net grid  --name node-chrome -e HUB_HOST=selenium-hub -v /dev/shm:/dev/shm selenium/node-chrome:3.141.0-actinium
docker run -d --net grid  --name node-firefox -e HUB_HOST=selenium-hub -v /dev/shm:/dev/shm selenium/node-firefox:3.141.0-actinium
