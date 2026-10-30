ALTER TABLE pagos ADD COLUMN created_at TIMESTAMP;
ALTER TABLE pagos ADD COLUMN updated_at TIMESTAMP;
ALTER TABLE pagos ADD COLUMN created_by VARCHAR(36);

UPDATE pagos SET created_at = fecha_pago WHERE created_at IS NULL;

ALTER TABLE pagos ALTER COLUMN created_at SET NOT NULL;
