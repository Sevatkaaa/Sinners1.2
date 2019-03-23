CREATE TABLE IF NOT EXISTS `sin_version` (
  `version` int(11) DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO `sin_version` (`version`) VALUES (1);

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  `user_name` varchar(200) NULL DEFAULT NULL,
  `password` varchar(200) NOT NULL COMMENT 'Should be crypted',
  `created_at` DATETIME NULL DEFAULT NULL,
  `validation` varchar(200) NOT NULL,
  `avatar` text NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `password_set_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_name` (`user_name`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `sins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `points` int(11) NOT NULL DEFAULT '0',
  `tag` json DEFAULT NULL,
  `title` varchar(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `verses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `chapter_number` int(11) NOT NULL,
  `verse_number` int(11) NOT NULL,
  `text` text NULL DEFAULT NULL,
  KEY `FK_verses_book_id` (`book_id`),
  CONSTRAINT `FK_verses_book_id` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `sin_face_outs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `sin_id` int(11) NOT NULL,
  `timestamp` int(11) NOT NULL COMMENT 'timestamp in ms',
  PRIMARY KEY (`id`),
  KEY `FK_sin_face_outs_sin_id` (`sin_id`),
  KEY `FK_sin_face_outs_user_id` (`user_id`),
  CONSTRAINT `FK_sin_face_outs_sin_id` FOREIGN KEY (`sin_id`) REFERENCES `sins` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_sin_face_outs_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT 'Creates when user clicks DONE on SinCard';

CREATE TABLE IF NOT EXISTS `sin_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sin_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sin_card_sin_id` (`sin_id`),
  KEY `FK_sin_card_user_id` (`user_id`),
  CONSTRAINT `FK_sin_card_sin_id` FOREIGN KEY (`sin_id`) REFERENCES `sins` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_sin_card_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `proofs_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `bible_links` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;