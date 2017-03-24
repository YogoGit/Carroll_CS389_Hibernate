FROM anapsix/alpine-java:8_server-jre

ENV ZIP=play2torial-1.0-SNAPSHOT.zip
ADD $ZIP /

RUN unzip /$ZIP && rm $ZIP
WORKDIR /play2torial-1.0-SNAPSHOT

COPY docker-entrypoint.sh /
RUN dos2unix /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]

CMD ["/bin/sh", "-c", "bin/play2torial"]
