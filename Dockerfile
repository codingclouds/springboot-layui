#docker暂时没有部署mysql
FROM java:8
VOLUME /tmp
ADD manager-0.0.1-SNAPSHOT.jar /manager.jar
ENTRYPOINT ["java","-jar","/manager.jar"]