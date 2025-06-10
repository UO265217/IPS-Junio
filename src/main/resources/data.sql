--Datos para carga inicial de la base de datos

--Para giis.demo.tkrun:
-- delete from carreras;
-- insert into carreras(id,inicio,fin,fecha,descr) values
-- 	(100,'2016-10-05','2016-10-25','2016-11-09','finalizada'),
-- 	(101,'2016-10-05','2016-10-25','2016-11-10','en fase 3'),
-- 	(102,'2016-11-05','2016-11-09','2016-11-20','en fase 2'),
-- 	(103,'2016-11-10','2016-11-15','2016-11-21','en fase 1'),
-- 	(104,'2016-11-11','2016-11-15','2016-11-22','antes inscripcion');

-- ENVÍOS
insert into Envios ( numero_seguimiento, origen, destino, estado_actual) values
    ( 1001, "Madrid", "Oviedo", "EN_TRANSITO"),
    ( 1002, "Oviedo", "Madrid", "EN_REPARTO"),
    ( 1003, "Barcelona", "Valencia", "ENTREGADO");

insert into HistorialEnvios ( id_envio, fecha_hora, descripcion_evento) values
    ( 1, '2024-07-28 09:00:00', 'Solicitud de envío creada'),
    ( 1, '2024-07-28 14:30:00', 'Paquete recogido en origen'),
    ( 1, '2024-07-28 18:00:00', 'Llegada al almacén de Madrid'),
    ( 1, '2024-07-29 08:15:00', 'Salida del almacén de Madrid'),

    ( 2, '2024-07-27 10:00:00', 'Solicitud de envío creada'),
    ( 2, '2024-07-27 11:30:00', 'Paquete recogido en origen'),
    ( 2, '2024-07-27 15:00:00', 'En tránsito'),
    ( 2, '2024-07-28 09:45:00', 'En reparto'),

    ( 3, '2024-07-27 10:00:00', 'Solicitud de envío creada'),
    ( 3, '2024-07-27 11:30:00', 'Paquete recogido en origen'),
    ( 3, '2024-07-27 15:00:00', 'En tránsito'),
    ( 3, '2024-07-28 09:45:00', 'Entregado en destino');