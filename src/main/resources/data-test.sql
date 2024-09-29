INSERT INTO pago (fecha_pago, monto_total) VALUES
                                               ('2024-02-10', 500.00),
                                               ('2024-02-15', 450.00),
                                               ('2024-02-20', 800.00)
    ON CONFLICT DO NOTHING;

INSERT INTO tutor (departamento, nombre, password, email) VALUES
                                                              ('Matemáticas', 'Carlos Pérez', 'password123', 'carlos.perez@upao.edu'),
                                                              ('Física', 'María Gómez', 'password123', 'maria.gomez@upao.edu'),
                                                              ('Programación', 'Juan Martínez', 'password123', 'juan.martinez@upao.edu')
    ON CONFLICT (email) DO NOTHING;


-- Insertar los estudiantes
INSERT INTO estudiante (ciclo, nombre, password, email) VALUES
                                                            (1, 'Luis Rodríguez', 'password123', 'luis.rodriguez@upao.edu'),
                                                            (2, 'Ana Torres', 'password123', 'ana.torres@upao.edu'),
                                                            (3, 'José Vega', 'password123', 'jose.vega@upao.edu')
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

INSERT INTO curso_tutor (id_curso, id_tutor, fecha_asignacion, fecha_termino) VALUES
                                                                                  (1, 1, '2024-01-01', '2024-12-31'),
                                                                                  (2, 2, '2024-01-01', '2024-12-31'),
                                                                                  (3, 3, '2024-01-01', '2024-12-31')
    ON CONFLICT (id_curso, id_tutor) DO NOTHING;

INSERT INTO detalle_pago (descripcion, cantidad_estudiantes, precio_por_estudiante, id_pago, id_tutor) VALUES
                                                                                                           ('Pago de curso Matemáticas Básicas', 5, 100.00, 1, 1),
                                                                                                           ('Pago de curso Física Avanzada', 3, 150.00, 2, 2),
                                                                                                           ('Pago de curso Programación Java', 4, 200.00, 3, 3)
    ON CONFLICT DO NOTHING;


