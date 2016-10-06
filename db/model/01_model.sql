-- MySQL Script generated by MySQL Workbench
-- Wed Oct  5 08:30:31 2016
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema rfd_local
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rfd_local
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rfd_local` DEFAULT CHARACTER SET utf8 ;
USE `rfd_local` ;

-- -----------------------------------------------------
-- Table `Country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Country` ;

CREATE TABLE IF NOT EXISTS `Country` (
  `country_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `code` VARCHAR(2) NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE INDEX `unq_ctry_code` (`code` ASC),
  UNIQUE INDEX `unq_ctry_name` (`name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 10000000;


-- -----------------------------------------------------
-- Table `City`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `City` ;

CREATE TABLE IF NOT EXISTS `City` (
  `city_id` BIGINT UNSIGNED NOT NULL,
  `country_id` BIGINT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`city_id`),
  INDEX `fk_city_cntry_idx` (`country_id` ASC),
  UNIQUE INDEX `unq_city_cntry` (`name` ASC, `country_id` ASC),
  CONSTRAINT `fk_City_Country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `Country` (`country_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Building`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Building` ;

CREATE TABLE IF NOT EXISTS `Building` (
  `building_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `city_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`building_id`),
  INDEX `fk_bldng_city_idx` (`city_id` ASC),
  UNIQUE INDEX `unq_bldng_city` (`city_id` ASC, `name` ASC),
  CONSTRAINT `fk_Building_City1`
    FOREIGN KEY (`city_id`)
    REFERENCES `City` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Location` ;

CREATE TABLE IF NOT EXISTS `Location` (
  `location_id` BIGINT UNSIGNED NOT NULL,
  `city_id` BIGINT UNSIGNED NULL,
  `name` VARCHAR(45) NOT NULL,
  `country_id` BIGINT UNSIGNED NULL,
  `building_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`location_id`),
  INDEX `fk_loc_city_idx` (`city_id` ASC),
  INDEX `fk_loc_cntry_idx` (`country_id` ASC),
  UNIQUE INDEX `unq_loc_name` (`name` ASC),
  INDEX `fk_loc_bldng_idx` (`building_id` ASC),
  CONSTRAINT `fk_Location_City`
    FOREIGN KEY (`city_id`)
    REFERENCES `City` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Location_Country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `Country` (`country_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Location_Building1`
    FOREIGN KEY (`building_id`)
    REFERENCES `Building` (`building_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Person` ;

CREATE TABLE IF NOT EXISTS `Person` (
  `person_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  PRIMARY KEY (`person_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10000000;


-- -----------------------------------------------------
-- Table `ContractType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ContractType` ;

CREATE TABLE IF NOT EXISTS `ContractType` (
  `contract_type_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(1) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`contract_type_id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 10000000;


-- -----------------------------------------------------
-- Table `Resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Resource` ;

CREATE TABLE IF NOT EXISTS `Resource` (
  `resource_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `person_id` BIGINT UNSIGNED NOT NULL,
  `billRate` DECIMAL(6,2) NULL,
  `location_id` BIGINT UNSIGNED NULL,
  `contract_type_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`resource_id`),
  INDEX `fk_res_per_idx` (`person_id` ASC),
  INDEX `fk_resource_location1_idx` (`location_id` ASC),
  INDEX `fk_resource_contracttype1_idx` (`contract_type_id` ASC),
  CONSTRAINT `fk_resource_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `Person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `Location` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_contracttype1`
    FOREIGN KEY (`contract_type_id`)
    REFERENCES `ContractType` (`contract_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10000000;


-- -----------------------------------------------------
-- Table `LocationRate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LocationRate` ;

CREATE TABLE IF NOT EXISTS `LocationRate` (
  `location_id` BIGINT UNSIGNED NOT NULL,
  `contract_type_id` BIGINT UNSIGNED NOT NULL,
  `rate` DECIMAL(6,2) NULL,
  `location_rate_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  INDEX `fk_locationrate_location1_idx` (`location_id` ASC),
  INDEX `fk_locationrate_contracttype1_idx` (`contract_type_id` ASC),
  PRIMARY KEY (`location_rate_id`),
  CONSTRAINT `fk_locationrate_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `Location` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_locationrate_contracttype1`
    FOREIGN KEY (`contract_type_id`)
    REFERENCES `ContractType` (`contract_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10000000;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `ContractType`
-- -----------------------------------------------------
START TRANSACTION;
USE `rfd_local`;
INSERT INTO `ContractType` (`contract_type_id`, `code`, `description`) VALUES (1, 'M', 'Managed Service');
INSERT INTO `ContractType` (`contract_type_id`, `code`, `description`) VALUES (2, 'C', 'Contractor');
INSERT INTO `ContractType` (`contract_type_id`, `code`, `description`) VALUES (3, 'P', 'Permanent');

COMMIT;

