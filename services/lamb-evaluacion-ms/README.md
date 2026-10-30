# lamb-evaluacion-ms

Como se evalua: 20 CRUD (componentes, pesos, escala, rubricas, notas, asistencia, actas, solicitudes de apertura y evaluacion docente; el 18 se retiro) y vistas calculadas de solo lectura en `/api/v1/vistas/...` (acta de notas, nota final, estadistica del grupo, cuadro de meritos, resultado de la evaluacion docente). La lista completa esta en Swagger. Microservicio Spring Boot 4 / Java 21, registrado en Eureka y configurado via Config Server.

## Correr en local
```
docker compose -f compose-dev.yml up -d
./mvnw spring-boot:run
```
API: http://localhost:8086/api/v1
Swagger: http://localhost:8086/swagger-ui.html
