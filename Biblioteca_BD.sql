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

INSERT INTO categoria(categoria) VALUES ('Biografía'),
					('Cuento'),
					('Drama'),
					('Ficción'),
					('Horror'),
					('Novela'),
					('Suspenso'),
					('Sagrado');

					
CREATE TABLE libro(
    id INT AUTO_INCREMENT,
    titulo VARCHAR(50),
    fecha_publicacion DATE,
    autor VARCHAR(50),
    categoria_id_fk INT,
    numero_paginas INT,
    estado BIT DEFAULT 1,

    PRIMARY KEY(id),
    FOREIGN KEY(categoria_id_fk) REFERENCES categoria(id)
);

SET @biografia_id = (SELECT id FROM categoria WHERE categoria = 'Biografía');
SET @cuento_id    = (SELECT id FROM categoria WHERE categoria = 'Cuento');
SET @drama_id     = (SELECT id FROM categoria WHERE categoria = 'Drama');
SET @ficcion_id   = (SELECT id FROM categoria WHERE categoria = 'Ficción');
SET @horror_id    = (SELECT id FROM categoria WHERE categoria = 'Horror');
SET @novela_id    = (SELECT id FROM categoria WHERE categoria = 'Novela');
SET @suspenso_id  = (SELECT id FROM categoria WHERE categoria = 'Suspenso');
SET @sagrado_id   = (SELECT id FROM categoria WHERE categoria = 'Sagrado');

INSERT INTO libro(titulo,fecha_publicacion,autor,categoria_id_fk,numero_paginas) VALUES('Le Petit Prince','1943-09-06','Antoine de Saint-Exupéry',@drama_id,96),
										       ('The Pit and the Pendulum','1836-05-21','Edgar Allan Poe',@cuento_id,344),
										       ('Misery','1987-06-08','Stephen King',@horror_id,320),
										       ('The Da Vinci Code','2003-04-20','Dan Brown',@suspenso_id,656),
										       ('Метро 2033','2005-06-12','Dmitry Glukhovsky',@novela_id,348);


CREATE TABLE categoria_libro(
    id INT AUTO_INCREMENT,
    libro_id_fk INT,
    categoria_id_fk INT,

    PRIMARY KEY(id),
    FOREIGN KEY(libro_id_fk) REFERENCES libro(id),
    FOREIGN KEY(categoria_id_fk) REFERENCES categoria(id)
);

INSERT INTO categoria_libro(libro_id_fk,categoria_id_fk) VALUES(1,6),
				  			       (2,2),
							       (3,5),
							       (4,7),
							       (5,6);

CREATE TABLE tipo_usuario(
    id INT AUTO_INCREMENT,
    tipo_usuario VARCHAR(30),

    PRIMARY KEY(id)
);

INSERT INTO tipo_usuario(tipo_usuario) VALUES('Administrador'),
					     ('Usuario');

CREATE TABLE usuario(
    id INT AUTO_INCREMENT,
    rut VARCHAR(13),
    pass VARCHAR(64),
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

SET @admin_id = (SELECT id FROM tipo_usuario WHERE tipo_usuario = 'Administrador');
SET @user_id  = (SELECT id FROM tipo_usuario WHERE tipo_usuario = 'Usuario');

INSERT INTO usuario VALUES(NULL,'12123456-7',SHA2('111',0),'Eduardo Alberto','Pérez Figueroa','eapf@gmail.com','1985-10-22',982310581,@admin_id),
			  (NULL,'17152325-7',SHA2('222',0),'John Paul','Rey Casto','doggo@gmail.com','1992-12-11',923419730,@user_id);


CREATE TABLE estado_registro(
    id INT AUTO_INCREMENT,
    estado VARCHAR(30),

    PRIMARY KEY (id)
);


CREATE TABLE registro(
    id INT AUTO_INCREMENT,
    usuario_id_fk INT,
    libro_id_fk INT,
    fecha_solicitud DATE,
    fecha_entrega DATE,
    estado_registro_id_fk INT,

    PRIMARY KEY(id),
    FOREIGN KEY(usuario_id_fk) REFERENCES usuario(id),
    FOREIGN KEY(libro_id_fk) REFERENCES libro(id),
    FOREIGN KEY(estado_registro_id_fk) REFERENCES estado_registro(id)
);

CREATE TABLE historial_libro(
    id INT AUTO_INCREMENT,
    libro_id_fk INT,
    titulo VARCHAR(50),
    fecha_publicacion DATE,
    autor VARCHAR(50),
    categoria_id_fk INT,
    cantidad_paginas INT,
    fecha_cambio DATETIME,
    
    PRIMARY KEY(id),
    FOREIGN KEY(libro_id_fk) REFERENCES libro(id),
    FOREIGN KEY(categoria_id_fk) REFERENCES categoria(id)
);
-- Trigger para agregar un historial de libros al hacer un update
DELIMITER //
CREATE TRIGGER trigger_historial_libro BEFORE UPDATE ON libro
    FOR EACH ROW
BEGIN
    INSERT INTO historial_libro VALUES(NULL,OLD.id,OLD.titulo,OLD.fecha_publicacion,OLD.autor,OLD.categoria_id_fk,OLD.numero_paginas,NOW());
END //
DELIMITER ;

-- Procedimiento para limitar el pedido de libros
DELIMITER //
CREATE PROCEDURE agregarRegistro(IN _id_usuario INT,IN _id_libro INT,IN _fecha_solicitud VARCHAR(12),IN _fecha_limite VARCHAR(12))
BEGIN

    DECLARE limite_usuario TINYINT(1);
    DECLARE libro_disponible TINYINT(1);

    SET limite_usuario = (SELECT COUNT(*) FROM registro WHERE usuario_id_fk = _id_usuario AND (estado_registro_id_fk = 2 OR estado_registro_id_fk = 3));
    SET libro_disponible = (SELECT COUNT(*) FROM libro WHERE id = _id_libro AND estado = 1);

    IF limite_usuario >= 2 THEN
            SELECT 'Este usuario ya tiene 2 libros en su poder' AS 'info';
    ELSE
        IF libro_disponible = 1 THEN 
            INSERT INTO registro VALUES (NULL,_id_usuario,_id_libro,_fecha_solicitud,_fecha_limite,1);
        ELSE
            SELECT 'Este libro no esta disponible' AS 'info';    
        END IF;
    END IF;
END //
DELIMITER ;

-- Procedimiento para agregar un libro y añadirlo a la tabla categoria_libro
DELIMITER //
CREATE PROCEDURE agregar_libro(IN _titulo VARCHAR(50),IN _fechaPublicacion VARCHAR(12),IN _autor VARCHAR(50),IN _categoria VARCHAR(20),IN _paginas INT)
BEGIN
    DECLARE existe_libro TINYINT(1);
    DECLARE existe_categoria TINYINT(1);
    DECLARE id_libro INT;
    DECLARE id_categoria INT;

    SET existe_libro = (SELECT COUNT(*) FROM libro WHERE titulo = _titulo);
    SET existe_categoria = (SELECT COUNT(*) FROM categoria WHERE categoria = _categoria);
    
    IF existe_categoria = 0 THEN 
        INSERT INTO categoria VALUES (NULL,_categoria);
	SELECT 'Se agregó la categoría' AS 'info';
    END IF;

    IF existe_libro = 0 THEN
	SET id_categoria = (SELECT id FROM categoria WHERE categoria = _categoria);
        INSERT INTO libro VALUES (NULL,_titulo,_fechaPublicacion,_autor,id_categoria,_paginas,1);
	SELECT 'Se agregó el libro exitosamente' AS 'info';
    END IF;

    IF existe_libro = 0 OR existe_categoria = 0 THEN
        SET id_libro = (SELECT id FROM libro WHERE titulo = _titulo);
        SET id_categoria = (SELECT id FROM categoria WHERE categoria = _categoria);
        INSERT INTO categoria_libro VALUES (NULL,id_libro,id_categoria);
    END IF;
END //
DELIMITER ;

-- CALL para probar
CALL agregar_libro('The Call of Cthulhu','1928-02-05','H.P. Lovecraft','Cuento',54);