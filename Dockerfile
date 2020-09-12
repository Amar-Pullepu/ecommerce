FROM openjdk:8

EXPOSE 8000

ADD Web/target/ecommerce.war ecommerce.war

ENTRYPOINT ["java", "-jar", "ecommerce.war"]