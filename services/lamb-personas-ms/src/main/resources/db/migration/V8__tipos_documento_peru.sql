-- Documentos de identidad habituales en Peru (mismo catalogo que TipoDocumentoPeru)
INSERT INTO tipos_documento_identidad (nombre, descripcion) VALUES
    ('DNI', 'Documento Nacional de Identidad (RENIEC) - 8 digitos'),
    ('CE', 'Carne de Extranjeria (Migraciones) - 9 a 12 caracteres'),
    ('PASAPORTE', 'Pasaporte - 6 a 12 caracteres'),
    ('RUC', 'Registro Unico de Contribuyentes (SUNAT) - 11 digitos'),
    ('PTP', 'Permiso Temporal de Permanencia - 9 a 15 caracteres'),
    ('CPP', 'Carne de Permiso Temporal de Permanencia - 9 a 15 caracteres'),
    ('CDI', 'Cedula de Identidad Diplomatica - 6 a 15 digitos'),
    ('PARTIDA', 'Partida de Nacimiento - 6 a 15 caracteres')
ON CONFLICT (nombre) DO NOTHING;
