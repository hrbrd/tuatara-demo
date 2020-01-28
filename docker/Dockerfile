FROM maven:3.6.3-jdk-8-openj9

RUN cd /
RUN apt-get update && apt-get -y install git
RUN git clone https://github.com/hrbrd/tuatara-demo.git
RUN mvn -f /tuatara-demo/pom.xml clean install

EXPOSE 8080