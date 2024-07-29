# Microservicio de Customer

Este microservicio gestiona la información de los clientes, incluyendo detalles personales y estados. Además, maneja la comunicación asincrónica con el microservicio de cuentas (Accounts) para desactivar las cuentas de un cliente cuando este es desactivado.

## Funcionalidades Principales

1. **CRUD de Clientes**: Crear, leer, actualizar y eliminar registros de clientes.
2. **Integración Asincrónica**: Uso de Kafka para enviar mensajes al microservicio de Accounts para desactivar las cuentas asociadas cuando un cliente es desactivado.

## Endpoints

- **/clientes**:
    - **GET**: Dado un customerId obtiene informacion de un cliente.
    - **POST**: Crea un nuevo cliente.
    - **PUT**: Actualiza la información de un cliente existente.
    - **PATCH**: Edita la información de un cliente existente.
    - **DELETE**: Elimina un cliente.

## Comunicación Asincrónica

El microservicio de Customer utiliza Apache Kafka para comunicarse con el microservicio de Accounts. Cuando un cliente es desactivado, se envía un mensaje a través de Kafka para que el microservicio de Accounts procese y desactive todas las cuentas asociadas a ese cliente.

## Despliegue

El microservicio cuenta con un archivo `Dockerfile` que permite crear una imagen de Docker para desplegar el microservicio. Para ello, se debe ejecutar los siguientes comandos en la raíz del proyecto:

docker build -t mi-aplicacion:latest .

docker run -d -p 8080:8080 mi-aplicacion:latest

## Comunicación Asincrónica
Incluye un diagrama de casos de uso que se llama usesCases.png

## Requisitos

- Java 17 o superior
- Spring Boot 3.2.x o superior
- Apache Kafka
- Docker
