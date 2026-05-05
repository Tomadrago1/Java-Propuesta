-- =============================================================
--  SCRIPT DE INICIALIZACIÓN - Base de datos: jartraining
--  Proyecto: Java-Propuesta (Plataforma de Nutrición y Entrenamiento)
--  Generado a partir de las entidades y DAOs del proyecto
-- =============================================================

CREATE DATABASE IF NOT EXISTS jartraining
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE jartraining;

-- -------------------------------------------------------------
-- 1. PROFESION
--    Tabla de referencia para tipos de profesión (nutricionista,
--    entrenador, etc.). Debe crearse antes que Usuario.
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS profesion (
    id       INT          NOT NULL AUTO_INCREMENT,
    nombre   VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 2. USUARIO
--    Tabla central de usuarios (clientes y profesionales).
--    tipo_usu: 1 = cliente, 2 = profesional
--    id_profesion es NULL para clientes
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
    id             INT          NOT NULL AUTO_INCREMENT,
    nombre         VARCHAR(100) NOT NULL,
    apellido       VARCHAR(100) NOT NULL,
    email          VARCHAR(150) NOT NULL UNIQUE,
    nombre_usuario VARCHAR(100) NOT NULL UNIQUE,
    contrasena     VARCHAR(255) NOT NULL,
    tipo_usu       TINYINT      NOT NULL DEFAULT 1,
    estado         TINYINT(1)   NOT NULL DEFAULT 1,
    id_profesion   INT          NULL,
    profesion      VARCHAR(100) NULL,        -- cache desnormalizado usado en JOINs del DaoUsuario
    PRIMARY KEY (id),
    CONSTRAINT fk_usuario_profesion FOREIGN KEY (id_profesion)
        REFERENCES profesion (id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 3. HORARIO
--    Disponibilidad horaria de un profesional.
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS horario (
    id_horario       INT      NOT NULL AUTO_INCREMENT,
    id_profesional   INT      NOT NULL,
    fecha_hora_desde DATETIME NOT NULL,
    fecha_hora_hasta DATETIME NOT NULL,
    PRIMARY KEY (id_horario),
    CONSTRAINT fk_horario_profesional FOREIGN KEY (id_profesional)
        REFERENCES usuario (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 4. CONSULTA
--    Registro de consultas entre cliente y profesional.
--    PK compuesta: (id_cliente, id_profesional, fecha_consulta)
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS consulta (
    id_cliente      INT          NOT NULL,
    id_profesional  INT          NOT NULL,
    fecha_consulta  DATETIME     NOT NULL,
    desc_resultados TEXT         NULL,
    estado          VARCHAR(50)  NOT NULL DEFAULT 'Pendiente',
    PRIMARY KEY (id_cliente, id_profesional, fecha_consulta),
    CONSTRAINT fk_consulta_cliente     FOREIGN KEY (id_cliente)
        REFERENCES usuario (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_consulta_profesional FOREIGN KEY (id_profesional)
        REFERENCES usuario (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 5. MEDIDA_USUARIO
--    Medidas corporales (peso / altura) por usuario y fecha.
--    PK compuesta: (id_usuario, fecha)
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS medida_usuario (
    id_usuario INT    NOT NULL,
    fecha      DATE   NOT NULL,
    peso       DOUBLE NOT NULL,
    altura     DOUBLE NOT NULL,
    PRIMARY KEY (id_usuario, fecha),
    CONSTRAINT fk_medida_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 6. NUTRIENTE
--    Catálogo de nutrientes (proteínas, carbohidratos, etc.)
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS nutriente (
    id          INT          NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(100) NOT NULL,
    descripcion TEXT         NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 7. NECESIDAD
--    Requerimientos nutricionales diarios de un cliente,
--    asignados por un profesional.
--    PK compuesta: (id_usuario, id_nutriente, fecha)
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS necesidad (
    id_usuario     INT    NOT NULL,
    id_nutriente   INT    NOT NULL,
    id_profesional INT    NOT NULL,
    fecha          DATE   NOT NULL,
    cantidad_min   DOUBLE NOT NULL,
    cantidad_max   DOUBLE NOT NULL,
    PRIMARY KEY (id_usuario, id_nutriente, fecha),
    CONSTRAINT fk_necesidad_usuario     FOREIGN KEY (id_usuario)
        REFERENCES usuario (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_necesidad_nutriente   FOREIGN KEY (id_nutriente)
        REFERENCES nutriente (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_necesidad_profesional FOREIGN KEY (id_profesional)
        REFERENCES usuario (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 8. INGREDIENTE
--    Catálogo de ingredientes culinarios.
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ingrediente (
    id          INT          NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(150) NOT NULL,
    descripcion TEXT         NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 9. NUTRIENTE_INGREDIENTE
--    Cantidad de cada nutriente que contiene un ingrediente
--    (por 100 g / unidad según unidad_medida).
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS nutriente_ingrediente (
    id_nutriente   INT          NOT NULL,
    id_ingrediente INT          NOT NULL,
    cantidad       DOUBLE       NOT NULL,
    unidad_medida  VARCHAR(50)  NOT NULL,
    PRIMARY KEY (id_nutriente, id_ingrediente),
    CONSTRAINT fk_ni_nutriente   FOREIGN KEY (id_nutriente)
        REFERENCES nutriente (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ni_ingrediente FOREIGN KEY (id_ingrediente)
        REFERENCES ingrediente (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 10. RECETA
--    Recetas creadas por profesionales.
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS receta (
    id               INT          NOT NULL AUTO_INCREMENT,
    nombre           VARCHAR(200) NOT NULL,
    descripcion      TEXT         NULL,
    nivel_dificultad VARCHAR(50)  NULL,
    id_profesional   INT          NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_receta_profesional FOREIGN KEY (id_profesional)
        REFERENCES usuario (id)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 11. INGREDIENTE_RECETA
--    Ingredientes que componen una receta con su porción.
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ingrediente_receta (
    id_receta      INT          NOT NULL,
    id_ingrediente INT          NOT NULL,
    cantidad_porcion DOUBLE     NOT NULL,
    unidad_medida  VARCHAR(50)  NOT NULL,
    PRIMARY KEY (id_receta, id_ingrediente),
    CONSTRAINT fk_ir_receta      FOREIGN KEY (id_receta)
        REFERENCES receta (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ir_ingrediente FOREIGN KEY (id_ingrediente)
        REFERENCES ingrediente (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 12. RECETA_USUARIO
--    Relación de recetas asignadas a un usuario (cliente).
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS receta_usuario (
    id_receta  INT NOT NULL,
    id_usuario INT NOT NULL,
    PRIMARY KEY (id_receta, id_usuario),
    CONSTRAINT fk_ru_receta  FOREIGN KEY (id_receta)
        REFERENCES receta (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ru_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 13. RUTINA
--    Plantillas de rutinas de entrenamiento.
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS rutina (
    id          INT          NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(200) NOT NULL,
    descripcion TEXT         NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 14. EJERCICIO
--    Catálogo de ejercicios físicos.
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ejercicio (
    id             INT          NOT NULL AUTO_INCREMENT,
    nombre         VARCHAR(200) NOT NULL,
    descripcion    TEXT         NULL,
    zona           VARCHAR(100) NULL,
    tipo_ejercicio VARCHAR(100) NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 15. RUTINA_EJERCICIO
--    Tabla intermedia: ejercicios que componen una rutina,
--    con series/repeticiones/tiempo aproximados.
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS rutina_ejercicio (
    id_rutina               INT          NOT NULL,
    id_ejercicio            INT          NOT NULL,
    series_aproximadas      INT          NOT NULL DEFAULT 0,
    repeticiones_aproximadas INT         NULL,
    tiempo_aproximado       VARCHAR(50)  NULL,
    PRIMARY KEY (id_rutina, id_ejercicio),
    CONSTRAINT fk_re_rutina   FOREIGN KEY (id_rutina)
        REFERENCES rutina (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_re_ejercicio FOREIGN KEY (id_ejercicio)
        REFERENCES ejercicio (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 16. RUTINA_CLIENTE
--    Rutinas asignadas a un cliente.
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS rutina_cliente (
    id_rutina  INT NOT NULL,
    id_usuario INT NOT NULL,
    PRIMARY KEY (id_rutina, id_usuario),
    CONSTRAINT fk_rc_rutina  FOREIGN KEY (id_rutina)
        REFERENCES rutina (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_rc_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------
-- 17. ENTRENAMIENTO
--    Registro de cada sesión real de entrenamiento del usuario.
-- -------------------------------------------------------------
CREATE TABLE IF NOT EXISTS entrenamiento (
    id          INT          NOT NULL AUTO_INCREMENT,
    id_usuario  INT          NOT NULL,
    id_rutina   INT          NOT NULL,
    id_ejercicio INT         NOT NULL,
    fecha_hora  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    serie       INT          NOT NULL,
    repeticion  INT          NULL,
    tiempo      VARCHAR(50)  NULL,
    peso        DOUBLE       NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT fk_ent_usuario   FOREIGN KEY (id_usuario)
        REFERENCES usuario (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ent_rutina    FOREIGN KEY (id_rutina)
        REFERENCES rutina (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ent_ejercicio FOREIGN KEY (id_ejercicio)
        REFERENCES ejercicio (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;


-- =============================================================
--  DATOS DE EJEMPLO (seed mínimo para poder probar la app)
-- =============================================================

-- Profesiones
INSERT IGNORE INTO profesion (id, nombre) VALUES
    (1, 'Nutricionista'),
    (2, 'Entrenador Personal'),
    (3, 'Médico Deportólogo');

-- Usuarios de prueba
-- Contraseñas en texto plano solo para desarrollo local.
-- Producción debe usar hash (bcrypt, etc.)
INSERT IGNORE INTO usuario (id, nombre, apellido, email, nombre_usuario, contrasena, tipo_usu, estado, id_profesion, profesion)
VALUES
    (1, 'Admin',    'Sistema',    'admin@jartraining.com',      'admin',    '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9',    1, 1, NULL,  NULL), -- admin123
    (2, 'Juan',     'Pérez',      'juan@jartraining.com',       'jperez',   '9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c',     3, 1, NULL,  NULL), -- pass123 (tipo_usu=3 para Cliente)
    (3, 'María',    'González',   'maria@jartraining.com',      'mgonzalez','9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c',     2, 1, 1,    'Nutricionista'), -- pass123
    (4, 'Carlos',   'López',      'carlos@jartraining.com',     'clopez',   '9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c',     2, 1, 2,    'Entrenador Personal'); -- pass123

-- Nutrientes
INSERT IGNORE INTO nutriente (id, nombre, descripcion) VALUES
    (1, 'Proteínas',       'Macronutriente esencial para la construcción muscular'),
    (2, 'Carbohidratos',   'Principal fuente de energía del cuerpo'),
    (3, 'Grasas',          'Ácidos grasos esenciales y vitaminas liposolubles'),
    (4, 'Fibra',           'Favorece el tránsito intestinal'),
    (5, 'Vitamina C',      'Antioxidante, refuerza el sistema inmune'),
    (6, 'Calcio',          'Mineral esencial para huesos y dientes'),
    (7, 'Hierro',          'Transporte de oxígeno en sangre');

-- Ingredientes
INSERT IGNORE INTO ingrediente (id, nombre, descripcion) VALUES
    (1,  'Pollo (pechuga)',  'Carne blanca magra, alta en proteínas'),
    (2,  'Arroz integral',   'Cereal integral, fuente de carbohidratos complejos'),
    (3,  'Brócoli',          'Vegetal crucífero rico en vitaminas'),
    (4,  'Huevo',            'Alimento completo, alta biodisponibilidad proteica'),
    (5,  'Avena',            'Cereal integral, fuente de fibra y carbohidratos'),
    (6,  'Atún (lata)',      'Pescado magro, fuente de omega-3 y proteínas'),
    (7,  'Espinaca',         'Vegetal de hoja, rica en hierro y ácido fólico'),
    (8,  'Banana',           'Fruta energética, fuente de potasio'),
    (9,  'Leche descremada', 'Lácteo bajo en grasa, fuente de calcio'),
    (10, 'Almendras',        'Fruto seco rico en grasas saludables y vitamina E');

-- Nutrientes por ingrediente (por 100 g)
INSERT IGNORE INTO nutriente_ingrediente (id_nutriente, id_ingrediente, cantidad, unidad_medida) VALUES
    -- Pollo pechuga
    (1, 1, 31.0,  'g'),   -- Proteínas
    (3, 1, 3.6,   'g'),   -- Grasas
    -- Arroz integral
    (2, 2, 23.0,  'g'),   -- Carbohidratos
    (4, 2, 1.8,   'g'),   -- Fibra
    -- Brócoli
    (5, 3, 89.0,  'mg'),  -- Vitamina C
    (4, 3, 2.6,   'g'),   -- Fibra
    -- Huevo
    (1, 4, 13.0,  'g'),   -- Proteínas
    (3, 4, 10.0,  'g'),   -- Grasas
    -- Avena
    (2, 5, 66.0,  'g'),   -- Carbohidratos
    (4, 5, 10.0,  'g'),   -- Fibra
    (1, 5, 13.0,  'g'),   -- Proteínas
    -- Atún
    (1, 6, 29.0,  'g'),   -- Proteínas
    (3, 6, 1.0,   'g'),   -- Grasas
    -- Espinaca
    (7, 7, 2.7,   'mg'),  -- Hierro
    (5, 7, 28.0,  'mg'),  -- Vitamina C
    -- Banana
    (2, 8, 23.0,  'g'),   -- Carbohidratos
    -- Leche descremada
    (6, 9, 120.0, 'mg'),  -- Calcio
    (1, 9, 3.4,   'g'),   -- Proteínas
    -- Almendras
    (3, 10, 50.0, 'g'),   -- Grasas
    (1, 10, 21.0, 'g');   -- Proteínas

-- Ejercicios
INSERT IGNORE INTO ejercicio (id, nombre, descripcion, zona, tipo_ejercicio) VALUES
    (1,  'Sentadilla',            'Ejercicio compuesto para piernas y glúteos',              'Piernas',    'Fuerza'),
    (2,  'Press de banca',        'Ejercicio de empuje para pecho',                          'Pecho',      'Fuerza'),
    (3,  'Dominadas',             'Ejercicio de jalón con peso corporal',                    'Espalda',    'Fuerza'),
    (4,  'Peso muerto',           'Ejercicio compuesto de cadena posterior',                 'Espalda',    'Fuerza'),
    (5,  'Plancha',               'Ejercicio isométrico de core',                            'Core',       'Resistencia'),
    (6,  'Trote en cinta',        'Cardio de moderada intensidad',                           'Cardio',     'Cardio'),
    (7,  'Curl de bíceps',        'Aislamiento de bíceps con mancuerna',                     'Brazos',     'Fuerza'),
    (8,  'Extensión de tríceps',  'Aislamiento de tríceps en polea',                         'Brazos',     'Fuerza'),
    (9,  'Zancadas',              'Ejercicio unilateral de piernas',                         'Piernas',    'Fuerza'),
    (10, 'Burpees',               'Ejercicio funcional de alta intensidad (HIIT)',            'Full body',  'Cardio');

INSERT IGNORE INTO rutina (id, nombre, descripcion) VALUES
    (1, 'Full Body Principiante', 'Rutina completa para personas que empiezan en el gym'),
    (2, 'Tren Superior',          'Enfocada en pecho, espalda y brazos'),
    (3, 'Tren Inferior',          'Enfocada en piernas y glúteos'),
    (4, 'HIIT 20 min',            'Circuito de alta intensidad, sin equipo');

INSERT IGNORE INTO rutina_ejercicio (id_rutina, id_ejercicio, series_aproximadas, repeticiones_aproximadas, tiempo_aproximado) VALUES
    (1, 1, 3, 12, NULL),   -- Sentadilla
    (1, 2, 3, 10, NULL),   -- Press de banca
    (1, 5, 3, NULL, '30s'),-- Plancha

    (2, 2, 4, 8,  NULL),   -- Press de banca
    (2, 3, 3, 6,  NULL),   -- Dominadas
    (2, 7, 3, 12, NULL),   -- Curl de bíceps
    (2, 8, 3, 12, NULL),   -- Extensión de tríceps
    (3, 1, 4, 10, NULL),   -- Sentadilla
    (3, 4, 3, 8,  NULL),   -- Peso muerto
    (3, 9, 3, 12, NULL),   -- Zancadas
    (4, 10, 4, NULL, '40s'),  -- Burpees
    (4, 5,  4, NULL, '30s');  -- Plancha

INSERT IGNORE INTO receta (id, nombre, descripcion, nivel_dificultad, id_profesional) VALUES
    (1, 'Bowl de pollo y arroz',    'Comida balanceada post-entrenamiento',   'Fácil',   3),
    (2, 'Avena proteica',           'Desayuno rápido y nutritivo',            'Fácil',   3),
    (3, 'Ensalada de atún',         'Almuerzo ligero rico en proteínas',      'Fácil',   3);

INSERT IGNORE INTO ingrediente_receta (id_receta, id_ingrediente, cantidad_porcion, unidad_medida) VALUES
    (1, 1, 150, 'GRAMOS'),  -- Pollo
    (1, 2, 100, 'GRAMOS'),  -- Arroz integral
    (1, 3, 80,  'GRAMOS'),  -- Brócoli
    (2, 5, 80,  'GRAMOS'),  -- Avena
    (2, 9, 200, 'ML'),      -- Leche descremada
    (2, 8, 1,   'UNIDAD'),  -- Banana
    (3, 6, 150, 'GRAMOS'),  -- Atún
    (3, 7, 100, 'GRAMOS');  -- Espinaca
