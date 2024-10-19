INSERT INTO curso (nombre_curso, descripcion, estado, valoracion) VALUES
('Matemáticas Básicas', 'Curso introductorio a las matemáticas', 'activo', 5),
('Física Avanzada', 'Curso avanzado de física teórica', 'activo', 4),
('Introducción a la Programación', 'Curso básico de programación en Java', 'activo', 5);

INSERT INTO comentario (texto, fecha, curso_id) VALUES
('Este curso es muy bueno para entender las matemáticas básicas.', CURRENT_TIMESTAMP, 1),
('El curso de física avanzada es excelente, pero requiere dedicación.', CURRENT_TIMESTAMP, 2),
('Me encanta la manera en que se enseña la programación en este curso.', CURRENT_TIMESTAMP, 3);

INSERT INTO tutor (departamento, nombre, contraseña, email) VALUES
('Ciencias de la Computación', 'Juan Pérez', 'contraseñaSegura123', 'juan.perez@example.com'),
('Matemáticas', 'Ana Gómez', 'contraseñaMat123', 'ana.gomez@example.com'),
('Biología', 'Luis Martínez', 'bioSegura456', 'luis.martinez@example.com'),
('Física', 'María López', 'fisicaSegura789', 'maria.lopez@example.com'),
('Química', 'Carlos Sánchez', 'quimicaSegura321', 'carlos.sanchez@example.com');

INSERT INTO comentario (texto, fecha, curso_id) VALUES
('Este curso es muy interesante.', NOW(), 1),
('Me gustaría tener más recursos en este tema.', NOW(), 1),
('La explicación fue muy clara, gracias.', NOW(), 2),
('Los ejercicios prácticos ayudan mucho a entender.', NOW(), 2),
('¿Podrían añadir más ejemplos en clase?', NOW(), 3);

INSERT INTO estudiante (ciclo, nombre, contraseña, email) VALUES
(1, 'Carlos Ruiz', 'contraseñaSegura1', 'carlos.ruiz@example.com'),
(2, 'Laura Fernández', 'contraseñaSegura2', 'laura.fernandez@example.com'),
(1, 'José Martínez', 'contraseñaSegura3', 'jose.martinez@example.com'),
(3, 'Ana Gómez', 'contraseñaSegura4', 'ana.gomez@example.com'),
(2, 'Luis Pérez', 'contraseñaSegura5', 'luis.perez@example.com');