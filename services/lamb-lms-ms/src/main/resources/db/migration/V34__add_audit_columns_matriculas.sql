ALTER TABLE matriculas ADD COLUMN created_at TIMESTAMP;
ALTER TABLE matriculas ADD COLUMN updated_at TIMESTAMP;
ALTER TABLE matriculas ADD COLUMN created_by VARCHAR(36);

UPDATE matriculas SET created_at = fecha_creacion WHERE created_at IS NULL;

ALTER TABLE matriculas ALTER COLUMN created_at SET NOT NULL;
ALTER TABLE matriculas DROP COLUMN fecha_creacion;
