# lamb-curriculo-ms

Que se estudia: 47 CRUD (programa, plan de estudios, catalogo de cursos, silabos, organizacion curricular, perfiles, requisitos de egreso, equivalencias y gestion curricular). Este README detalla solo el plan de estudios; el resto esta en Swagger (`/swagger-ui.html`). Microservicio Spring Boot 4 / Java 21, registrado en Eureka y configurado via Config Server.

## Correr en local
```
docker compose -f ../../kafka/compose-dev.yml up -d   # Kafka (18092) + Kafka UI (18090)
docker compose -f compose-dev.yml up -d               # Postgres (15458)
./mvnw spring-boot:run
```
API: http://localhost:8088/api/v1
Swagger: http://localhost:8088/swagger-ui.html

## Endpoints
| Metodo | Ruta | Descripcion |
|---|---|---|
| GET | `/api/v1/planes-estudio?idPrograma=` | Lista planes (filtro opcional por programa) |
| GET | `/api/v1/planes-estudio/{id}` | Plan con sus cursos y prerrequisitos |
| GET | `/api/v1/planes-estudio/programa/{idPrograma}/vigente` | Plan VIGENTE del programa |
| POST | `/api/v1/planes-estudio` | Crea plan en BORRADOR |
| PUT / DELETE | `/api/v1/planes-estudio/{id}` | Edita / elimina un plan en BORRADOR |
| POST | `/api/v1/planes-estudio/{id}/cursos` | Agrega al plan un curso del catalogo `{idCursoCatalogo, ciclo, idTipoCurso, idAreaCurricular?}` |
| DELETE | `/api/v1/planes-estudio/{id}/cursos/{idPlanCurso}` | Quita curso del plan |
| POST | `/api/v1/planes-estudio/{id}/cursos/{idPlanCurso}/prerrequisitos` | Agrega prerrequisito |
| DELETE | `/api/v1/planes-estudio/{id}/cursos/{idPlanCurso}/prerrequisitos/{idPlanCursoRequisito}` | Quita prerrequisito |
| POST | `/api/v1/planes-estudio/{id}/publicar` | Publica el plan (BORRADOR -> VIGENTE) |

## Reglas
- Estados: `BORRADOR` -> `VIGENTE` -> `DEROGADO`. Solo un plan en BORRADOR se puede modificar (si no, 409).
- `version` es unica por programa. Solo hay un plan VIGENTE por programa: al publicar uno nuevo, el anterior pasa a DEROGADO.
- El curso se define una sola vez en el catalogo (`/api/v1/cursos-catalogo`) y no se repite dentro del plan. Un prerrequisito debe ser de un ciclo anterior.
- No se puede publicar un plan sin cursos ni quitar un curso que es prerrequisito de otro.

## Endpoint para otros servicios
| GET | `/api/v1/planes-estudio/cursos/{codigoCurso}/vigencia` | Si el curso esta en un plan VIGENTE (lo usa planificacion por Feign) |

Todas las rutas exigen JWT (resource server, claves en `lamb-auth-ms`).

## Eventos (Kafka)
| Topic | Key | tipoEvento | Cuando |
|---|---|---|---|
| `curriculo-eventos` | `planId` | `plan.publicado` | Al publicar un plan (despues del commit) |

```json
{"tipoEvento":"plan.publicado","planId":2,"idPrograma":10,"version":"2027","idPlanDerogado":1,"codigosCursos":["IS101"],"cursos":[{"codigo":"IS101","nombre":"Programacion I","creditos":4}],"origen":"lamb-curriculo-ms","timestamp":1792123866000}
```
Consumidor: `lamb-planificacion-ms` (actualiza su copia de cursos y revalida los grupos PENDIENTE_VALIDACION). Ver `docs/modulo02-u2-s6-s9.md`.
