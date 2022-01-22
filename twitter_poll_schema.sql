CREATE DATABASE  IF NOT EXISTS `twitterpoll`;
USE `twitterpoll`;

DROP TABLE IF EXISTS `poll`;
CREATE TABLE `poll` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_date` datetime DEFAULT NULL,
  `live` tinyint(1) DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_poll_user_idx` (`created_by`),
  CONSTRAINT `fk_poll_user` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `poll_choice`;
CREATE TABLE `poll_choice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `vote` bigint(20) NOT NULL DEFAULT '0',
  `poll_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_poll_poll_choices` (`poll_id`),
  CONSTRAINT `fk_poll_poll_choices` FOREIGN KEY (`poll_id`) REFERENCES `poll` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user_poll_choice`;
CREATE TABLE `user_poll_choice` (
  `poll_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`poll_id`,`user_id`),
  KEY `fk_user_poll_choice_user` (`user_id`),
  CONSTRAINT `fk_user_poll_choice_poll` FOREIGN KEY (`poll_id`) REFERENCES `poll` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_poll_choice_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
