-- Inserción de roles (sin especificar el id)
INSERT INTO roles (name) VALUES
                             ('TUTOR'),
                             ('ESTUDIANTE')
ON CONFLICT (name) DO NOTHING;

-- Inserción de usuarios (sin especificar el id) con contraseñas seguras
INSERT INTO users (email, password, role_id) VALUES
                                                 ('tutor1@eduaccess.com', 'Password@123', (SELECT id FROM roles WHERE name = 'TUTOR')),
                                                 ('estudiante1@eduaccess.com', 'Estudiante@2023', (SELECT id FROM roles WHERE name = 'ESTUDIANTE'))
ON CONFLICT (email) DO NOTHING;

-- Inserción de tutores (sin especificar el id_tutor)
INSERT INTO tutor (departamento, nombre, apellidos, user_id) VALUES
    ('Ciencias', 'Juan', 'Lopez', (SELECT id FROM users WHERE email = 'tutor1@eduaccess.com'));

-- Inserción de estudiantes (sin especificar el id)
INSERT INTO estudiante (ciclo, nombre, apellidos, user_id) VALUES
    (3, 'Marta', 'Campos', (SELECT id FROM users WHERE email = 'estudiante1@eduaccess.com'));

-- Inserción de cursos
INSERT INTO curso (nombre_curso, descripcion, estado, valoracion) VALUES
                                                                      ('Matemáticas Básicas', 'Curso de introducción a las matemáticas', 'PUBLICADO', 5),
                                                                      ('Física Avanzada', 'Curso avanzado de física', 'NO_PUBLICADO', 4);

-- Relación curso-tutor
INSERT INTO curso_tutor (id_curso, id_tutor, fecha_asignacion, fecha_termino) VALUES
    ((SELECT id FROM curso WHERE nombre_curso = 'Matemáticas Básicas'),
     (SELECT id_tutor FROM tutor WHERE nombre = 'Juan' AND apellidos = 'Lopez'),
     '2023-01-01',
     '2023-12-31');

-- Relación estudiante-curso
INSERT INTO estudiante_curso (id_estudiante, id_curso, fecha) VALUES
    ((SELECT id FROM estudiante WHERE nombre = 'Marta' AND apellidos = 'Campos'),
     (SELECT id FROM curso WHERE nombre_curso = 'Matemáticas Básicas'),
     '2023-03-01');

-- Inserción de materiales
INSERT INTO material (titulo, tipo, fecha_subida, curso_id) VALUES
                                                                ('Introducción a Álgebra', 'PDF', '2023-05-01', (SELECT id FROM curso WHERE nombre_curso = 'Matemáticas Básicas')),
                                                                ('Conceptos Básicos de Física', 'VIDEO', '2023-06-01', (SELECT id FROM curso WHERE nombre_curso = 'Física Avanzada'));

-- Inserción de comentarios
INSERT INTO comentario (texto, fecha, autor, curso_id, estudiante_id) VALUES
    ('Excelente material, muy claro.', '2023-06-15', 'Estudiante Uno',
     (SELECT id FROM curso WHERE nombre_curso = 'Matemáticas Básicas'),
     (SELECT id FROM estudiante WHERE nombre = 'Marta' AND apellidos = 'Campos'));

-- Inserción de notas
INSERT INTO nota (texto, fecha, material_id) VALUES
    ('Nota sobre la importancia del álgebra', '2023-07-01',
     (SELECT id FROM material WHERE titulo = 'Introducción a Álgebra'));

-- Inserción de pagos
INSERT INTO pago (fecha_pago, monto_total) VALUES
    ('2023-04-10', 150.00);

-- Inserción de detalle de pago
INSERT INTO detalle_pago (descripcion, cantidad_estudiantes, precio_por_estudiante, id_pago, id_tutor) VALUES
    ('Pago del curso de matemáticas', 10, 15.00,
     (SELECT id_pago FROM pago WHERE fecha_pago = '2023-04-10' AND monto_total = 150.00),
     (SELECT id_tutor FROM tutor WHERE nombre = 'Juan' AND apellidos = 'Lopez'));

