UPDATE `sin_version` SET `version`=2 WHERE `version`=1 LIMIT 1;

ALTER TABLE `chapters`
  ADD COLUMN `url` text NULL DEFAULT NULL,
  DROP COLUMN `title`;

ALTER TABLE `verses`
  ADD COLUMN `verse_id` int(11) NOT NULL AFTER `id`;