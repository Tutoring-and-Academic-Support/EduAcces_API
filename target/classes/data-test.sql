-- Inserción de roles (sin especificar el id)
INSERT INTO roles (name) VALUES
                             ('TUTOR'),
                             ('ESTUDIANTE')
    ON CONFLICT (name) DO NOTHING;

-- Inserción de usuarios (sin especificar el id)
INSERT INTO users (email, password, role_id) VALUES
                                                 ('tutor1@upao.edu.pe', 'password123', (SELECT id FROM roles WHERE name = 'TUTOR')),
                                                 ('estudiante1@upao.edu.pe', 'password123', (SELECT id FROM roles WHERE name = 'ESTUDIANTE'))
    ON CONFLICT (email) DO NOTHING;

-- Inserción de tutores (sin especificar el id_tutor)
INSERT INTO tutor (departamento, nombre, contraseña, email, user_id) VALUES
    ('Ciencias', 'Juan', 'password123', 'tutor1@upao.edu.pe', (SELECT id FROM users WHERE email = 'tutor1@upao.edu.pe'));

-- Inserción de estudiantes (sin especificar el id)
INSERT INTO estudiante (ciclo, nombre, contraseña, email, user_id) VALUES
    (3, 'Marta', 'password123', 'estudiante1@upao.edu.pe', (SELECT id FROM users WHERE email = 'estudiante1@upao.edu.pe'));

-- Inserción de cursos
INSERT INTO curso (id, nombre_curso, descripcion, estado, valoracion) VALUES
                                                                          (1, 'Matemáticas Básicas', 'Curso de introducción a las matemáticas', 'PUBLICADO', 5),
                                                                          (2, 'Física Avanzada', 'Curso avanzado de física', 'NO_PUBLICADO', 4);

-- Relación curso-tutor
INSERT INTO curso_tutor (id_curso, id_tutor, fecha_asignacion, fecha_termino) VALUES
    (1, 1, '2023-01-01', '2023-12-31');

-- Relación estudiante-curso
INSERT INTO estudiante_curso (id_estudiante, id_curso, fecha) VALUES
    (1, 1, '2023-03-01');

-- Inserción de materiales
INSERT INTO material (id, titulo, tipo, fecha_subida, curso_id) VALUES
                                                                    (1, 'Introducción a Álgebra', 'PDF', '2023-05-01', 1),
                                                                    (2, 'Conceptos Básicos de Física', 'VIDEO', '2023-06-01', 2);

-- Inserción de comentarios
INSERT INTO comentario (texto, fecha, autor, curso_id, estudiante_id) VALUES
    ('Excelente material, muy claro.', '2023-06-15', 'Estudiante Uno', 1, 1);

-- Inserción de notas
INSERT INTO nota (texto, fecha, material_id) VALUES
    ('Nota sobre la importancia del álgebra', '2023-07-01', 1);

-- Inserción de pagos
INSERT INTO pago (fecha_pago, monto_total) VALUES
    ('2023-04-10', 150.00);

-- Inserción de detalle de pago
INSERT INTO detalle_pago (descripcion, cantidad_estudiantes, precio_por_estudiante, id_pago, id_tutor) VALUES
    ('Pago del curso de matemáticas', 10, 15.00, 1, 1);
