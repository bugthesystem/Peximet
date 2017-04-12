FROM fabric8/java-alpine-openjdk8-jdk

ENTRYPOINT ["java" ,"-jar", "/app/app.jar"]

ADD ./target/scala-2.12/Peximet-assembly*.jar /app/app.jar
