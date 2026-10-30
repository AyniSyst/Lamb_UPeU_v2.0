# lamb-planificacion-ms

Que se dicta en el periodo: 35 CRUD (periodos, oferta, grupos, ambientes, horarios, carga docente, silabos, solicitudes y equivalencias). Es el **microservicio transaccional** del modulo: `POST /api/v1/grupos` abre el grupo (cabecera) con sus horarios (Grupo x Ambiente) y docentes (Grupo x Docente) en una sola transaccion; ver `docs/PLANIFICACION-TRANSACCIONAL.md`. La lista completa de recursos esta en Swagger (`/swagger-ui.html`). Microservicio Spring Boot 4 / Java 21, registrado en Eureka y configurado via Config Server.

## Correr en local
```
docker compose -f compose-dev.yml up -d
./mvnw spring-boot:run
```
API: http://localhost:8083/api/v1
Swagger: http://localhost:8083/swagger-ui.html
