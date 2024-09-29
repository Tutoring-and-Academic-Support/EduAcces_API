INSERT INTO users (email, password) VALUES
                                        ('luis.rodriguez@upao.edu', 'password123'),
                                        ('ana.torres@upao.edu', 'password123'),
                                        ('jose.vega@upao.edu', 'password123')
    ON CONFLICT DO NOTHING;

INSERT INTO estudiante (user_id, ciclo, nombre, contraseña, email) VALUES
                                                                       (1, 1, 'Luis Rodríguez', 'password123', 'luis.rodriguez@upao.edu'),
                                                                       (2, 2, 'Ana Torres', 'password123', 'ana.torres@upao.edu'),
                                                                       (3, 3, 'José Vega', 'password123', 'jose.vega@upao.edu')
    ON CONFLICT DO NOTHING;


INSERT INTO curso (nombre_curso, descripcion, estado, valoracion) VALUES
                                                                      ('Matemáticas Básicas', 'Curso introductorio a las matemáticas', 'activo', 5),
                                                                      ('Física Avanzada', 'Curso avanzado de física teórica', 'activo', 4),
                                                                      ('Introducción a la Programación', 'Curso básico de programación en Java', 'activo', 5);

-- Insertar datos de ejemplo en la tabla comentario
INSERT INTO comentario (texto, fecha, curso_id) VALUES
                                                    ('Este curso es muy bueno para entender las matemáticas básicas.', CURRENT_TIMESTAMP, 1),
                                                    ('El curso de física avanzada es excelente, pero requiere dedicación.', CURRENT_TIMESTAMP, 2),
                                                    ('Me encanta la manera en que se enseña la programación en este curso.', CURRENT_TIMESTAMP, 3);

INSERT INTO material (titulo, tipo, fecha_subida, curso_id) VALUES
                                                                ('Introducción a la Matemática', 'PDF', '2024-01-10', 1),
                                                                ('Física Moderna', 'VIDEO', '2024-01-15', 2),
                                                                ('Fundamentos de Programación', 'TEXTO', '2024-01-20', 3)
    ON CONFLICT DO NOTHING;

INSERT INTO nota (texto, fecha, material_id) VALUES
                                                 ('Esta es una nota sobre la introducción a la matemática.', CURRENT_TIMESTAMP, 1),
                                                 ('La teoría de la relatividad es un tema fascinante.', CURRENT_TIMESTAMP, 2),
                                                 ('Este ejercicio de programación es muy útil para aprender loops.', CURRENT_TIMESTAMP, 3)
    ON CONFLICT DO NOTHING;

