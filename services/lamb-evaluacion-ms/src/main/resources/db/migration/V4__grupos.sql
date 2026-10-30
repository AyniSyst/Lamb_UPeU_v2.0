-- La seccion de planificacion pasa a llamarse grupo (evento grupo.abierto)
ALTER TABLE actas RENAME COLUMN id_seccion TO id_grupo;
ALTER TABLE actas RENAME CONSTRAINT uk_actas_seccion TO uk_actas_grupo;
ALTER TABLE notas RENAME COLUMN id_seccion TO id_grupo;

-- El docente titular llega como id (idPersona), ya no como nombre libre
ALTER TABLE actas DROP COLUMN docente;
ALTER TABLE actas ADD COLUMN id_docente BIGINT;
