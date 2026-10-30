CREATE TABLE calendarios_academicos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    id_periodo_academico BIGINT NOT NULL,
    descripcion VARCHAR(255),
    CONSTRAINT fk_calendario_periodo_academico FOREIGN KEY (id_periodo_academico) REFERENCES periodos_academicos (id)
);

CREATE TABLE tipos_evento (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE eventos_calendario (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    id_calendario_academico BIGINT NOT NULL,
    id_tipo_evento BIGINT NOT NULL,
    fecha_inicio DATE,
    fecha_fin DATE,
    CONSTRAINT fk_evento_calendario_academico FOREIGN KEY (id_calendario_academico) REFERENCES calendarios_academicos (id),
    CONSTRAINT fk_evento_tipo_evento FOREIGN KEY (id_tipo_evento) REFERENCES tipos_evento (id)
);

CREATE TABLE feriados_no_laborables (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    fecha DATE NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE calendario_facultad_escuela (
    id BIGSERIAL PRIMARY KEY,
    id_calendario_academico BIGINT NOT NULL,
    id_facultad BIGINT,
    id_escuela BIGINT,
    CONSTRAINT fk_cfe_calendario_academico FOREIGN KEY (id_calendario_academico) REFERENCES calendarios_academicos (id),
    CONSTRAINT fk_cfe_facultad FOREIGN KEY (id_facultad) REFERENCES facultades (id),
    CONSTRAINT fk_cfe_escuela FOREIGN KEY (id_escuela) REFERENCES escuelas_profesionales (id)
);

CREATE TABLE reprogramaciones_evento (
    id BIGSERIAL PRIMARY KEY,
    id_evento_calendario BIGINT NOT NULL,
    numero_resolucion VARCHAR(100),
    fecha_inicio_nueva DATE,
    fecha_fin_nueva DATE,
    motivo VARCHAR(255),
    CONSTRAINT fk_reprogramacion_evento FOREIGN KEY (id_evento_calendario) REFERENCES eventos_calendario (id)
);

CREATE TABLE publicaciones_calendario (
    id BIGSERIAL PRIMARY KEY,
    id_calendario_academico BIGINT NOT NULL,
    fecha_publicacion TIMESTAMP,
    url_documento VARCHAR(255),
    CONSTRAINT fk_publicacion_calendario_academico FOREIGN KEY (id_calendario_academico) REFERENCES calendarios_academicos (id)
);

CREATE TABLE recordatorios_calendario (
    id BIGSERIAL PRIMARY KEY,
    id_evento_calendario BIGINT NOT NULL,
    mensaje VARCHAR(255),
    fecha_envio TIMESTAMP,
    CONSTRAINT fk_recordatorio_evento FOREIGN KEY (id_evento_calendario) REFERENCES eventos_calendario (id)
);
