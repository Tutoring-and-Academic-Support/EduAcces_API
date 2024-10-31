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

-- Insertar datos de ejemplo en la tabla comentario
INSERT INTO comentario (texto, fecha, curso_id) VALUES
('Este curso es muy bueno para entender las matemáticas básicas.', CURRENT_TIMESTAMP, 1),
('El curso de física avanzada es excelente, pero requiere dedicación.', CURRENT_TIMESTAMP, 2),
('Me encanta la manera en que se enseña la programación en este curso.', CURRENT_TIMESTAMP, 3);