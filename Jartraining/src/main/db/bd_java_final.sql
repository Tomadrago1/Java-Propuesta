DROP DATABASE IF EXISTS jartraining;

CREATE DATABASE jartraining;

USE jartraining;

CREATE TABLE USUARIO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL,
    estado BOOLEAN NOT NULL,
    nombre_usuario VARCHAR(255) NOT NULL,
    tipo_usu INT NOT NULL,
    profesion VARCHAR(100)
);

CREATE TABLE RECETA (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_profesional INT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    nivel_dificultad ENUM('BAJO', 'MEDIO', 'ALTO') NOT NULL,
    FOREIGN KEY (id_profesional) REFERENCES usuario(id) ON DELETE SET NULL
);

CREATE TABLE HISTORIAL_MEDIDA (
    id_usuario INT,
    fecha DATE,
    peso DECIMAL(5, 2),
    altura DECIMAL(5, 2),
    PRIMARY KEY (id_usuario, fecha),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE INGREDIENTE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE INGREDIENTE_RECETA (
    id_ingrediente INT,
    id_receta INT,
    cantidad_porcion DECIMAL(5, 2),
    unidad_medida VARCHAR(50),
    PRIMARY KEY (id_ingrediente, id_receta),
    FOREIGN KEY (id_receta) REFERENCES RECETA(id) ON DELETE CASCADE,
    FOREIGN KEY (id_ingrediente) REFERENCES INGREDIENTE(id) ON DELETE CASCADE
);

CREATE TABLE NUTRIENTE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE RUTINA (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE EJERCICIO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE ENTRENAMIENTO (
    id_usuario INT,
    id_rutina INT,
    id_ejercicio INT,
    fecha_entrenamiento DATE,
    serie INT,
    repeticion INT,
    orden INT,
    tiempo TIME,
    peso DECIMAL(5, 2),
    PRIMARY KEY (id_rutina, id_ejercicio, fecha_entrenamiento),
    FOREIGN KEY (id_rutina) REFERENCES RUTINA(id) ON DELETE CASCADE,
    FOREIGN KEY (id_ejercicio) REFERENCES EJERCICIO(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE NECESIDAD (
    id_usuario INT,
    id_nutriente INT,
    id_profesional INT,
    fecha DATE,
    cantidad_min DECIMAL(5, 2),
    cantidad_max DECIMAL(5, 2),
    PRIMARY KEY (id_usuario, id_nutriente, fecha),
    FOREIGN KEY (id_profesional) REFERENCES usuario(id) ON DELETE SET NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_nutriente) REFERENCES NUTRIENTE(id) ON DELETE CASCADE
);

CREATE TABLE RUTINA_CLIENTE (
    id_usuario INT,
    id_rutina INT,
    PRIMARY KEY (id_usuario, id_rutina),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_rutina) REFERENCES RUTINA(id) ON DELETE CASCADE
);

CREATE TABLE CONSULTA (
    id_cliente INT,
    id_profesional INT NOT NULL,
    fecha_consulta DATE,
    desc_resultados VARCHAR(255),
    PRIMARY KEY (id_cliente, id_profesional, fecha_consulta),
    FOREIGN KEY (id_cliente) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_profesional) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE RUTINA_EJERCICIO (
    id_rutina INT,
    id_ejercicio INT,
    series_aproximadas INT,
    repeticiones_aproximadas INT,
    tiempo_aproximado VARCHAR(25) NULL DEFAULT NULL,
    PRIMARY KEY (id_rutina, id_ejercicio),
    FOREIGN KEY (id_rutina) REFERENCES RUTINA(id) ON DELETE CASCADE,
    FOREIGN KEY (id_ejercicio) REFERENCES EJERCICIO(id) ON DELETE CASCADE
);

CREATE TABLE NUTRIENTE_INGREDIENTE (
    id_nutriente INT,
    id_ingrediente INT,
    cantidad DECIMAL(5 , 2 ),
    unidad_medida VARCHAR(50),
    PRIMARY KEY (id_nutriente , id_ingrediente),
    FOREIGN KEY (id_ingrediente) REFERENCES INGREDIENTE(id) ON DELETE CASCADE,
    FOREIGN KEY (id_nutriente) REFERENCES NUTRIENTE(id) ON DELETE CASCADE
);

CREATE TABLE HORARIOS (
    fecha_hora_desde DATETIME,
    fecha_hora_hasta DATETIME,
    id_profesional INT NOT NULL,
    PRIMARY KEY (id_profesional, fecha_hora_desde),
    FOREIGN KEY (id_profesional) REFERENCES usuario(id) ON DELETE CASCADE
);

INSERT INTO usuario (id, nombre, apellido, email, contrasena, estado, nombre_usuario, tipo_usu)
VALUES (1, 'Admin', 'Admin', 't@t', 'a', 1, 'admin', 1);