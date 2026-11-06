-- Valor temporal "Sin asignar" para Nivel académico mientras se integra
-- con el microservicio Currículo (lamb-curriculo-ms).
INSERT INTO niveles_academicos (id, nombre, descripcion)
VALUES (1, 'Sin asignar', 'Valor temporal mientras se integra con el microservicio Currículo.');

SELECT setval('niveles_academicos_id_seq', (SELECT COALESCE(MAX(id), 1) FROM niveles_academicos));
