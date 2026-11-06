ALTER TABLE programas
    ADD COLUMN id_nivel_academico BIGINT REFERENCES niveles_academicos(id);
