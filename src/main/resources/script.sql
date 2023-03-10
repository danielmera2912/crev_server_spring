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
    `clave` VARCHAR(45) NOT NULL,
    `correo` VARCHAR(100) NOT NULL,
    `fecha_creacion` DATE NOT NULL,
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
    PRIMARY KEY (`id`),
    INDEX `fk_evento_ciudad1_idx` (`ciudad_id` ASC) VISIBLE,
    INDEX `fk_evento_deporte1_idx` (`deporte_id` ASC) VISIBLE,
    CONSTRAINT `fk_evento_ciudad1`
    FOREIGN KEY (`ciudad_id`)
    REFERENCES `crev`.`ciudad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_evento_deporte1`
    FOREIGN KEY (`deporte_id`)
    REFERENCES `crev`.`deporte` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_usuario_has_evento_evento1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `crev`.`evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_usuario_has_equipo_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `crev`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- Tabla usuario: --
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion) VALUES (1, 'Juan', '1990-01-01', 'avatar1.jpg', 'clave1', 'juan@gmail.com', '2023-03-09');
INSERT INTO crev.usuario (id, nombre, fecha_nacimiento, avatar, clave, correo, fecha_creacion) VALUES (2, 'María', '1995-05-05', 'avatar2.jpg', 'clave2', 'maria@gmail.com', '2023-03-09');

-- Tabla ciudad: --
INSERT INTO crev.ciudad (id, nombre) VALUES (1, 'Barcelona');
INSERT INTO crev.ciudad (id, nombre) VALUES (2, 'Madrid');

-- Tabla deporte: --
INSERT INTO crev.deporte (id, nombre) VALUES (1, 'Fútbol');
INSERT INTO crev.deporte (id, nombre) VALUES (2, 'Baloncesto');

-- Tabla evento: --
INSERT INTO crev.evento (id, hora, fecha, ciudad_id, deporte_id, fecha_creacion) VALUES (1, '15:00', '2023-03-12', 1, 1, '2023-03-09');
INSERT INTO crev.evento (id, hora, fecha, ciudad_id, deporte_id, fecha_creacion) VALUES (2, '20:00', '2023-03-13', 2, 2, '2023-03-09');

-- Tabla equipo: --
INSERT INTO crev.equipo (id, nombre, escudo, evento_id) VALUES (1, 'Equipo 1', 'escudo1.jpg', 1);
INSERT INTO crev.equipo (id, nombre, escudo, evento_id) VALUES (2, 'Equipo 2', 'escudo2.jpg', 1);

-- Tabla usuario_has_evento: --
INSERT INTO crev.usuario_has_evento (id, usuario_id, evento_id) VALUES (1, 1, 1);
INSERT INTO crev.usuario_has_evento (id, usuario_id, evento_id) VALUES (2, 2, 1);

-- Tabla usuario_has_equipo: --
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (1, 1, 1);
INSERT INTO crev.usuario_has_equipo (id, usuario_id, equipo_id) VALUES (2, 2, 2);