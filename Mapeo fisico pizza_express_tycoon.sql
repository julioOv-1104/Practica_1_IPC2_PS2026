
CREATE DATABASE  pizza_express_tycoon;

USE pizza_express_tycoon;

CREATE TABLE rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE sucursal (
    id_sucursal INT AUTO_INCREMENT PRIMARY KEY,
    nombre_sucursal VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    id_rol INT NOT NULL,
    id_sucursal INT,

    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES rol(id_rol),

    CONSTRAINT fk_usuario_sucursal  FOREIGN KEY (id_sucursal)  REFERENCES sucursal(id_sucursal)
) ;

CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE sucursal_producto (
    id_sucursal_producto INT AUTO_INCREMENT PRIMARY KEY,
    id_sucursal INT NOT NULL,
    id_producto INT NOT NULL,
    estado_activo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_sp_sucursal  FOREIGN KEY (id_sucursal)  REFERENCES sucursal(id_sucursal),

    CONSTRAINT fk_sp_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto),

    CONSTRAINT uq_sucursal_producto UNIQUE (id_sucursal, id_producto)
);

CREATE TABLE config_nivel (
    id_nivel INT AUTO_INCREMENT PRIMARY KEY,
    numero_nivel INT NOT NULL UNIQUE,
    tiempo_base INT NOT NULL COMMENT 'Tiempo base en segundos por pedido'
);

CREATE TABLE partida (
    id_partida INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_sucursal INT NOT NULL,
    puntaje_total INT NOT NULL DEFAULT 0,
    nivel_alcanzado INT NOT NULL DEFAULT 1,

    CONSTRAINT fk_partida_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),

    CONSTRAINT fk_partida_sucursal FOREIGN KEY (id_sucursal) REFERENCES sucursal(id_sucursal)
);

CREATE TABLE estado (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    nombre_estado VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_partida INT NOT NULL,
    tiempo_limite INT NOT NULL COMMENT 'Tiempo límite en segundos',
    id_estado INT NOT NULL DEFAULT 1,

    CONSTRAINT fk_pedido_partida FOREIGN KEY (id_partida) REFERENCES partida(id_partida),

    CONSTRAINT fk_pedido_estado FOREIGN KEY (id_estado) REFERENCES estado(id_estado)
);

CREATE TABLE detalle_pedido (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,

    CONSTRAINT fk_detalle_pedido FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),

    CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE historial_estado (
    id_historial INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_estado INT NOT NULL,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_historial_pedido FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),

    CONSTRAINT fk_historial_estado FOREIGN KEY (id_estado) REFERENCES estado(id_estado)
);

-- llenado de tablas necesarias para el funcionamiento de la Base de Datos

INSERT INTO rol (nombre_rol) VALUES
('JUGADOR'),
('ADMIN_TIENDA'),
('SUPER_ADMIN');

INSERT INTO estado (nombre_estado) VALUES
('RECIBIDA'),
('PREPARANDO'),
('EN_HORNO'),
('ENTREGADO'),
('CANCELADA'),
('NO_ENTREGADO');

INSERT INTO config_nivel (numero_nivel, tiempo_base) VALUES
(1, 60),
(2, 50),
(3, 40); 

-- Datos de prueba para el funcionamiento basico de la base de datos

INSERT INTO sucursal (nombre_sucursal) VALUES ('Sucursal Xela');

INSERT INTO usuario (nombre, id_rol, id_sucursal) VALUES 
('Julio',1, null),
('Allan',2,1),
('Ovalle',3,null);
