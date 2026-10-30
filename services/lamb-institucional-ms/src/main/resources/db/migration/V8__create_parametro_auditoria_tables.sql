CREATE TABLE parametros_institucionales (
    id BIGSERIAL PRIMARY KEY,
    clave VARCHAR(100) NOT NULL,
    valor VARCHAR(255),
    descripcion VARCHAR(255)
);

CREATE TABLE parametros_sede_escuela (
    id BIGSERIAL PRIMARY KEY,
    clave VARCHAR(100) NOT NULL,
    valor VARCHAR(255),
    id_sede BIGINT,
    id_escuela BIGINT
);

CREATE TABLE bitacoras_cambios_estructura (
    id BIGSERIAL PRIMARY KEY,
    entidad_afectada VARCHAR(100) NOT NULL,
    id_entidad_afectada BIGINT,
    accion VARCHAR(100) NOT NULL,
    detalle VARCHAR(255),
    fecha_cambio TIMESTAMP
);
