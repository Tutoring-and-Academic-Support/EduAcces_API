-- src/main/resources/data.sql

-- 1. Insertar Roles
INSERT INTO roles (name) VALUES
                             ('ESTUDIANTE'),
                             ('TUTOR')
    ON CONFLICT (name) DO NOTHING;

-- 2. Insertar Users con role_id
-- Asumiendo que 'ESTUDIANTE' tiene id=1 y 'TUTOR' tiene id=2
INSERT INTO users (email, password, role_id) VALUES
                                                 ('luis.rodriguez@upao.edu', 'password123', 1),
                                                 ('ana.torres@upao.edu', 'password123', 1),
                                                 ('jose.vega@upao.edu', 'password123', 1),
                                                 ('carlos.martinez@upao.edu', 'password123', 2),
                                                 ('maria.lopez@upao.edu', 'password123', 2)
    ON CONFLICT (email) DO NOTHING;

-- 3. Insertar Estudiantes vinculados a Users con role_id=1
INSERT INTO estudiante (user_id, ciclo, nombre, contraseña, email) VALUES
                                                                       ((SELECT id FROM users WHERE email = 'luis.rodriguez@upao.edu'), 1, 'Luis Rodríguez', 'password123', 'luis.rodriguez@upao.edu'),
                                                                       ((SELECT id FROM users WHERE email = 'ana.torres@upao.edu'), 2, 'Ana Torres', 'password123', 'ana.torres@upao.edu'),
                                                                       ((SELECT id FROM users WHERE email = 'jose.vega@upao.edu'), 3, 'José Vega', 'password123', 'jose.vega@upao.edu')
    ON CONFLICT (user_id) DO NOTHING;

-- 4. Insertar Tutores vinculados a Users con role_id=2
INSERT INTO tutor (user_id, departamento, nombre, contraseña, email) VALUES
                                                                         ((SELECT id FROM users WHERE email = 'carlos.martinez@upao.edu'), 'Matemáticas', 'Carlos Martínez', 'password123', 'carlos.martinez@upao.edu'),
                                                                         ((SELECT id FROM users WHERE email = 'maria.lopez@upao.edu'), 'Física', 'María López', 'password123', 'maria.lopez@upao.edu')
    ON CONFLICT (user_id) DO NOTHING;

-- 5. Insertar Cursos
INSERT INTO curso (nombre_curso, descripcion, estado, valoracion) VALUES
                                                                      ('Matemáticas Básicas', 'Curso introductorio a las matemáticas', 'activo', 5),
                                                                      ('Física Avanzada', 'Curso avanzado de física teórica', 'activo', 4),
                                                                      ('Introducción a la Programación', 'Curso básico de programación en Java', 'activo', 5)
    ON CONFLICT DO NOTHING;

-- 6. Insertar Comentarios
INSERT INTO comentario (autor, texto, fecha, curso_id) VALUES
                                                           ('Pablo','Este curso es muy bueno para entender las matemáticas básicas.', CURRENT_TIMESTAMP, (SELECT id FROM curso WHERE nombre_curso = 'Matemáticas Básicas')),
                                                           ('Carlos' ,'El curso de física avanzada es excelente, pero requiere dedicación.', CURRENT_TIMESTAMP, (SELECT id FROM curso WHERE nombre_curso = 'Física Avanzada')),
                                                           ('Paola' ,'Me encanta la manera en que se enseña la programación en este curso.', CURRENT_TIMESTAMP, (SELECT id FROM curso WHERE nombre_curso = 'Introducción a la Programación'))
    ON CONFLICT DO NOTHING;

-- 7. Insertar Materiales con file_path
-- Asegúrate de que los archivos mencionados existan en 'uploads/materials'
INSERT INTO material (titulo, tipo, fecha_subida, curso_id, file_path) VALUES
                                                                           ('Introducción a la Matemática', 'PDF', '2024-01-10', (SELECT id FROM curso WHERE nombre_curso = 'Matemáticas Básicas'), 'introduccion_matematica.pdf'),
                                                                           ('Física Moderna', 'VIDEO', '2024-01-15', (SELECT id FROM curso WHERE nombre_curso = 'Física Avanzada'), 'fisica_moderna.mp4'),
                                                                           ('Fundamentos de Programación', 'PRESENTACION', '2024-01-20', (SELECT id FROM curso WHERE nombre_curso = 'Introducción a la Programación'), 'fundamentos_programacion.pptx')
    ON CONFLICT DO NOTHING;

-- 8. Insertar Notas vinculadas a Materiales
INSERT INTO nota (texto, fecha, material_id) VALUES
                                                 ('Esta es una nota sobre la introducción a la matemática.', CURRENT_TIMESTAMP, (SELECT id FROM material WHERE titulo = 'Introducción a la Matemática')),
                                                 ('La teoría de la relatividad es un tema fascinante.', CURRENT_TIMESTAMP, (SELECT id FROM material WHERE titulo = 'Física Moderna')),
                                                 ('Este ejercicio de programación es muy útil para aprender loops.', CURRENT_TIMESTAMP, (SELECT id FROM material WHERE titulo = 'Fundamentos de Programación'))
    ON CONFLICT DO NOTHING;