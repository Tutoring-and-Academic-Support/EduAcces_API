INSERT INTO curso (nombre_curso, descripcion, estado, valoracion) VALUES
('Matemáticas Básicas', 'Curso introductorio a las matemáticas', 'activo', 5),
('Física Avanzada', 'Curso avanzado de física teórica', 'activo', 4),
('Introducción a la Programación', 'Curso básico de programación en Java', 'activo', 5);

-- Insertar datos de ejemplo en la tabla comentario
INSERT INTO comentario (texto, fecha, curso_id) VALUES
('Este curso es muy bueno para entender las matemáticas básicas.', CURRENT_TIMESTAMP, 1),
('El curso de física avanzada es excelente, pero requiere dedicación.', CURRENT_TIMESTAMP, 2),
('Me encanta la manera en que se enseña la programación en este curso.', CURRENT_TIMESTAMP, 3);