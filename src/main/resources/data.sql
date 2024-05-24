
DELETE FROM factura_material;
DELETE FROM contratos;
DELETE FROM facturas;
DELETE FROM favoritos;
DELETE FROM clientes;
DELETE FROM profesionales;
DELETE FROM usuarios;
DELETE FROM materiales;
DELETE FROM servicios;

INSERT INTO usuarios (user_id, username, correo, password, activado, fecha_envio, codigo_activacion, role) VALUES (1, 'juan123', 'sasaji2004@gmial.com', '$2a$10$qFvk70IVphAh/q8O4.YCwOAiogSyK8ZLgwdbCX4pwI2bcBsKMfaCe', true, null, null, 'CLIENTE');
INSERT INTO usuarios (user_id, username, correo, password, activado, fecha_envio, codigo_activacion, role) VALUES (2, 'maria456', 'maria456@example.com', 'maria456pass', false, null, null, 'CLIENTE');
INSERT INTO usuarios (user_id, username, correo, password, activado, fecha_envio, codigo_activacion, role) VALUES (3, 'pedro789', 'pedro789@example.com', 'pedro789pass', false, null, null, 'CLIENTE');
INSERT INTO usuarios (user_id, username, correo, password, activado, fecha_envio, codigo_activacion, role) VALUES (4, 'marcos123', 'samper7804@gmial.com', '$2a$10$qFvk70IVphAh/q8O4.YCwOAiogSyK8ZLgwdbCX4pwI2bcBsKMfaCe', true, null, null, 'PROF');
INSERT INTO usuarios (user_id, username, correo, password, activado, fecha_envio, codigo_activacion, role) VALUES (5, 'maria4564', 'maria4564@example.com', 'maria4564pass', false, null, null, 'PROF');
INSERT INTO usuarios (user_id, username, correo, password, activado, fecha_envio, codigo_activacion, role) VALUES (6, 'pedro7894', 'pedro7894@example.com', 'pedro7894pass', false, null, null, 'PROF');
INSERT INTO usuarios (user_id, username, correo, password, activado, fecha_envio, codigo_activacion, role) VALUES (7, 'sasaji', 'samuelsanchezjw109@gmial.com', '$2a$10$qFvk70IVphAh/q8O4.YCwOAiogSyK8ZLgwdbCX4pwI2bcBsKMfaCe', true, null, null, 'ADMIN');

INSERT INTO clientes (cliente_id, nombre, apellidos, numero, user_id_c) VALUES (1, 'Ana', 'García', '123456789', 1);
INSERT INTO clientes (cliente_id, nombre, apellidos, numero, user_id_c) VALUES (2, 'Juan', 'Martínez', '987654321', 2);
INSERT INTO clientes (cliente_id, nombre, apellidos, numero, user_id_c) VALUES (3, 'María', 'López', '456789123', 3);

INSERT INTO profesionales (profesional_id, nombre, apellidos, numero, experiencia, oficio, disponibilidad, valoracion, user_id_p) VALUES (1, 'Carlos', 'Sánchez', '111222333', 5, 'Fontanero', 'Mañana', 4, 4);
INSERT INTO profesionales (profesional_id, nombre, apellidos, numero, experiencia, oficio, disponibilidad, valoracion, user_id_p) VALUES (2, 'Laura', 'Díaz', '444555666', 8, 'Electricista', 'Tarde', 5, 5);
INSERT INTO profesionales (profesional_id, nombre, apellidos, numero, experiencia, oficio, disponibilidad, valoracion, user_id_p) VALUES (3, 'Pedro', 'Gómez', '777888999', 10, 'Carpintero', 'Noche', 43, 6);

INSERT INTO servicios (servicio_id, nombre, descripcion, tarifa_base) VALUES (1, 'Reparación de Fontanería', 'Reparación de tuberías y grifos', 50.00);
INSERT INTO servicios (servicio_id, nombre, descripcion, tarifa_base) VALUES (2, 'Instalación Eléctrica', 'Instalación de sistemas eléctricos', 70.00);
INSERT INTO servicios (servicio_id, nombre, descripcion, tarifa_base) VALUES (3, 'Construcción de Muebles', 'Construcción de muebles a medida', 100.00);

INSERT INTO materiales (material_id, nombre, descripcion, precio) VALUES (1, 'Mesa', 'Mesa de madera', 150.00);
INSERT INTO materiales (material_id, nombre, descripcion, precio) VALUES (2, 'Silla', 'Silla de plástico', 30.00);
INSERT INTO materiales (material_id, nombre, descripcion, precio) VALUES (3, 'Lámpara', 'Lámpara de pie', 80.00);

INSERT INTO facturas (factura_id, cliente_id_f, profesional_id_f, servicio_id_f, precio, estado) VALUES (1, 1, 1, 1, 20.5, 'pendiente');
INSERT INTO facturas (factura_id, cliente_id_f, profesional_id_f, servicio_id_f, precio, estado) VALUES (2, 2, 2, 2, 100.5, 'aceptado');
INSERT INTO facturas (factura_id, cliente_id_f, profesional_id_f, servicio_id_f, precio, estado) VALUES (3, 3, 3, 3, 60.5, 'vigente');

INSERT INTO contratos (contrato_id, cliente_id_c, profesional_id_c, servicio_id_c, fecha_inicio, fecha_fin, estado) VALUES (1, 1, 1, 1, '2024-04-15', '2024-04-20', 'pendiente');
INSERT INTO contratos (contrato_id, cliente_id_c, profesional_id_c, servicio_id_c, fecha_inicio, fecha_fin, estado) VALUES (2, 2, 2, 2, '2024-04-16', '2024-04-22', 'pendiente');
INSERT INTO contratos (contrato_id, cliente_id_c, profesional_id_c, servicio_id_c, fecha_inicio, fecha_fin, estado) VALUES (3, 3, 3, 3, '2024-04-17', '2024-04-25', 'pagada');

INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (1, 1, 1);
INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (2, 2, 2);
INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (3, 3, 3);
INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (4, 1, 2);
INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (5, 2, 3);
INSERT INTO favoritos (id, cliente_id, profesional_id) VALUES (6, 3, 1);

INSERT INTO factura_material (id, factura_id, material_id, cantidad) VALUES (1, 1, 1, 4);
INSERT INTO factura_material (id, factura_id, material_id, cantidad) VALUES (2, 2, 2, 2);
INSERT INTO factura_material (id, factura_id, material_id, cantidad) VALUES (3, 3, 3, 3);
INSERT INTO factura_material (id, factura_id, material_id, cantidad) VALUES (4, 1, 2, 1);
INSERT INTO factura_material (id, factura_id, material_id, cantidad) VALUES (5, 2, 3, 6);
INSERT INTO factura_material (id, factura_id, material_id, cantidad) VALUES (6, 3, 1, 9);