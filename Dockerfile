FROM openjdk:11
COPY ./target/employee.jar employee.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/employee.jar"]