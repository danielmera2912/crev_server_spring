-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema crev
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema crev
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `crev` DEFAULT CHARACTER SET utf8 ;
USE `crev` ;

-- -----------------------------------------------------
-- Table `crev`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crev`.`usuario` (
                                                `id` INT NOT NULL AUTO_INCREMENT,
                                                `nombre` VARCHAR(45) NOT NULL,
    `fecha_nacimiento` DATE NOT NULL,
    `avatar` VARCHAR(100) NOT NULL,
    `clave` VARCHAR(60) NOT NULL,
    `correo` VARCHAR(100) NOT NULL,
    `fecha_creacion` DATE NOT NULL,
    `fecha_modificacion` DATE NOT NULL,
    `rol` ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE,
    UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crev`.`ciudad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crev`.`ciudad` (
                                               `id` INT NOT NULL AUTO_INCREMENT,
                                               `nombre` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crev`.`deporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crev`.`deporte` (
                                                `id` INT NOT NULL AUTO_INCREMENT,
                                                `nombre` VARCHAR(45) NOT NULL,
    `equipos` BOOLEAN NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crev`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crev`.`evento` (
                                               `id` INT NOT NULL AUTO_INCREMENT,
                                               `hora` VARCHAR(45) NOT NULL,
    `fecha` DATE NOT NULL,
    `ciudad_id` INT NOT NULL,
    `deporte_id` INT NOT NULL,
    `fecha_creacion` DATE NOT NULL,
    `estado` VARCHAR(45) NOT NULL,
    `puntos_local` INT NULL,
    `puntos_visitante` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_evento_ciudad1_idx` (`ciudad_id` ASC) VISIBLE,
    INDEX `fk_evento_deporte1_idx` (`deporte_id` ASC) VISIBLE,
    CONSTRAINT `fk_evento_ciudad1`
    FOREIGN KEY (`ciudad_id`)
    REFERENCES `crev`.`ciudad` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_evento_deporte1`
    FOREIGN KEY (`deporte_id`)
    REFERENCES `crev`.`deporte` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crev`.`equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crev`.`equipo` (
                                               `id` INT NOT NULL AUTO_INCREMENT,
                                               `nombre` VARCHAR(45) NOT NULL,
    `escudo` VARCHAR(100) NOT NULL,
    `evento_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_equipo_evento1_idx` (`evento_id` ASC) VISIBLE,
    CONSTRAINT `fk_equipo_evento1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `crev`.`evento` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crev`.`usuario_has_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crev`.`usuario_has_evento` (
                                                           `id` INT NOT NULL AUTO_INCREMENT,
                                                           `usuario_id` INT NOT NULL,
                                                           `evento_id` INT NOT NULL,
                                                           PRIMARY KEY (`id`),
    INDEX `fk_usuario_has_evento_evento1_idx` (`evento_id` ASC) VISIBLE,
    INDEX `fk_usuario_has_evento_usuario_idx` (`usuario_id` ASC) VISIBLE,
    CONSTRAINT `fk_usuario_has_evento_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `crev`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_usuario_has_evento_evento1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `crev`.`evento` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crev`.`usuario_has_equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crev`.`usuario_has_equipo` (
                                                           `id` INT NOT NULL AUTO_INCREMENT,
                                                           `usuario_id` INT NOT NULL,
                                                           `equipo_id` INT NOT NULL,
                                                           PRIMARY KEY (`id`),
    INDEX `fk_usuario_has_equipo_equipo1_idx` (`equipo_id` ASC) VISIBLE,
    INDEX `fk_usuario_has_equipo_usuario1_idx` (`usuario_id` ASC) VISIBLE,
    CONSTRAINT `fk_usuario_has_equipo_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `crev`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_usuario_has_equipo_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `crev`.`equipo` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




-- Tabla usuario: --
INSERT INTO crev.usuario (nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES ('Plaza vacante', '0001-01-01', 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png', 'noaccesible', 'noaccesible', '0001-01-01', '0001-01-01');
UPDATE crev.usuario SET id = 0 WHERE id = 1;
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (1, 'Juan34', '1990-01-01', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'juan@gmail.com', '2000-03-09', '2000-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (2, 'Maria1', '1995-05-05', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'maria@gmail.com', '2000-03-09', '2000-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (3, 'Pepito', '1995-05-05', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'pepito@gmail.com', '2000-03-09', '2000-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (4, 'danimera', '1995-05-05', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'danimera@g.com', '2000-03-09', '2000-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (5, 'manug', '1995-05-05', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'manug@gmail.com', '2000-03-09', '2000-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (6, 'abcd', '1995-05-05', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'abc@g.com', '2000-03-09', '2000-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (7, 'julia23', '1990-01-01', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'julia23@gmail.com', '2000-03-09', '2000-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (8, 'laura3893', '1995-05-05', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'laura3893@gmail.com', '2000-03-09', '2000-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (9, 'manolo', '1995-05-05', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'manolo@gmail.com', '2000-03-09', '2000-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (10, 'villa', '1995-05-05', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'villa@g.com', '2000-03-09', '2000-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (11, 'oreoo', '1995-05-05', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'oreoo@gmail.com', '2000-03-09', '2000-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion, fecha_modificacion) VALUES (12, 'patatass', '1995-05-05', 'https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600', '$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS', 'patatass@g.com', '2000-03-09', '2000-03-09');


-- Tabla ciudad: --
INSERT INTO crev.ciudad(nombre) VALUES
                                    ('Álava'),
                                    ('Albacete'),
                                    ('Alicante'),
                                    ('Almería'),
                                    ('Asturias'),
                                    ('Ávila'),
                                    ('Badajoz'),
                                    ('Barcelona'),
                                    ('Burgos'),
                                    ('Cáceres'),
                                    ('Cádiz'),
                                    ('Cantabria'),
                                    ('Castellón'),
                                    ('Ciudad Real'),
                                    ('Córdoba'),
                                    ('Cuenca'),
                                    ('Gerona'),
                                    ('Granada'),
                                    ('Guadalajara'),
                                    ('Guipúzcoa'),
                                    ('Huelva'),
                                    ('Huesca'),
                                    ('Islas Balears'),
                                    ('Jaén'),
                                    ('La Coruña'),
                                    ('La Rioja'),
                                    ('Las Palmas'),
                                    ('León'),
                                    ('Lérida'),
                                    ('Lugo'),
                                    ('Madrid'),
                                    ('Málaga'),
                                    ('Murcia'),
                                    ('Navarra'),
                                    ('Orense'),
                                    ('Palencia'),
                                    ('Pontevedra'),
                                    ('Salamanca'),
                                    ('Santa Cruz de Tenerife'),
                                    ('Segovia'),
                                    ('Sevilla'),
                                    ('Soria'),
                                    ('Tarragona'),
                                    ('Teruel'),
                                    ('Toledo'),
                                    ('Valencia'),
                                    ('Valladolid'),
                                    ('Vizcaya'),
                                    ('Zamora'),
                                    ('Zaragoza');
-- Tabla deporte: --
INSERT INTO crev.deporte (id, nombre, equipos) VALUES (1, 'Fútbol Sala', true);
INSERT INTO crev.deporte (id, nombre, equipos) VALUES (2, 'Baloncesto', true);
INSERT INTO crev.deporte (id, nombre, equipos) VALUES (3, 'Pádel', false);
INSERT INTO crev.deporte (id, nombre, equipos) VALUES (4, 'Tenis', false);
INSERT INTO crev.deporte (id, nombre, equipos) VALUES (5, 'Esgrima', false);
INSERT INTO crev.deporte (id, nombre, equipos) VALUES (6, 'Boxeo', false);
INSERT INTO crev.deporte (id, nombre, equipos) VALUES (7, 'Golf', false);
INSERT INTO crev.deporte (id, nombre, equipos) VALUES (8, 'Voleibol', true);

-- Tabla evento: --
INSERT INTO crev.evento (id, hora, fecha, ciudad_id, deporte_id, estado, fecha_creacion) VALUES (1, '15:00', '2023-03-12', 1, 1, "EN CURSO", '2023-03-09');
INSERT INTO crev.evento (id, hora, fecha, ciudad_id, deporte_id, estado, fecha_creacion) VALUES (2, '20:00', '2023-03-13', 2, 2, "EN CURSO",'2023-03-09');
INSERT INTO crev.evento (id, hora, fecha, ciudad_id, deporte_id, estado, fecha_creacion) VALUES (3, '20:00', '2023-03-13', 1, 2, "EN CURSO",'2023-03-09');
INSERT INTO crev.evento (id, hora, fecha, ciudad_id, deporte_id, estado, fecha_creacion) VALUES (4, '12:30', '2023-03-23', 3, 3, "EN CURSO",'2023-03-09');

-- Tabla equipo: --
INSERT INTO crev.equipo (id, nombre, escudo, evento_id) VALUES (1, 'Ciervo Verde', 'https://i.ibb.co/fYRFPbh/ciervoverde.png', 1);
INSERT INTO crev.equipo (id, nombre, escudo, evento_id) VALUES (2, 'Ballena Azul', 'https://i.ibb.co/k9LNHCX/ballenazul.png', 1);
INSERT INTO crev.equipo (id, nombre, escudo, evento_id) VALUES (3, 'Ciervo Verde', 'https://i.ibb.co/fYRFPbh/ciervoverde.png', 2);
INSERT INTO crev.equipo (id, nombre, escudo, evento_id) VALUES (4, 'Ballena Azul', 'https://i.ibb.co/k9LNHCX/ballenazul.png', 2);
INSERT INTO crev.equipo (id, nombre, escudo, evento_id) VALUES (5, 'Ciervo Verde', 'https://i.ibb.co/fYRFPbh/ciervoverde.png', 3);
INSERT INTO crev.equipo (id, nombre, escudo, evento_id) VALUES (6, 'Ballena Azul', 'https://i.ibb.co/k9LNHCX/ballenazul.png', 3);
-- Tabla usuario_has_evento: --
INSERT INTO crev.usuario_has_evento (id, usuario_id, evento_id) VALUES (1, 1, 1);
INSERT INTO crev.usuario_has_evento (id, usuario_id, evento_id) VALUES (2, 2, 1);
INSERT INTO crev.usuario_has_evento (id, usuario_id, evento_id) VALUES (3, 1, 2);
INSERT INTO crev.usuario_has_evento (id, usuario_id, evento_id) VALUES (4, 2, 2);
INSERT INTO crev.usuario_has_evento (id, usuario_id, evento_id) VALUES (5, 3, 3);
INSERT INTO crev.usuario_has_evento (id, usuario_id, evento_id) VALUES (6, 5, 3);
INSERT INTO crev.usuario_has_evento (id, usuario_id, evento_id) VALUES (7, 3, 4);
INSERT INTO crev.usuario_has_evento (id, usuario_id, evento_id) VALUES (8, 5, 4);

-- Tabla usuario_has_equipo: --
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (1, 1, 1);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (2, 2, 2);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (3, 3, 1);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (4, 4, 2);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (5, 0, 1);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (6, 0, 2);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (7, 0, 1);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (8, 0, 2);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (9, 0, 1);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (10, 0, 2);

INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (11, 1, 3);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (12, 2, 4);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (13, 3, 3);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (14, 4, 4);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (15, 0, 3);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (16, 0, 4);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (17, 0, 3);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (18, 0, 4);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (19, 0, 3);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (20, 0, 4);

INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (21, 1, 5);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (22, 2, 6);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (23, 3, 5);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (24, 4, 6);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (25, 0, 5);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (26, 0, 6);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (27, 0, 5);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (28, 0, 6);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (29, 0, 5);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (30, 0, 6);