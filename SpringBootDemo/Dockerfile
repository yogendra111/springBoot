FROM openjdk:17
EXPOSE 8080
ADD target/springBootDemo.jar springBootDemo-Docker.jar
ENTRYPOINT [ "java", "-jar", "/springBootDemo-Docker.jar" ]