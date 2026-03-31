CREATE DATABASE IF NOT EXISTS temp_chat;

USE temp_chat;

CREATE TABLE IF NOT EXISTS `user` (
   `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `username` varchar(255) DEFAULT NULL,
   `passwd` varchar(255) DEFAULT NULL,
   `fName` varchar(255) DEFAULT NULL,
   `lName` varchar(255) DEFAULT NULL
 );

CREATE TABLE IF NOT EXISTS `group_list` (
`id` bigint NOT NULL PRIMARY_KEY AUTO_INCREMENT,
`group_name` varchar(255) DEFAULT NULL,
`date_created` date DEFAULT NULL,
`group_code` varchar(20) DEFAULT NULL,
`groupCode` varchar(255) DEFAULT NULL
)

CREATE TABLE IF NOT EXISTS `message` (
   `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `message` varchar(255) DEFAULT NULL,
   `group_id` bigint DEFAULT NULL,
   `sent_by` bigint DEFAULT NULL,
   CONSTRAINT `fk_message_user` FOREIGN KEY (`sent_by`) REFERENCES `user` (`id`),
   CONSTRAINT `fk_message_group_list` FOREIGN KEY (`group_id`) REFERENCES `group_list` (`id`)
 ) ;

