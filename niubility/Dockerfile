FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
WORKDIR /app

COPY --from=build /app/target/niubility-0.0.1-SNAPSHOT.jar /app/jar/

EXPOSE 8080
CMD ["java", "-jar", "/app/jar/niubility-0.0.1-SNAPSHOT.jar"]
