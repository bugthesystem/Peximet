FROM fabric8/java-alpine-openjdk8-jdk

ENTRYPOINT ["java" ,"-jar", "/app/server.jar"]

ADD ./target/scala-2.12/peximet-assembly*.jar /app/app.jar
