--DB version 0.2
DROP DATABASE IF EXISTS biblioteca_generica;
CREATE DATABASE biblioteca_generica;
USE biblioteca_generica;

CREATE TABLE categoria(
    id INT AUTO_INCREMENT,
    categoria VARCHAR(30),

    PRIMARY KEY(id),
    UNIQUE(categoria)
);


CREATE TABLE libro(
    id INT AUTO_INCREMENT,
    titulo VARCHAR(30),
    fecha_publicacion DATE,
    autor VARCHAR(50),
    categoria_id_fk INT,
    numero_paginas INT,
    estado BIT DEFAULT 1,

    PRIMARY KEY(id),
    FOREIGN KEY(categoria_id_fk) REFERENCES categoria_libro(id)
);


CREATE TABLE categoria_libro(
    id INT AUTO_INCREMENT,
    libro_id_fk INT,
    categoria_id_fk INT,

    PRIMARY KEY(id),
    FOREIGN KEY(libro_id_fk) REFERENCES libro(id),
    FOREIGN KEY(categoria_id_fk) REFERENCES categoria(id)
);

CREATE TABLE tipo_usuario(
    id INT AUTO_INCREMENT,
    tipo_usuario VARCHAR(30),

    PRIMARY KEY(id)
);


CREATE TABLE usuario(
    id INT AUTO_INCREMENT,
    rut VARCHAR(13),
    pass VARCHAR(30),
    nombres VARCHAR(50),
    apellidos VARCHAR(50),
    correo VARCHAR(50),
    fecha_nacimiento DATE,
    fono INT,
    tipo_usuario_id_fk INT,

    PRIMARY KEY(id),
    FOREIGN KEY(tipo_usuario_id_fk) REFERENCES tipo_usuario(id),
    UNIQUE (rut),
    UNIQUE (correo)
);

CREATE TABLE estado_registro(
    id INT AUTO_INCREMENT,
    estado VARCHAR(20),

    PRIMARY KEY (id)
);

CREATE TABLE registro(
    id INT AUTO_INCREMENT,
    usuario_id_fk INT,
    libro_id_fk INT,
    fecha_solicitud DATE,
    fecha_entrega DATE,
    estado_registro_id_fk,

    PRIMARY KEY(id),
    FOREIGN KEY(usuario_id_fk) REFERENCES usuario(id),
    FOREIGN KEY(libro_id_fk) REFERENCES libro(id),
    FOREIGN KEY(estado_registro_id_fk) REFERENCES estado_registro(id)
);
