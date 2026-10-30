-- Dominio "Programa Academico": catalogos y subrecursos asociados al
-- programa academico existente (tabla "programas", creada en V1).

CREATE TABLE niveles_academicos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE modalidades_estudio (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE regimenes_academicos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE programa_sede (
    id BIGSERIAL PRIMARY KEY,
    id_programa BIGINT NOT NULL REFERENCES programas (id),
    id_sede BIGINT NOT NULL REFERENCES sedes (id),
    vigente BOOLEAN
);

CREATE TABLE grados_titulos (
    id BIGSERIAL PRIMARY KEY,
    id_programa BIGINT NOT NULL REFERENCES programas (id),
    grado VARCHAR(100),
    titulo VARCHAR(150)
);

CREATE TABLE resoluciones_programa (
    id BIGSERIAL PRIMARY KEY,
    id_programa BIGINT NOT NULL REFERENCES programas (id),
    numero_resolucion VARCHAR(100) NOT NULL,
    tipo_resolucion VARCHAR(100),
    fecha_emision DATE
);

CREATE TABLE menciones_especialidad (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    id_programa BIGINT NOT NULL REFERENCES programas (id),
    descripcion VARCHAR(255)
);

CREATE TABLE acreditaciones_programa (
    id BIGSERIAL PRIMARY KEY,
    id_programa BIGINT NOT NULL REFERENCES programas (id),
    entidad_acreditadora VARCHAR(150),
    fecha_acreditacion DATE,
    fecha_vigencia DATE
);
