CREATE TABLE `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50),	
  `lastName` varchar(50),
  `patroNymic` varchar(50),
  `addDate` DATE,
  `phone` varchar(15),
  `address` varchar(80),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `dent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11),	
  `toothNumber` int(3),
  `healingDate` date,
  `dentCondition` int(5),
  `comment` varchar(600),
  PRIMARY KEY (`id`),
  FOREIGN KEY (patient_id) REFERENCES `patient`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11),	
  `dateTime` datetime,
  `comment` varchar(600),
  `duration` tinyint(1),
  `status` tinyint(1),
  PRIMARY KEY (`id`),
  FOREIGN KEY (patient_id) REFERENCES `patient`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
