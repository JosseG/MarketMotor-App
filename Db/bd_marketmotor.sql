drop database if exists marketmotordb;
-- creamos la bd
create database marketmotordb;
-- activamos la bd
use marketmotordb;



create table if not exists tb_rol
(
    id_rol int auto_increment primary key,
    nombre_rol varchar(20) unique not null,
    estado boolean not null
);


create table if not exists tb_usuario
(
    id_usuario int auto_increment primary key,
    id_rol int not null,
    alias_usuario varchar(20) unique  not null,
    contrasena_usuario varchar(20) not null,
    estado boolean not null,
    foreign key(id_rol) references tb_rol(id_rol)
);

create table if not exists tb_producto
(
	id_producto int auto_increment primary key,
    descripcion_producto varchar(40) not null,
    tipo_producto varchar(40) not null,
    serial_producto char(12) not null,
    marca_producto varchar(40) not null
);

create table if not exists tb_empleado
(
	id_empleado int auto_increment primary key,
    id_usuario int not null,
    nombre_empleado varchar(40) not null,
    apellidoPat_empleado varchar(40) not null,
    apellidoMat_empleado varchar(40) not null,
    telefono_empleado varchar(15) not null,
    correo_empleado varchar(60) not null,
    foreign key (id_usuario) references tb_usuario(id_usuario)
);


create table if not exists tb_proveedor
(
	id_proveedor int auto_increment primary key,
    id_usuario int not null,
    razon_social_proveedor varchar(40) not null,
    nombre_comercial_proveedor varchar(40) not null,
    numero_ruc_proveedor varchar(40) not null,
    correo_proveedor varchar(15) not null,
    direccion_proveedor varchar(60) not null,
    departamento_proveedor varchar(60) not null,
    telefono_proveedor varchar(15) not null,
    foreign key (id_usuario) references tb_usuario(id_usuario)
);

create table if not exists tb_permiso
(
	id_permiso int auto_increment primary key,
    tipo_permiso varchar(10) not null /*READ, UPDATE, INSERT, DELETE*/
);

create table if not exists tb_permiso_rol
(
	id_permiso int,
    id_rol int,
    primary key(id_permiso,id_rol),
    foreign key(id_permiso) references tb_permiso(id_permiso),
    foreign key(id_rol) references tb_rol(id_rol)
);


create table if not exists tb_o_compra
(
	id_o_compra int auto_increment primary key,
    id_proveedor int not null,
    id_empleado int not null,
    numero_o_compra char(12) not null,
    fecha_o_compra datetime not null,
    valort_o_compra float not null,
    foreign key(id_proveedor) references tb_proveedor(id_proveedor),
    foreign key(id_empleado) references tb_empleado(id_empleado)
);


create table if not exists tb_det_o_compra
(
	id_det_o_compra int auto_increment primary key,
    id_o_compra int not null,
    id_producto int not null,
    cantidad_det_o_compra int not null,
    preciou_det_o_compra float not null,
    foreign key(id_o_compra) references tb_o_compra(id_o_compra),
    foreign key(id_producto) references tb_producto(id_producto)
);


create table if not exists tb_inf_salida
(
	id_inf_salida int auto_increment primary key,
    id_empleado int not null,
    id_proveedor int not null,
    numero_inf_salida char(12) not null,
    fecha_inf_salida datetime not null,
    fechallegada_inf_salida datetime not null,
    foreign key(id_empleado) references tb_empleado(id_empleado),
    foreign key(id_proveedor) references tb_proveedor(id_proveedor)
);


create table if not exists tb_reporte_accion
(
	id_reporte_accion int auto_increment primary key,
    id_empleado int not null,
    descripcion_reporte_accion varchar(80) not null,
    foreign key(id_empleado) references tb_empleado(id_empleado)
);

create table if not exists tb_estado_app
(
	id_estado_app int auto_increment primary key,
    descripcion_estado_app varchar(20) not null /* Mantenimiento - Activo - Inactivo*/
);


create table if not exists tb_chat
(
	id_chat int auto_increment primary key
);


create table if not exists tb_mensaje
(
	id_mensaje int auto_increment primary key,
	id_chat int not null,
    id_usuario int not null,
    descripcion_mensaje varchar(255) not null,
    fecha_mensaje datetime not null,
    foreign key(id_chat) references tb_chat(id_chat),
    foreign key(id_usuario) references tb_usuario(id_usuario)
);


create table if not exists tb_participantes_chat
(
	id_chat int not null,
    id_usuario int not null,
    primary key(id_chat,id_usuario),
    foreign key(id_chat) references tb_chat(id_chat),
    foreign key(id_usuario) references tb_usuario(id_usuario)
);





