-- Renombre seccion -> grupo (coherencia con planificacion y evaluacion v3)
ALTER TABLE historial_cursos RENAME COLUMN id_seccion TO id_grupo;
ALTER TABLE historial_cursos RENAME CONSTRAINT uk_historial_persona_seccion TO uk_historial_persona_grupo;
