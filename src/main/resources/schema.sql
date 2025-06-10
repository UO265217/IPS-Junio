--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada aplicacion se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
-- drop table Carreras;
drop table Envios;
drop table HistorialEnvios;

-- create table Carreras (id int primary key not null, inicio date not null, fin date not null, fecha date not null, descr varchar(32), check(inicio<=fin), check(fin<fecha));

create table Envios (
    id integer primary key autoincrement,
    numero_seguimiento int not null,
    origen varchar(32) not null,
    destino varchar(32) not null,
    estado_actual varchar(16) not null
);

create table HistorialEnvios (
    id integer primary key autoincrement,
    id_envio integer not null,

    fecha_hora text not null,
    descripcion_evento varchar(255) not null,

    foreign key (id_envio) references Envios(id)
);