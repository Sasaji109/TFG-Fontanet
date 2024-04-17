
DELETE FROM profesional_herramienta;
DELETE FROM contratos;
DELETE FROM favoritos;
DELETE FROM clientes;
DELETE FROM profesionales;
DELETE FROM usuarios;
DELETE FROM herramientas;
DELETE FROM objetos;
DELETE FROM servicios;

INSERT INTO usuarios (user_id, username, correo, password, activado, role) VALUES (1, 'juan123', 'juan123@example.com', 'juan123pass', false, 'CLIENTE');
INSERT INTO usuarios (user_id, username, correo, password, activado, role) VALUES (2, 'maria456', 'maria456@example.com', 'maria456pass', false, 'CLIENTE');
INSERT INTO usuarios (user_id, username, correo, password, activado, role) VALUES (3, 'pedro789', 'pedro789@example.com', 'pedro789pass', false, 'CLIENTE');
INSERT INTO usuarios (user_id, username, correo, password, activado, role) VALUES (4, 'juan1234', 'juan1234@example.com', 'juan1234pass', false, 'PROF');
INSERT INTO usuarios (user_id, username, correo, password, activado, role) VALUES (5, 'maria4564', 'maria4564@example.com', 'maria4564pass', false, 'PROF');
INSERT INTO usuarios (user_id, username, correo, password, activado, role) VALUES (6, 'pedro7894', 'pedro7894@example.com', 'pedro7894pass', false, 'PROF');
INSERT INTO usuarios (user_id, username, correo, password, activado, role) VALUES (7, 'juan1235', 'juan1235@example.com', 'juan1235pass', true, 'ADMIN');

INSERT INTO clientes (cliente_id, nombre, apellidos, numero, user_id) VALUES (1, 'Ana', 'García', '123456789', 1);
INSERT INTO clientes (cliente_id, nombre, apellidos, numero, user_id) VALUES (2, 'Juan', 'Martínez', '987654321', 2);
INSERT INTO clientes (cliente_id, nombre, apellidos, numero, user_id) VALUES (3, 'María', 'López', '456789123', 3);

INSERT INTO profesionales (profesional_id, nombre, apellidos, numero, experiencia, oficio, disponibilidad, valoracion, user_id) VALUES (1, 'Carlos', 'Sánchez', '111222333', 5, 'Fontanero', 'Mañana', 4, 4);
INSERT INTO profesionales (profesional_id, nombre, apellidos, numero, experiencia, oficio, disponibilidad, valoracion, user_id) VALUES (2, 'Laura', 'Díaz', '444555666', 8, 'Electricista', 'Tarde', 5, 5);
INSERT INTO profesionales (profesional_id, nombre, apellidos, numero, experiencia, oficio, disponibilidad, valoracion, user_id) VALUES (3, 'Pedro', 'Gómez', '777888999', 10, 'Carpintero', 'Noche', 43, 6);

INSERT INTO servicios (servicio_id, nombre, descripcion, tarifa_base) VALUES (1, 'Reparación de Fontanería', 'Reparación de tuberías y grifos', 50.00);
INSERT INTO servicios (servicio_id, nombre, descripcion, tarifa_base) VALUES (2, 'Instalación Eléctrica', 'Instalación de sistemas eléctricos', 70.00);
INSERT INTO servicios (servicio_id, nombre, descripcion, tarifa_base) VALUES (3, 'Construcción de Muebles', 'Construcción de muebles a medida', 100.00);

INSERT INTO contratos (contrato_id, cliente_id, profesional_id, servicio_id, fecha_inicio, fecha_fin, estado) VALUES (1, 1, 1, 1, '2024-04-15', '2024-04-20', 'En proceso');
INSERT INTO contratos (contrato_id, cliente_id, profesional_id, servicio_id, fecha_inicio, fecha_fin, estado) VALUES (2, 2, 2, 2, '2024-04-16', '2024-04-22', 'En proceso');
INSERT INTO contratos (contrato_id, cliente_id, profesional_id, servicio_id, fecha_inicio, fecha_fin, estado) VALUES (3, 3, 3, 3, '2024-04-17', '2024-04-25', 'En proceso');

INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (1, 1, 1);
INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (2, 2, 2);
INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (3, 3, 3);
INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (4, 1, 2);
INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (5, 2, 3);
INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (6, 3, 1);

INSERT INTO herramientas (herramienta_id, nombre) VALUES (1, 'Martillo');
INSERT INTO herramientas (herramienta_id, nombre) VALUES (2, 'Destornillador');
INSERT INTO herramientas (herramienta_id, nombre) VALUES (3, 'Sierra');

INSERT INTO objetos (objeto_id, nombre, descripcion, precio) VALUES (1, 'Mesa', 'Mesa de madera', 150.00);
INSERT INTO objetos (objeto_id, nombre, descripcion, precio) VALUES (2, 'Silla', 'Silla de plástico', 30.00);
INSERT INTO objetos (objeto_id, nombre, descripcion, precio) VALUES (3, 'Lámpara', 'Lámpara de pie', 80.00);

INSERT INTO profesional_herramienta (id, profesional_id, herramienta_id, cantidad) VALUES (1, 1, 1, 4);
INSERT INTO profesional_herramienta (id, profesional_id, herramienta_id, cantidad) VALUES (2, 2, 2, 2);
INSERT INTO profesional_herramienta (id, profesional_id, herramienta_id, cantidad) VALUES (3, 3, 3, 3);