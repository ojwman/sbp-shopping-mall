FROM maven
ADD ./springboot-server-start.sh app.sh
ENTRYPOINT ["/bin/sh","app.sh"]