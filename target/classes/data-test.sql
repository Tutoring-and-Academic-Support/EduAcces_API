-- Inserción de roles
INSERT INTO roles (id, name) VALUES
                                 (1, 'TUTOR'),
                                 (2, 'ESTUDIANTE');

-- Inserción de usuarios
INSERT INTO users (id, email, password, role_id) VALUES
                                                     (1, 'tutor1@eduaccess.com', 'password123', 1),
                                                     (2, 'estudiante1@eduaccess.com', 'password123', 2);

-- Inserción de tutores
INSERT INTO tutor (id_tutor, departamento, nombre, contraseña, email, user_id) VALUES
    (1, 'Ciencias', 'Tutor Uno', 'password123', 'tutor1@eduaccess.com', 1);

-- Inserción de estudiantes
INSERT INTO estudiante (id, ciclo, nombre, contraseña, email, user_id) VALUES
    (1, 3, 'Estudiante Uno', 'password123', 'estudiante1@eduaccess.com', 2);

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
INSERT INTO comentario (id, texto, fecha, autor, curso_id, estudiante_id) VALUES
    (1, 'Excelente material, muy claro.', '2023-06-15', 'Estudiante Uno', 1, 1);

-- Inserción de notas
INSERT INTO nota (id, texto, fecha, material_id) VALUES
    (1, 'Nota sobre la importancia del álgebra', '2023-07-01', 1);

-- Inserción de detalle de pago
INSERT INTO pago (id_pago, fecha_pago, monto_total) VALUES
    (1, '2023-04-10', 150.00);

INSERT INTO detalle_pago (id_detalle, descripcion, cantidad_estudiantes, precio_por_estudiante, id_pago, id_tutor) VALUES
    (1, 'Pago del curso de matemáticas', 10, 15.00, 1, 1);
