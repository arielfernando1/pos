CREATE TABLE categoria(
    idcategoria int primary key auto_increment,
    nombre_categoria varchar(30) not null
    );
	
CREATE TABLE impresion(
	idimpresion int PRIMARY KEY auto_increment,
	fecha date,
	color100 int,
	color50 int,
	bn int,
	total DECIMAL(4,2)
);

CREATE TABLE producto(
	idproducto int PRIMARY KEY auto_increment,
	idcategoria int,
	nombre varchar(64) UNIQUE,
	marca varchar(64),
	cod_prod VARCHAR(32),
	stock int,
	precio DECIMAL(4,2),
	FOREIGN key (idcategoria) REFERENCES categoria(idcategoria)
);

CREATE TABLE reg_venta(
	id_venta int PRIMARY key auto_increment,
	idproducto int,
	cantidad int not null,
	total decimal(4,2),
	fecha date,
	foreign key (idproducto) REFERENCES productos(idproducto);

);

CREATE TABLE servicio(
	idservicio int PRIMARY KEY auto_increment,
	nombre VARCHAR(64),
	precio DECIMAL(4,2),
	descripcion VARCHAR(254)
);

CREATE TABLE reg_servicio(
	id_venta int PRIMARY KEY auto_increment,
	idservicio int,
	cantidad int,
	fecha datetime,
	total decimal(5,2),
	foreign key(idservicio) REFERENCES servicio(idservicio)
);

CREATE TABLE reg_diario(
	fecha date PRIMARY KEY,
	internet decimal(4,2),
	impresiones decimal(4,2),
	ventas decimal(4,2),
);

CREATE TABLE detalle_venta (
	id_detalle int primary key auto_increment,
	id_venta int,
	idproducto int,
	cant int,
	total decimal(4,2),
	fecha datetime,
	foreign key(id_venta) references reg_venta_test(id_venta),
	foreign key(idproducto) references producto(idproducto)
);
CREATE TABLE cliente(
	cedula varchar(10) PRIMARY KEY,
	nombre_cliente varchar(100),	
);
CREATE TABLE reg_venta_test(
	id_venta int PRIMARY KEY,
	cedula varchar(10),
	fecha date
);

select x from estudiante where nombre LIKE '%NOMBRE%';