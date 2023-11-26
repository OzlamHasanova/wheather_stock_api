FROM openjdk
WORKDIR /app
EXPOSE 8087
ARG JAR_FILE=target/wheather_stock_api-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","/application.jar"]