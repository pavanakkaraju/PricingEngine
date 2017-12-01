DELIMITER $$
CREATE DATABASE IF NOT EXISTS SQLTEST;
USE SQLTEST;
DROP FUNCTION IF EXISTS FN_FN_FORMINITCAP;
CREATE FUNCTION FN_FORMINITCAP(inputStr varchar(512))
returns varchar(512)
BEGIN
/* Declare initial variables */
DECLARE lowerInput VARCHAR(512);
DECLARE counter INT;
DECLARE word VARCHAR(512);
DECLARE outputSentence VARCHAR(512);

/*
Logic 
1. Convert the whole sentence into lower case
2. Split on <space>
3. Capitalise first letter of each word
4. Concanate
 */
	SET outputSentence='';
	SET lowerInput=LCASE(inputStr);
	SET counter=INSTR(lowerInput,' ');

WHILE counter >0
DO
	SET word=SUBSTRING_INDEX(lowerInput,' ',1);
	SET outputSentence = CONCAT(outputSentence,CONCAT(UCASE(LEFT(word, 1)),SUBSTRING(word, 2)));
	SET outputSentence =CONCAT(outputSentence,' ');
	SET lowerInput = trim(substring(lowerInput,length(word)+1));
	SET counter=length(lowerInput);
END WHILE;

RETURN outputSentence;
END$$
DELIMITER ;