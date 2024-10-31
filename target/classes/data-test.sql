
-- Inserción de notas
INSERT INTO nota (id, texto, fecha, material_id) VALUES
    (1, 'Nota sobre la importancia del álgebra', '2023-07-01', 1);
ON CONFLICT DO NOTHING;

