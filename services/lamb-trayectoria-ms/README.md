# lamb-trayectoria-ms

Trayectoria academica derivada: la base (trayectoria e historial) se arma con eventos y los 6 CRUD del documento v3 agregan el detalle de acompanamiento.

- `postulante.admitido` (admision-eventos) abre la trayectoria del estudiante.
- `acta.cerrada` (evaluacion-eventos) agrega el curso al historial y recalcula creditos aprobados y promedio ponderado.

Ambos consumidores son idempotentes: revision explicita + restriccion UNIQUE (`uk_trayectorias_persona`, `uk_historial_persona_grupo`).

## CRUD del documento v3
| Cuadro | Rutas |
|---|---|
| 1. Estudiante en el plan | `/api/v1/cambios-academicos` |
| 2. Avance y requisitos | `/api/v1/cumplimientos-requisito` |
| 3. Riesgo academico | `/api/v1/alertas-riesgo`, `/api/v1/niveles-riesgo`, `/api/v1/reglas-alerta`, `/api/v1/casos-seguimiento`, `/api/v1/controles-repitencia`, `/api/v1/planes-refuerzo` |
| 4. Tutoria y bienestar | `/api/v1/programas-tutoria`, `/api/v1/tutorias`, `/api/v1/sesiones-tutoria` |
| 5. Logros y evidencias | `/api/v1/reconocimientos` |
| 6. Cierre y egreso | `/api/v1/egresos`, `/api/v1/solicitudes-constancia`, `/api/v1/situaciones-cierre`, `/api/v1/informes-trayectoria` |

## Correr en local
```
docker compose -f compose-dev.yml up -d   # Postgres (15459)
./mvnw spring-boot:run                    # puerto 8089
```

## Endpoints (JWT obligatorio)
| Metodo | Ruta | Descripcion |
|---|---|---|
| GET | `/api/v1/trayectorias` | Todas las trayectorias |
| GET | `/api/v1/trayectorias/persona/{idPersona}` | Trayectoria + historial de una persona |
| GET | `/api/v1/trayectorias/me` | La del usuario del token (claim `idPersona`) |
| GET | `/api/v1/vistas/avance-curricular` | Avance curricular calculado |
| GET | `/api/v1/vistas/historial-academico` | Historial de cursos |
| GET | `/api/v1/vistas/promedio-ponderado` | Promedio ponderado por creditos |
| GET | `/api/v1/vistas/rendimiento-por-curso` | Rendimiento agrupado por curso |
| GET | `/api/v1/vistas/historial-estados` | Cambios de estado de la trayectoria |
