---
title: Lamb UPeU 2.0 — Producto de Unidad 2
---

# Lamb UPeU 2.0 — Producto de Unidad 2

Esta página adapta la **plantilla-ejemplo del producto de Unidad 2** del curso de Desarrollo de Aplicaciones Distribuidas al dominio de **Lamb UPeU 2.0** (plataforma universitaria integrada: admisión, currículo, matrícula, evaluación, trayectoria, finanzas, LMS, etc.). La estructura (comunicación resiliente, seguridad distribuida, mensajería asíncrona, consistencia distribuida, observabilidad, integración frontend) se mantiene exactamente igual a la plantilla; el contenido se reemplaza con el dominio académico de Lamb.

## Producto

**Sistema distribuido seguro, resiliente, consistente, observable e integrado con cliente frontend.**

Lamb UPeU 2.0 implementa comunicación síncrona resiliente, seguridad distribuida, mensajería asíncrona, consistencia eventual en procesos de negocio (matrícula, cierre de actas, cobros), observabilidad operacional e integración frontend mediante el punto único de acceso (`gateway`) — fortaleciendo el sistema base de Unidad 1 con los atributos de calidad que un sistema distribuido real necesita para operar en producción universitaria.

## 1. Alcance de capacidades por sesión

### Tabla 1. De la sesión a la capacidad del sistema

| Sesión | Capacidad que agrega | Ejemplo Lamb UPeU 2.0 |
|---|---|---|
| **S6** | Comunicación síncrona resiliente entre servicios (respuesta controlada ante fallos). | `lamb-matricula-ms` llama a `lamb-curriculo-ms` (validar prerrequisitos) y a `lamb-planificacion-ms` (cupo y horario) vía Feign con timeout, reintentos y fallback de Resilience4j ante caída de instancia. |
| **S7 / S07b** | Seguridad distribuida y protección de rutas. | JWT propio (S7) reemplazado por **Keycloak** (S07b, prerrequisito obligatorio de S11): realm `lamb` con roles `ADMIN`, `COORDINADOR`, `DOCENTE`, `ESTUDIANTE`. El token se valida en **cada microservicio** (SecurityConfig propio en admision, curriculo, planificacion, matricula, evaluacion, finanzas, personas, institucional), no solo en el Gateway. |
| **S8** | Mensajería asíncrona entre servicios desacoplados. | Eventos de negocio publicados y consumidos vía **Kafka**, sin llamada síncrona directa: `matricula.confirmada` (matricula → finanzas), `acta.cerrada` (evaluacion → trayectoria), `plan.publicado` (curriculo → planificacion), `grupo.abierto` (planificacion → evaluacion/matricula), `postulante.admitido` (admision → trayectoria/personas). |
| **S9** | Consistencia eventual, compensación e idempotencia en procesos de negocio. | El flujo **matrícula → generación de cargo** tolera fallos parciales: si `lamb-finanzas-ms` cae al consumir `matricula.confirmada`, el evento se reprocesa sin duplicar el `Pago` (clave idempotente por `idMatricula`). Si una matrícula se anula (`matricula.anulada`), se compensa liberando cupo en planificacion y cancelando el cargo en finanzas. |
| **S10** | Observabilidad operacional: logs, health, métricas y paneles de diagnóstico. | Stack `obs/compose-dev.yml`: **Prometheus** scrapea métricas de los 11 microservicios + Gateway + Keycloak; **Loki + Promtail** centralizan logs con trace-id correlacionado; **Grafana** expone dashboards de salud y latencia por servicio. Health checks `/actuator/health` en cada microservicio. |
| **S11** | Integración con cliente frontend mediante el Gateway. | `lamb-ng` (Angular) consume la API **únicamente a través de `gateway`** (puerto único); login SSO contra Keycloak con **PKCE** y cliente `lamb-frontend`; rutas protegidas por rol (ESTUDIANTE accede a matrícula/notas, DOCENTE a registro de notas, ADMIN a toda la gestión). |
| **S12** (esta evaluación) | Ensambla todo lo anterior en un solo sistema y lo sustenta. | El sistema completo Lamb UPeU 2.0 Unidad 2 + sección 4 de la guía de evaluación. |

Lo que sustentamos en S12 es **nuestro propio sistema**: las capacidades que construimos en S6–S11 sobre el dominio universitario Lamb — no el de pagatu.

## 2. Rúbrica de Evaluación

### Tabla 2. Rúbrica de evaluación de la Unidad 2

| Criterio | Peso | CE / Nivel | A (20 pts) | B (15 pts) | C (10 pts) | D (5 pts) |
|---|---|---|---|---|---|---|
| **1. Comunicación entre servicios con respuesta controlada ante fallos** | 12% | CE023-N3 | Timeout, reintentos y fallback verificados **en vivo** en el flujo matrícula↔currículo↔planificación ante una caída provocada de una instancia Feign. | Mecanismo presente, verificado parcialmente. | Mecanismo declarado sin verificación clara de fallos. | Falta o no funciona. |
| **2. Seguridad distribuida y protección de rutas** | 16% | CE023-N3 | Autenticación y autorización verificadas en los **11 microservicios propios** (SecurityConfig con Keycloak), no solo en el Gateway. Prueba en vivo con token sin rol → 403. | Seguridad verificada en la mayoría de los servicios. | Seguridad solo en Gateway, pasa libre a los ms. | Falta o inconsistente. |
| **3. Mensajería asíncrona entre servicios desacoplados** | 16% | CE023-N3 | Eventos `matricula.confirmada` y `acta.cerrada` publicados y consumidos, con `lamb-matricula-ms` / `lamb-finanzas-ms` / `lamb-evaluacion-ms` / `lamb-trayectoria-ms` verificados de forma independiente (no acoplados). | Mensajería funcional, con acoplamiento parcial (ej. consumer sincroniza con publisher). | Mensajería declarada sin verificación clara de desacoplo. | Falta o Kafka no corre. |
| **4. Consistencia eventual, compensación e idempotencia en procesos de negocio** | 16% | CE023-N3 | Flujo **matrícula → pago** probado con fallo parcial (finanzas down) y reprocesamiento del mismo evento, **sin inconsistencia ni duplicidad** (misma `idMatricula` ⇒ un solo `Pago`). | Consistencia verificada parcialmente (uno de los dos casos: compensación o idempotencia). | Consistencia mencionada sin pruebas. | Falta. |
| **5. Logs, health, métricas y paneles de diagnóstico** | 12% | CE023-N3 | Grafana operativo con métricas (Prometheus), logs correlacionados por trace-id (Loki) y health checks de los 11 ms + Gateway + Keycloak. | Panel operativo, con correlación parcial entre servicios. | Métricas o logs presentes sin correlación. | Falta observabilidad. |
| **6. Cliente frontend integrado mediante Gateway** | 8% | CE023-N3 | `lamb-ng` (Angular) consume la API completa **solo a través del Gateway**, con sesión SSO contra Keycloak y rutas protegidas según rol (ESTUDIANTE/DOCENTE/COORDINADOR/ADMIN). | Cliente funcional, con alguna llamada fuera del Gateway o protección parcial. | Cliente parcial, invoca directo a los ms. | Falta cliente integrado. |
| **7. Sustentación** | 20% | CG | Sustenta con **claridad y profesionalismo** su aporte individual, respondiendo con precisión las preguntas del jurado. | Sustenta con solvencia, con detalles menores en claridad, orden o precisión. | Sustenta con dificultades de claridad, orden o precisión. | Insuficiente. |

**Nota final** = suma de ( `Peso × Puntos de la calificación obtenida` ) / 100 × 20.

`CE023-N3` = Nivel 3 de la competencia CE023 (Programación). `CG` = Competencia General "Carácter y Aprendizaje Autónomo" del sílabo — no es CE023: los criterios 1–6 ya son la evidencia técnica, incluida su verificación en vivo; el criterio 7 verifica aporte individual y comunicación.

### Tabla 3. Subaspectos de la sustentación (Unidad 2)

El criterio 7 se evalúa con los mismos 6 subaspectos de la sustentación integral del Proyecto Sello.

| Subaspecto | Qué observa en Unidad 2 para Lamb |
|---|---|
| **1. Aporte individual** | Cada integrante demuestra lo que construyó en Unidad 2 (microservicios asignados, resiliencia, seguridad, eventos, observabilidad o frontend). |
| **2. Comunicación y orden** | Claridad, estructura, tiempo y lenguaje técnico durante la presentación. |
| **3. Presentación personal y actitud** | Puntualidad, vestimenta limpia y adecuada, higiene, cabello ordenado, actitud profesional, respeto, honestidad y coherencia con los valores y principios cristianos de la institución (UPeU). |
| **4. Repositorio y estándares** | Topics académicos vigentes desde S2, organización del repo `AyniSyst/Lamb_UPeU_v2.0`, commits significativos y reproducibilidad del sistema (`docker compose up`). |
| **5. MkDocs o equivalente** | Documentación de Unidad 2 publicada, navegable y alineada con `u2-producto.md`: esta misma página en `https://aynisyst.github.io/Lamb_UPeU_v2.0/u2-producto/`. |
| **6. Pitch/demo ejecutiva** | Introducción breve de cómo evolucionó Lamb desde Unidad 1 (sistema base con los 11 microservicios y 181 controllers REST) hacia Unidad 2 (resiliente, seguro con Keycloak, asíncrono con Kafka, observable con Prometheus/Loki/Grafana, con cliente Angular). No reemplaza la demo técnica de S12, la precede. |

**Prompt para usar la rúbrica con IA:**

```
Evalúa la sustentación y el producto Lamb UPeU 2.0 (adaptado al dominio universitario)
usando la rúbrica de esta sección.
Para cada criterio selecciona la calificación obtenida: A=20, B=15, C=10, D=5.
Justifica brevemente cada nivel con evidencia concreta (fallo provocado en Feign,
evento Kafka publicado/consumido, panel Grafana, cliente Angular en vivo).
Para el criterio 7, verifica explícitamente los 6 subaspectos de la Tabla 3
antes de asignar el nivel.
Calcula la nota final con la fórmula: suma de (Peso × Puntos de la
calificación obtenida) / 100 × 20.
Indica 2 fortalezas y 2 recomendaciones para lo que sigue en Unidad III.
```

## 3. Trazabilidad y procedencia de la rúbrica

Los primeros seis criterios son **cita literal** de los criterios de evaluación del producto de la Unidad II en el sílabo de Desarrollo de Aplicaciones Distribuidas; el séptimo (Sustentación) corresponde a la sustentación exigida por el mismo sílabo (sesión 12, actividad 2).

**Con la malla curricular:** estos criterios profundizan la porción de **plataforma distribuida del Nivel 3 de CE023** (Programación) que Unidad 1 dejó como base — la integración completa de las cinco plataformas (consola, escritorio, web, distribuido, móvil) ocurre recién en PI1, Ciclo 8. El criterio 7 (Sustentación) es transversal y no forma parte de la definición de la competencia.

## 4. Evidencia técnica en el repositorio de Lamb

Mapa rápido entre criterios de la rúbrica y evidencia verificable en `AyniSyst/Lamb_UPeU_v2.0`:

| Criterio | Evidencia en el repo |
|---|---|
| 1. Resiliencia Feign | Config Resilience4j en `lamb-matricula-ms`, `lamb-admision-ms`, `lamb-curriculo-ms`, `lamb-evaluacion-ms`, `lamb-planificacion-ms` |
| 2. Seguridad por servicio | `SecurityConfig.java` en los 8+ microservicios implementados; realm Keycloak importable en `keycloak/` |
| 3. Mensajería Kafka | `kafka/` compose; eventos `matricula.confirmada`, `acta.cerrada`, `plan.publicado`, `grupo.abierto`, `postulante.admitido` |
| 4. Consistencia / idempotencia | Generación idempotente de `Pago` por `idMatricula` en `lamb-finanzas-ms`; patrón saga en matrícula↔finanzas |
| 5. Observabilidad | `obs/compose-dev.yml`: Prometheus + Loki + Promtail + Grafana con targets de los 9 ms + Gateway + Keycloak |
| 6. Frontend | `lamb-ng` (Angular) consumiendo exclusivamente `gateway`; login PKCE contra Keycloak |

---

[← Volver al Inicio](index.md)
