FROM maven:3.3-jdk-8
ADD ./springboot-server-start.sh app.sh
VOLUME /sbp-shopping-mall-log
ENTRYPOINT ["/bin/sh","app.sh"]