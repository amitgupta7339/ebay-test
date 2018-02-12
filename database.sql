STEP1:   CREATE DATABASE ebaytest ;
STEP2:   use ebaytest;
STEP3:   CREATE TABLE `user_info` (
	  `first_name` varchar(50) NOT NULL,
	  `last_name` varchar(50) NOT NULL,
	  `user_email` varchar(100) NOT NULL,
	  `user_password` varchar(50) NOT NULL,
	  PRIMARY KEY (`user_email`),
	  UNIQUE KEY `user_email_UNIQUE` (`user_email`)
	) ;
