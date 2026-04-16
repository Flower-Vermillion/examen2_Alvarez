CREATE DATABASE IF NOT EXISTS examen2_alvarez;
USE examen2_alvarez;

-- ================================================================
-- ASEGURADO
-- ================================================================
CREATE TABLE asegurado (
    id VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- ================================================================
-- SEGURO (CLASE PADRE)
-- ================================================================
CREATE TABLE seguro (
    numero VARCHAR(50) PRIMARY KEY,
    fecha_exp VARCHAR(10) NOT NULL,
    estado TINYINT(1) NOT NULL,
    asegurado_id VARCHAR(50) NOT NULL,

    CONSTRAINT fk_seguro_asegurado
        FOREIGN KEY (asegurado_id)
        REFERENCES asegurado(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- ================================================================
-- SEGURO VIDA
-- ================================================================
CREATE TABLE seguro_de_vida (
    numero VARCHAR(50) PRIMARY KEY,
    beneficiario TINYINT(1) NOT NULL,

    CONSTRAINT fk_vida_seguro
        FOREIGN KEY (numero)
        REFERENCES seguro(numero)
        ON DELETE CASCADE
);

-- ================================================================
-- SEGURO VEHICULO
-- ================================================================
CREATE TABLE seguro_de_vehiculo (
    numero VARCHAR(50) PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,

    CONSTRAINT fk_vehiculo_seguro
        FOREIGN KEY (numero)
        REFERENCES seguro(numero)
        ON DELETE CASCADE
);