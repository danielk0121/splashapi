CREATE TABLE `splash` (
	`seq` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`createUserId` INT(11) NOT NULL DEFAULT '0',
	`token` VARCHAR(10) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
	`roomId` VARCHAR(10) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
	`splashAmount` DOUBLE(22,0) NOT NULL DEFAULT '0',
	`splashUserCount` INT(11) NOT NULL,
	`createdDate` DATETIME NULL DEFAULT current_timestamp(),
	PRIMARY KEY (`seq`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;
