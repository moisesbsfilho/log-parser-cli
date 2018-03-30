CREATE  TABLE `wallet_hub`.`threshold_searches` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `threshold` INT NULL ,
  `ip` VARCHAR(15) NULL ,
  `startDate` TIMESTAMP NULL ,
  `endDate` TIMESTAMP NULL ,
  `comment` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) );
