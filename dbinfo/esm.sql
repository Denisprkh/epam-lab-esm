-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema esm
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema esm
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `esm` DEFAULT CHARACTER SET utf8 ;
USE `esm` ;

-- -----------------------------------------------------
-- Table `esm`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `esm`.`tag` (
  `tag_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`tag_id`),
  UNIQUE INDEX `id_UNIQUE` (`tag_id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `esm`.`gift_certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `esm`.`gift_certificate` (
  `gift_certificate_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL NOT NULL,
  `create_date` TIMESTAMP NOT NULL,
  `last_update_date` TIMESTAMP NOT NULL,
  `duration` INT NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`gift_certificate_id`),
  UNIQUE INDEX `id_UNIQUE` (`gift_certificate_id` ASC) VISIBLE,
  UNIQUE INDEX `Name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `esm`.`gift_certificates_tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `esm`.`gift_certificates_tags` (
  `tag_id` INT NOT NULL,
  `gift_certificate_id` INT NOT NULL,
  PRIMARY KEY (`tag_id`, `gift_certificate_id`),
  INDEX `fk_lnk_tags_gift_certificates_tags_idx` (`tag_id` ASC) VISIBLE,
  INDEX `fk_lnk_tags_gift_certificates_gift_certificates1_idx` (`gift_certificate_id` ASC) VISIBLE,
  CONSTRAINT `fk_lnk_tags_gift_certificates_tags`
    FOREIGN KEY (`tag_id`)
    REFERENCES `esm`.`tag` (`tag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lnk_tags_gift_certificates_gift_certificates1`
    FOREIGN KEY (`gift_certificate_id`)
    REFERENCES `esm`.`gift_certificate` (`gift_certificate_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;