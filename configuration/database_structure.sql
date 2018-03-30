CREATE DATABASE `wallet_hub`;

CREATE TABLE `wallet_hub`.`logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(45) DEFAULT NULL,
  `status_code` int(11) DEFAULT NULL,
  `user_agent` varchar(200) DEFAULT NULL,
  `http_method` varchar(15) DEFAULT NULL,
  `request_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `threshold_searches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `threshold_actual` int(11) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);