INSERT INTO categorias (nombre, descripcion) VALUES 
('Fútbol', 'Equipos y accesorios para fútbol'),
('Baloncesto', 'Equipos y accesorios para baloncesto'),
('Tenis', 'Equipos y accesorios para tenis'),
('Atletismo', 'Equipos y accesorios para atletismo');

INSERT INTO productos (nombre, descripcion, precio, stock, categoria_id, sku) VALUES 
('Balón de Fútbol Official', 'Balón profesional FIFA', 85.50, 50, 1, 'BALON-001'),
('Botas de Fútbol Nike Phantom', 'Botas de fútbol de alto rendimiento', 120.00, 30, 1, 'BOTAS-001'),
('Canilleras de Fútbol', 'Protección profesional', 25.00, 100, 1, 'CANIL-001'),
('Balón de Baloncesto Spalding', 'Balón oficial NBA', 95.00, 25, 2, 'BALON-002'),
('Zapatillas de Baloncesto Air Jordan', 'Zapatillas de baloncesto premium', 150.00, 20, 2, 'ZAPAT-001'),
('Raqueta de Tenis Wilson Pro', 'Raqueta profesional', 200.00, 15, 3, 'RAQUET-001'),
('Pelotas de Tenis Wilson', 'Caja de 3 pelotas', 12.00, 200, 3, 'PELOTA-001'),
('Zapatillas de Atletismo Adidas', 'Zapatillas para correr', 110.00, 40, 4, 'ZAPAT-002'),
('Cinta Métrica para Atletismo', 'Para medición de saltos y lanzamientos', 15.50, 150, 4, 'CINTA-001'),
('Uniforme de Fútbol', 'Jersey oficial', 45.00, 80, 1, 'UNIFM-001');

INSERT INTO clientes (nombre, email, telefono, direccion, ciudad) VALUES 
('Juan García', 'juan.garcia@email.com', '5551234567', 'Calle Principal 123', 'Madrid'),
('María López', 'maria.lopez@email.com', '5559876543', 'Avenida Central 456', 'Barcelona'),
('Carlos Martínez', 'carlos.martinez@email.com', '5552468135', 'Calle del Deporte 789', 'Valencia'),
('Ana Rodríguez', 'ana.rodriguez@email.com', '5557891234', 'Plaza Mayor 101', 'Bilbao'),
('Pedro Sánchez', 'pedro.sanchez@email.com', '5554567890', 'Callejón del Fútbol 202', 'Sevilla');

INSERT INTO ventas (cliente_id, fecha, total, estado) VALUES 
(1, NOW(), 205.50, 'COMPLETADA'),
(2, NOW(), 275.00, 'PENDIENTE'),
(3, NOW(), 250.00, 'COMPLETADA'),
(4, NOW(), 110.00, 'COMPLETADA');

INSERT INTO detalles_venta (venta_id, producto_id, cantidad, precio_unitario, subtotal) VALUES 
(1, 1, 2, 85.50, 171.00),
(1, 3, 1, 25.00, 25.00),
(1, 9, 1, 15.50, 15.50),
(2, 4, 2, 95.00, 190.00),
(2, 7, 1, 12.00, 12.00),
(2, 1, 1, 85.50, 85.00),
(3, 6, 1, 200.00, 200.00),
(3, 7, 5, 12.00, 60.00),
(4, 8, 1, 110.00, 110.00);