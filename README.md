# Encora Challenge

## Descripción

Servicio para envio de notificaciones a traves de un servicio de mensajeria.

## Tecnologias

- Java 11
- Spring boot
- Webflux

## Instalacion

- Ejecutar comando: mvn clean install
- Correr la aplicacion

Ejecutar el siguiente curl:

    curl --location 'http://localhost:9092/api/v1/notification' \
    --header 'Content-Type: application/json' \
    --data '{
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "userId": "1",
    "message": "Enviando notificacion",
    "timestamp": "2024-10-21T15:22:14.122"
    }'