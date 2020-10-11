FROM centos:8
MAINTAINER Alesey Ubozhenko
RUN yum install -y sudo java-11-openjdk && \
    yum clean all && \
    mkdir -p /opt/crashmap

RUN alternatives --remove-all java && \
    update-alternatives --install /usr/bin/java java /usr/lib/jvm/java-11*/bin/java 2

WORKDIR /opt/crashmap

ADD target/crash*.jar /opt/crashmap/crashmap.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/opt/crashmap/crashmap.jar"]
