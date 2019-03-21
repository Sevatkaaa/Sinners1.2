UPDATE `sin_version` SET `version`=2 WHERE `version`=1 LIMIT 1;

CREATE TABLE IF NOT EXISTS `proofs_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `bible_links` text NOT NULL
  #`sin_id` int(11) NOT NULL,
  #`verse_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  #KEY `FK_proofs_sin_id` (`sin_id`),
  #KEY `FK_proofs_verse_id` (`verse_id`),
  #CONSTRAINT `FK_proofs_sin_id` FOREIGN KEY (`sin_id`) REFERENCES `sins` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  #CONSTRAINT `FK_proofs_verse_id` FOREIGN KEY (`verse_id`) REFERENCES `verses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8;