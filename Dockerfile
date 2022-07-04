FROM java:8
WORKDIR /
ADD target/msoauth-0.0.1-SNAPSHOT.jar //
ADD initData/ /initData/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/msoauth-0.0.1-SNAPSHOT.jar"]