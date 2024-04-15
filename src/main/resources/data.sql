DELETE FROM clientes;
DELETE FROM contratos;
DELETE FROM favoritos;
DELETE FROM herramientas;
DELETE FROM objetos;
DELETE FROM profesionales;
DELETE FROM profesional_herramienta;
DELETE FROM servicios;
DELETE FROM usuarios;

-- Clientes
INSERT INTO clientes (nombre, apellidos, numero) VALUES ('Ana', 'García', '123456789');
INSERT INTO clientes (nombre, apellidos, numero) VALUES ('Juan', 'Martínez', '987654321');
INSERT INTO clientes (nombre, apellidos, numero) VALUES ('María', 'López', '456789123');

-- Profesionales
INSERT INTO profesionales (nombre, apellidos, numero, experiencia, oficio, disponibilidad, valoracion) VALUES ('Carlos', 'Sánchez', '111222333', 5, 'Fontanero', 'Mañana', 4);
INSERT INTO profesionales (nombre, apellidos, numero, experiencia, oficio, disponibilidad, valoracion) VALUES ('Laura', 'Díaz', '444555666', 8, 'Electricista', 'Tarde', 5);
INSERT INTO profesionales (nombre, apellidos, numero, experiencia, oficio, disponibilidad, valoracion) VALUES ('Pedro', 'Gómez', '777888999', 10, 'Carpintero', 'Noche', 4);

-- Servicios
INSERT INTO servicios (nombre, descripcion, tarifaBase) VALUES ('Reparación de Fontanería', 'Reparación de tuberías y grifos', 50.00);
INSERT INTO servicios (nombre, descripcion, tarifaBase) VALUES ('Instalación Eléctrica', 'Instalación de sistemas eléctricos', 70.00);
INSERT INTO servicios (nombre, descripcion, tarifaBase) VALUES ('Construcción de Muebles', 'Construcción de muebles a medida', 100.00);

-- Contratos
INSERT INTO contratos (cliente_id, profesional_id, servicio_id, fechaInicio, fechaFin, estado) VALUES (1, 1, 1, '2024-04-15', '2024-04-20', 'En proceso');
INSERT INTO contratos (cliente_id, profesional_id, servicio_id, fechaInicio, fechaFin, estado) VALUES (2, 2, 2, '2024-04-16', '2024-04-22', 'En proceso');
INSERT INTO contratos (cliente_id, profesional_id, servicio_id, fechaInicio, fechaFin, estado) VALUES (3, 3, 3, '2024-04-17', '2024-04-25', 'En proceso');

-- Favoritos
INSERT INTO favoritos (clienteId, profesionalId) VALUES (1, 2);
INSERT INTO favoritos (clienteId, profesionalId) VALUES (2, 1);
INSERT INTO favoritos (clienteId, profesionalId) VALUES (3, 3);

-- Herramientas
INSERT INTO herramientas (nombre) VALUES ('Martillo');
INSERT INTO herramientas (nombre) VALUES ('Destornillador');
INSERT INTO herramientas (nombre) VALUES ('Sierra');

-- Objetos
INSERT INTO objetos (nombre, descripcion, precio) VALUES ('Mesa', 'Mesa de madera', 150.00);
INSERT INTO objetos (nombre, descripcion, precio) VALUES ('Silla', 'Silla de plástico', 30.00);
INSERT INTO objetos (nombre, descripcion, precio) VALUES ('Lámpara', 'Lámpara de pie', 80.00);

-- Asociación de herramientas a profesionales
INSERT INTO profesional_herramienta (profesionalId, herramientaId, cantidad) VALUES (1, 1, 1);
INSERT INTO profesional_herramienta (profesionalId, herramientaId, cantidad) VALUES (2, 2, 2);
INSERT INTO profesional_herramienta (profesionalId, herramientaId, cantidad) VALUES (3, 3, 1);