docker container rm -f selenium-hub || true
docker container rm -f node-chrome || true
docker container rm -f node-firefox || true

docker network rm grid || true
