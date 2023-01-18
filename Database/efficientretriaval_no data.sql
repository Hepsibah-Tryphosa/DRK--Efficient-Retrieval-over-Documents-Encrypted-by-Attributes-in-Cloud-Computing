/*
SQLyog - Free MySQL GUI v5.0
Host - 5.0.16-nt : Database - efficient
*********************************************************************
Server version : 5.0.16-nt
*/


create database if not exists `efficient`;

USE `efficient`;

/*Table structure for table `datafiles` */

DROP TABLE IF EXISTS `datafiles`;

CREATE TABLE `datafiles` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `publickey` varchar(50) NOT NULL,
  `branch` varchar(50) NOT NULL,
  `deptment` varchar(50) NOT NULL,
  `subdepot` varchar(50) NOT NULL,
  `jobrole` varchar(50) NOT NULL,
  `filename` varchar(50) NOT NULL,
  `filedata` longtext NOT NULL,
  `frequency` float default NULL,
  `cipherdata` longtext NOT NULL,
  `wordindexs` longtext NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `dataowner` */

DROP TABLE IF EXISTS `dataowner`;

CREATE TABLE `dataowner` (
  `id` int(10) NOT NULL auto_increment,
  `username` varchar(100) default NULL,
  `password` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `mobile` varchar(100) default NULL,
  `branch` varchar(100) default NULL,
  `department` varchar(100) default NULL,
  `subdept` varchar(100) default NULL,
  `jobrole` varchar(100) default NULL,
  `status` varchar(10) default NULL,
  `publicKey` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `datauser` */

DROP TABLE IF EXISTS `datauser`;

CREATE TABLE `datauser` (
  `id` int(10) NOT NULL auto_increment,
  `username` varchar(100) default NULL,
  `password` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `mobile` varchar(100) default NULL,
  `branch` varchar(100) default NULL,
  `department` varchar(100) default NULL,
  `subdept` varchar(100) default NULL,
  `jobrole` varchar(100) default NULL,
  `status` varchar(10) default NULL,
  `publicKey` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `request` */

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `fileid` int(10) NOT NULL auto_increment,
  `userid` int(10) default NULL,
  `username` varchar(50) default NULL,
  `filename` varchar(50) default NULL,
  `status` varchar(50) default NULL,
  `SecretKey` varchar(50) default NULL,
  `pubkeys` varchar(80) default NULL,
  PRIMARY KEY  (`fileid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
