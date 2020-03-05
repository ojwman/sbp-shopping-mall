#!/bin/bash
echo "start spring boot server"

# remove
rm -r sbp-shopping-mall

# clone git
git clone https://github.com/ojwman/sbp-shopping-mall

# move
cd sbp-shopping-mall

# build
mvn package

# run
cd target
java -jar -Dspring.profiles.active=production sbp-shopping-mall-0.0.1-SNAPSHOT.jar

echo "boot"
