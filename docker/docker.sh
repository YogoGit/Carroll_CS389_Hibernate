#!/bin/sh -x

TEMP=$(mktemp -d)

ZIP=play2torial-1.0-SNAPSHOT.zip

cp ../target/universal/$ZIP $TEMP
cp Dockerfile $TEMP
cp docker-entrypoint.sh $TEMP

cd $TEMP
docker build -t playapp .

cd /
rm -rf $TEMP
