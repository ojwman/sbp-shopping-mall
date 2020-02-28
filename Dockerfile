FROM maven:3.3-jdk-8
ADD ./springboot-server-start.sh app.sh
ENTRYPOINT ["/bin/sh","app.sh"]