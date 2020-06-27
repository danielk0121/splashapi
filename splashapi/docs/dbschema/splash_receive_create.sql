CREATE TABLE `splash_receive` (
	`seq` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`splashSeq` INT(11) NOT NULL,
	`finishYn` VARCHAR(1) NOT NULL DEFAULT 'N' COLLATE 'latin1_swedish_ci',
	`receiveUserId` INT(11) NULL DEFAULT NULL,
	`receiveAmount` DOUBLE(22,0) NOT NULL DEFAULT '0',
	`regDate` DATETIME NOT NULL DEFAULT current_timestamp(),
	`updDate` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`seq`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;
