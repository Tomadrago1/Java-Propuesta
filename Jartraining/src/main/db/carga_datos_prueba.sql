-- Inserción de datos en la tabla USUARIO
INSERT INTO USUARIO (nombre, apellido, email, contrasena, estado, nombre_usuario, tipo_usu, profesion)
VALUES
('Admin', 'Admin', 'admin@admin.com', 'a', 1, 'admin', 1, NULL),
('Juan', 'Perez', 'juanp@correo.com', 'password123', 1, 'juanp', 2, 'Nutricionista'),
('Ana', 'Lopez', 'anal@correo.com', 'password123', 1, 'anal', 2, 'Entrenador Personal'),
('User', 'User', 'user@user.com', 'a', 1, 'user', 3, NULL),
('Maria', 'Rodriguez', 'mariar@correo.com', 'password123', 1, 'mariar', 3, NULL);

-- Inserción de datos en la tabla RECETA
INSERT INTO RECETA (id_profesional, nombre, descripcion, nivel_dificultad)
VALUES
(2, 'Ensalada César', 'Ensalada con lechuga, pollo, y aderezo César', 'BAJO'),
(2, 'Tarta de manzana', 'Tarta dulce de manzana', 'MEDIO'),
(3, 'Pizza Margarita', 'Pizza con tomate, mozzarella y albahaca', 'ALTO'),
(2, 'Sopa de vegetales', 'Sopa con variedad de vegetales', 'BAJO'),
(2, 'Brownies de chocolate', 'Brownies con chispas de chocolate', 'ALTO');


-- Inserción de datos en la tabla HISTORIAL_MEDIDA
INSERT INTO HISTORIAL_MEDIDA (id_usuario, fecha, peso, altura)
VALUES
(4, '2023-01-01', 70.5, 1.75),
(4, '2023-02-01', 72.0, 1.75),
(5, '2023-01-01', 65.0, 1.65),
(5, '2023-02-01', 64.5, 1.65),
(4, '2023-03-01', 71.0, 1.75);

-- Inserción de datos en la tabla INGREDIENTE
INSERT INTO INGREDIENTE (nombre, descripcion)
VALUES
('Lechuga', 'Verdura verde'),
('Pollo', 'Carne blanca'),
('Manzana', 'Fruta roja'),
('Chocolate', 'Dulce'),
('Harina', 'Polvo blanco para repostería');

-- Inserción de datos en la tabla INGREDIENTE_RECETA
INSERT INTO INGREDIENTE_RECETA (id_ingrediente, id_receta, cantidad_porcion, unidad_medida)
VALUES
(1, 1, 100, 'gramos'),
(2, 1, 200, 'gramos'),
(3, 2, 150, 'gramos'),
(4, 5, 50, 'gramos'),
(5, 5, 100, 'gramos');

-- Inserción de datos en la tabla NUTRIENTE
INSERT INTO NUTRIENTE (nombre, descripcion)
VALUES
('Proteína', 'Macronutriente esencial para el cuerpo'),
('Carbohidrato', 'Fuente de energía primaria'),
('Grasa', 'Reserva de energía'),
('Fibra', 'Ayuda a la digestión'),
('Vitaminas', 'Nutrientes esenciales');

-- Inserción de datos en la tabla NUTRIENTE_INGREDIENTE
INSERT INTO NUTRIENTE_INGREDIENTE (id_nutriente, id_ingrediente, cantidad, unidad_medida)
VALUES
(1, 2, 25, 'gramos'),
(2, 3, 15, 'gramos'),
(3, 4, 5, 'gramos'),
(4, 1, 2, 'gramos'),
(5, 5, 1, 'gramos');

-- Inserción de datos en la tabla RUTINA
INSERT INTO RUTINA (nombre, descripcion)
VALUES
('Rutina de Cardio', 'Ejercicios para mejorar la resistencia cardiovascular'),
('Rutina de Fuerza', 'Ejercicios para aumentar la fuerza'),
('Rutina de Flexibilidad', 'Ejercicios para mejorar la flexibilidad'),
('Rutina de HIIT', 'Entrenamiento de intervalos de alta intensidad'),
('Rutina de Yoga', 'Ejercicios de estiramiento y relajación');

-- Inserción de datos en la tabla EJERCICIO
INSERT INTO EJERCICIO (nombre, descripcion)
VALUES
('Correr', 'Ejercicio cardiovascular'),
('Flexiones', 'Ejercicio de fuerza para pecho y brazos'),
('Sentadillas', 'Ejercicio de fuerza para piernas'),
('Abdominales', 'Ejercicio para fortalecer el abdomen'),
('Estiramiento', 'Ejercicio para mejorar la flexibilidad');

-- Inserción de datos en la tabla ENTRENAMIENTO
INSERT INTO ENTRENAMIENTO (id_usuario, id_rutina, id_ejercicio, fecha_entrenamiento, serie, repeticion, orden, tiempo, peso)
VALUES
(4, 1, 1, '2023-05-01', 3, 10, 1, '00:20:00', NULL),
(4, 2, 2, '2023-05-01', 4, 8, 2, NULL, 70.0),
(4, 2, 3, '2023-05-01', 4, 10, 3, NULL, NULL),
(5, 3, 4, '2023-05-02', 3, 15, 1, '00:10:00', NULL),
(5, 4, 5, '2023-05-03', 2, 20, 1, '00:30:00', NULL);

-- Inserción de datos en la tabla NECESIDAD
INSERT INTO NECESIDAD (id_usuario, id_nutriente, id_profesional, fecha, cantidad_min, cantidad_max)
VALUES
(4, 1, 2, '2023-04-01', 50.0, 150.0),
(4, 2, 2, '2023-04-01', 100.0, 300.0),
(5, 3, 2, '2023-04-01', 20.0, 70.0),
(5, 4, 2, '2023-04-01', 25.0, 80.0),
(4, 5, 3, '2023-04-01', 10.0, 50.0);

-- Inserción de datos en la tabla RUTINA_CLIENTE
INSERT INTO RUTINA_CLIENTE (id_usuario, id_rutina)
VALUES
(4, 1),
(4, 2),
(5, 3),
(5, 4),
(4, 5);

-- Inserción de datos en la tabla CONSULTA
INSERT INTO CONSULTA (id_cliente, id_profesional, fecha_consulta, desc_resultados)
VALUES
(4, 2, '2023-06-01', 'Consulta nutricional, revisión de dieta'),
(4, 3, '2023-06-05', 'Consulta de entrenamiento, ajuste de rutina'),
(5, 2, '2023-06-10', 'Consulta nutricional, revisión de suplementos'),
(5, 3, '2023-06-15', 'Consulta de entrenamiento, planificación de ejercicios'),
(4, 2, '2023-06-20', 'Consulta nutricional, seguimiento de progreso');

-- Inserción de datos en la tabla RUTINA_EJERCICIO
INSERT INTO RUTINA_EJERCICIO (id_rutina, id_ejercicio, series_aproximadas, repeticiones_aproximadas, tiempo_aproximado)
VALUES
(1, 1, 3, 10, '00:20:00'),
(2, 2, 4, 8, NULL),
(2, 3, 4, 10, NULL),
(3, 4, 3, 15, '00:10:00'),
(4, 5, 2, 20, '00:30:00');

-- Inserción de datos en la tabla HORARIOS
INSERT INTO HORARIOS (id_profesional, fecha_hora_desde, fecha_hora_hasta)
VALUES
(2, '2023-06-01 08:00:00', '2023-06-01 12:00:00'),
(2, '2023-06-01 14:00:00', '2023-06-01 18:00:00'),
(3, '2023-06-02 08:00:00', '2023-06-02 12:00:00'),
(3, '2023-06-02 14:00:00', '2023-06-02 18:00:00'),
(2, '2023-06-03 08:00:00', '2023-06-03 12:00:00');
