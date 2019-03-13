/* Borrar, crear y usar base de datos SGEDB*/
drop database sgedb;

create database sgedb;

use sgedb;

/* Eliminar tablas */ 
DROP table actuadores;
DROP TABLE reglas;
DROP TABLE sensores;
DROP TABLE fabricantedispositivointeligente;
DROP TABLE estados_dispositivos_inteligentes;
DROP TABLE dispositivos;
DROP TABLE dispositivos_disponibles;
DROP TABLE transformador_por_domicilio;
DROP TABLE transformadores_activos;
DROP TABLE domicilios;
DROP table zonas;
DROP TABLE clientes;
DROP TABLE administradores;
DROP TABLE hibernate_sequence;

/* Seleccionar tablas */ 
SELECT * FROM administradores;
SELECT * FROM clientes;
SELECT * FROM domicilios;
SELECT * FROM transformadores_activos;
SELECT * FROM transformador_por_domicilio;
SELECT * FROM zonas;
SELECT * FROM dispositivos;
SELECT * FROM dispositivos_disponibles;
SELECT * FROM estados_dispositivos_inteligentes;
SELECT * FROM actuadores;
SELECT * FROM reglas;
SELECT * FROM sensores;
SELECT * FROM fabricantedispositivointeligente;

--------------------------------------------------------

/* Insertamos algunas reglas más... */
INSERT INTO  reglas(DTYPE, descripcion, id_sensor)
VALUES ('ReglaTemperaturaMayorA20Grados', 'Apagar luz patio si la luminosidad desciende 20 cd', 1);

UPDATE reglas
SET id_regla = 1
WHERE id_dispositivo = 2;


---------------------------------------------------------

/* 1. SIMULAMOS FUNCIONAMIENTO DE LOS DISPOSITIVOS */ 

-- Limpiamos só-lo la tabla de estados 
SET foreign_key_checks =0;
TRUNCATE estados_dispositivos_inteligentes;
SET foreign_key_checks =1;
-- Cargamos estados de prueba para el dispositivo 1
INSERT INTO estados_dispositivos_inteligentes(estado, fecha_hora, id_dispositivo_inteligente) 
VALUES
	('ENCENDIDO', '2017-03-15 17:05:00', 1),
	('APAGADO', '2017-03-25 17:00:00', 1),
	('ENCENDIDO', '2017-05-20 17:05:00', 1),
	('APAGADO', '2017-05-24 18:00:00', 1),
	('ENCENDIDO', '2017-08-13 22:00:00', 1),
	('APAGADO', '2017-08-16 22:30:00', 1),
	('ENCENDIDO', '2017-11-05 16:00:00', 1),
	('APAGADO', '2017-11-08 18:30:00', 1),
    ('ENCENDIDO', '2018-12-02 16:00:00', 1),
	('APAGADO', '2018-12-04 18:30:00', 1),
	('ENCENDIDO', '2018-12-08 16:00:00', 1),
	('APAGADO', '2018-12-10 18:30:00', 1),
	('ENCENDIDO', '2018-12-15 16:00:00', 1),
	('APAGADO', '2018-12-17 18:30:00', 1),
	('ENCENDIDO', '2018-12-24 16:00:00', 1),
	('APAGADO', '2018-12-28: 17:00:00', 1);
    
-- Cargamos estados de prueba para el dispositivo 2
INSERT INTO estados_dispositivos_inteligentes(estado, fecha_hora, id_dispositivo_inteligente) 
VALUES
	('ENCENDIDO', '2017-03-15 17:05:00', 2),
	('APAGADO', '2017-03-16 17:00:00', 2),
	('ENCENDIDO', '2017-04-20 17:05:00', 2),
	('APAGADO', '2017-05-20 19:30:00', 2),
	('ENCENDIDO', '2017-06-13 21:00:00', 2),
	('APAGADO', '2017-06-16 20:30:00', 2),
	('ENCENDIDO', '2017-11-05 16:00:00', 2),
	('APAGADO', '2017-11-07 17:30:00', 2),
    ('ENCENDIDO', '2018-12-04 15:00:00', 2),
	('APAGADO', '2018-12-04 18:30:00', 2),
	('ENCENDIDO', '2018-12-08 16:00:00', 2),
	('APAGADO', '2018-12-09 18:00:00', 2),
	('ENCENDIDO', '2018-12-11 16:00:00', 2),
	('APAGADO', '2018-12-13 18:30:00', 2),
	('ENCENDIDO', '2018-12-25 16:00:00', 2),
	('APAGADO', '2018-12-30: 17:00:00', 2);
/****************/

/* 2. PRUEBAS PARA SIMPLEX */ 
UPDATE dispositivos
SET consumo_kw_por_hora = 3.0
WHERE id_dispositivo = 2;


/****************/

CALL minutos_entre_fechas('2017-05-18', '2017-07-26', @resultado);
SELECT @resultado;

CALL minutos_encendido(1, '2018-12-01', '2018-12-31', @resultado);
SELECT @resultado;

SELECT * 
FROM estados_dispositivos_inteligentes
WHERE id_dispositivo_inteligente =1 
	AND fecha_hora BETWEEN '2018-10-18' AND '2018-10-26'; 

UPDATE dispositivos_inteligentes SET nombre_generico = 'LED 32 Inteligente' 
WHERE id_dispositivo = 1;

------------------------------------------------------------------------------

-- Inserto un cliente nuevo
INSERT INTO Clientes (id_cliente, nombre, apellido, usuario, password, ahorro_automatico, cantidad_puntos, numero_documento, tipo_documento)
VALUES (2, 'Lisandro', 'Lopez', 'licha', 'racing', 0, 50, 30241754, 'dni');

-- Inserto un administrador nuevo
INSERT INTO Administradores (id_admin, nombre, apellido, usuario, password, direccion, fecha_alta)
VALUES (3, 'Eduardo', 'Coudet', 'admin', '123', '', '2017-12-12');

-- Cambio el password de licha
UPDATE Clientes
SET password = '123456'
WHERE id_cliente = '2'

