-- Un grupo puede tener varias sesiones el mismo dia (manana/tarde, teoria/practica):
-- se permite registrar mas de una asistencia por persona y fecha, y mas de una
-- evaluacion del mismo componente en una misma fecha.
ALTER TABLE registros_asistencia DROP CONSTRAINT IF EXISTS uk_registros_asistencia_id_grupo_id_persona_fecha;
ALTER TABLE evaluaciones_programadas DROP CONSTRAINT IF EXISTS uk_evaluaciones_programadas_id_grupo_componente_fecha;

CREATE INDEX IF NOT EXISTS ix_registros_asistencia_grupo_persona_fecha ON registros_asistencia (id_grupo, id_persona, fecha);
CREATE INDEX IF NOT EXISTS ix_evaluaciones_programadas_grupo_componente_fecha ON evaluaciones_programadas (id_grupo, id_componente, fecha);
