-- Dominio "Periodo Academico": anios, tipos, periodos, estados y
-- vigencias asociadas a programa/sede.

CREATE TABLE anios_academicos (
    id BIGSERIAL PRIMARY KEY,
    anio INTEGER NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE tipos_periodo (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE periodos_academicos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    id_anio_academico BIGINT NOT NULL REFERENCES anios_academicos (id),
    id_tipo_periodo BIGINT NOT NULL REFERENCES tipos_periodo (id),
    fecha_inicio DATE,
    fecha_fin DATE
);

CREATE TABLE estados_periodo (
    id BIGSERIAL PRIMARY KEY,
    id_periodo_academico BIGINT NOT NULL REFERENCES periodos_academicos (id),
    estado VARCHAR(100) NOT NULL,
    fecha_cambio DATE
);

CREATE TABLE periodo_programa_sede (
    id BIGSERIAL PRIMARY KEY,
    id_periodo_academico BIGINT NOT NULL REFERENCES periodos_academicos (id),
    id_programa BIGINT NOT NULL REFERENCES programas (id),
    id_sede BIGINT NOT NULL REFERENCES sedes (id),
    vigente BOOLEAN
);

CREATE TABLE ampliaciones_cierre_periodo (
    id BIGSERIAL PRIMARY KEY,
    id_periodo_academico BIGINT NOT NULL REFERENCES periodos_academicos (id),
    tipo_accion VARCHAR(100) NOT NULL,
    numero_resolucion VARCHAR(100),
    fecha_accion DATE,
    descripcion VARCHAR(255)
);
