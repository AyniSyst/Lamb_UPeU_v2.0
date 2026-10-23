-- Ciclo de vida de la seccion dentro de la saga planificacion -> evaluacion.
-- Las secciones que ya existian quedan ABIERTA.
ALTER TABLE secciones ADD COLUMN IF NOT EXISTS estado VARCHAR(30) NOT NULL DEFAULT 'ABIERTA';
ALTER TABLE secciones ADD COLUMN IF NOT EXISTS motivo VARCHAR(255);
