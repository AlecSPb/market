-- MySQL Script generated by MySQL Workbench
-- Thu Jul 19 00:58:04 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema eshopdatabase
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema eshopdatabase
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eshopdatabase` DEFAULT CHARACTER SET latin1 ;
USE `eshopdatabase` ;

-- -----------------------------------------------------
-- Table `eshopdatabase`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshopdatabase`.`customer` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`firstname` VARCHAR(45) NULL DEFAULT NULL,
`secondname` VARCHAR(45) NULL DEFAULT NULL,
`email` VARCHAR(45) NOT NULL,
`parole` VARCHAR(200) NOT NULL,
`phonenumber` VARCHAR(45) NULL DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `E-mail_UNIQUE` (`email` ASC),
UNIQUE INDEX `id_UNIQUE` (`id` ASC),
UNIQUE INDEX `phonenumber_UNIQUE` (`phonenumber` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `eshopdatabase`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshopdatabase`.`address` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`postcode` INT(11) NOT NULL,
`country` VARCHAR(45) NOT NULL,
`region` VARCHAR(45) NULL DEFAULT NULL,
`city` VARCHAR(45) NOT NULL,
`street` VARCHAR(45) NOT NULL,
`building` VARCHAR(45) NOT NULL,
`apartment` VARCHAR(45) NOT NULL,
`customer_id` INT(11) NULL DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id_UNIQUE` (`id` ASC),
INDEX `addresscustomer_idx` (`customer_id` ASC),
CONSTRAINT `addresscustomer`
FOREIGN KEY (`customer_id`)
REFERENCES `eshopdatabase`.`customer` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `eshopdatabase`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshopdatabase`.`role` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`role_name` VARCHAR(45) NULL DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `eshopdatabase`.`customer_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshopdatabase`.`customer_roles` (
`customer_id` INT(11) NOT NULL,
`role_id` INT(11) NOT NULL,
INDEX `customerrolecustomer_idx` (`customer_id` ASC),
INDEX `customerrolerole_idx` (`role_id` ASC),
CONSTRAINT `customerrolecustomer`
FOREIGN KEY (`customer_id`)
REFERENCES `eshopdatabase`.`customer` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
CONSTRAINT `customerrolerole`
FOREIGN KEY (`role_id`)
REFERENCES `eshopdatabase`.`role` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `eshopdatabase`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshopdatabase`.`orders` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`customer_id` INT(12) NOT NULL,
`address_id` INT(11) NULL DEFAULT NULL,
`payment_method` VARCHAR(45) NULL DEFAULT NULL,
`orders_status` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id`),
INDEX `orderproductorder_idx` (`id` ASC),
INDEX `orderaddress_idx` (`address_id` ASC),
INDEX `ordercustomer_idx` (`customer_id` ASC),
CONSTRAINT `orderaddress`
FOREIGN KEY (`address_id`)
REFERENCES `eshopdatabase`.`address` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
CONSTRAINT `ordercustomer`
FOREIGN KEY (`customer_id`)
REFERENCES `eshopdatabase`.`customer` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `eshopdatabase`.`product_parameter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshopdatabase`.`product_parameter` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`brand` VARCHAR(55) NOT NULL,
`color` VARCHAR(55) NOT NULL,
`weight` INT(11) NOT NULL,
PRIMARY KEY (`id`),
INDEX `parameterproductparameter_idx` (`color` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `eshopdatabase`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshopdatabase`.`product` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`title` VARCHAR(50) NOT NULL,
`description` VARCHAR(800) NOT NULL,
`price` INT(11) NOT NULL,
`category` VARCHAR(40) NOT NULL,
`parameter_id` INT(11) NOT NULL,
`count` INT(11) NULL DEFAULT NULL,
`notavailable` TINYINT(1) NULL DEFAULT '0',
PRIMARY KEY (`id`),
INDEX `productproductparameter_idx` (`parameter_id` ASC),
CONSTRAINT `productparameterproduct`
FOREIGN KEY (`parameter_id`)
REFERENCES `eshopdatabase`.`product_parameter` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `eshopdatabase`.`product_orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshopdatabase`.`product_orders` (
`product_id` INT(11) NOT NULL,
`orders_id` INT(11) NOT NULL,
`product_count` INT(10) NULL DEFAULT NULL,
PRIMARY KEY (`product_id`, `orders_id`),
INDEX `productorderorder_idx` (`orders_id` ASC),
CONSTRAINT `productorderorder`
FOREIGN KEY (`orders_id`)
REFERENCES `eshopdatabase`.`orders` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
CONSTRAINT `productorderproduct`
FOREIGN KEY (`product_id`)
REFERENCES `eshopdatabase`.`product` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
