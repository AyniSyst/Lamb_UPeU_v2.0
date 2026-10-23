# lamb-trayectoria-ms

Trayectoria academica derivada (no es fuente de verdad): se arma solo con eventos.

- `postulante.admitido` (admision-eventos) abre la trayectoria del estudiante.
- `acta.cerrada` (evaluacion-eventos) agrega el curso al historial y recalcula creditos aprobados y promedio ponderado.

Ambos consumidores son idempotentes: revision explicita + restriccion UNIQUE (`uk_trayectorias_persona`, `uk_historial_persona_seccion`).

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
