# Usar uma imagem base com Java
FROM openjdk:17

# Copiar o arquivo .jar do seu projeto para o container
COPY target/avaliacao-0.0.1-SNAPSHOT.jar avaliacao-0.0.1-SNAPSHOT.jar

# Definir variáveis de ambiente para configurar o Spring Boot
ENV SPRING_PROFILES_ACTIVE=docker

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/avaliacao-0.0.1-SNAPSHOT.jar"]