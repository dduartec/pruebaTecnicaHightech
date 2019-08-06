-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema users
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `users` ;

-- -----------------------------------------------------
-- Schema users
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `users` DEFAULT CHARACTER SET utf8 ;
USE `users` ;

-- -----------------------------------------------------
-- Table `users`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users`.`User` ;

CREATE TABLE IF NOT EXISTS `users`.`User` (
  `username` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `users`.`CreditCard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users`.`CreditCard` ;

CREATE TABLE IF NOT EXISTS `users`.`CreditCard` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(16) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `expirationDate` VARCHAR(5) NOT NULL,
  `securityCode` VARCHAR(45) NOT NULL,
  `User_username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`, `User_username`),
  INDEX `fk_CreditCard_User_idx` (`User_username` ASC) VISIBLE,
  CONSTRAINT `fk_CreditCard_User`
    FOREIGN KEY (`User_username`)
    REFERENCES `users`.`User` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
