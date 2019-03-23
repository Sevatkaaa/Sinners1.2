UPDATE `sin_version` SET `version`=4 WHERE `version`=3 LIMIT 1;

ALTER TABLE `chapters`
  ADD COLUMN `chapter_number` INT(11) NOT NULL AFTER `book_id`;

ALTER TABLE `verses`
  CHANGE COLUMN `verse_id` `verse_number` INT(11) NOT NULL AFTER `id`;#,
  #DROP COLUMN `id`,
  #DROP PRIMARY KEY,
  #ADD PRIMARY KEY (, t2ID)