-- Datos para el cierre: creditos del curso (llegan en seccion.abierta) y fecha de cierre
ALTER TABLE actas ADD COLUMN IF NOT EXISTS creditos INTEGER;
ALTER TABLE actas ADD COLUMN IF NOT EXISTS fecha_cierre TIMESTAMP;
