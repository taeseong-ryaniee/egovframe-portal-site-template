-- Basic DB sanity: current DB and core tables/views
SELECT DATABASE() AS current_db;

-- Key tables should exist and have rows after seed
SELECT 'LETTNBBSMASTER' AS table_name, COUNT(*) AS cnt FROM LETTNBBSMASTER;
SELECT 'LETTNBBS' AS table_name, COUNT(*) AS cnt FROM LETTNBBS;
SELECT 'LETTCCMMNCLCODE' AS table_name, COUNT(*) AS cnt FROM LETTCCMMNCLCODE;
SELECT 'LETTCCMMNCODE' AS table_name, COUNT(*) AS cnt FROM LETTCCMMNCODE;
SELECT 'LETTNFAQINFO' AS table_name, COUNT(*) AS cnt FROM LETTNFAQINFO;
SELECT 'LETTNSTPLATINFO' AS table_name, COUNT(*) AS cnt FROM LETTNSTPLATINFO;

-- Views present?
SHOW FULL TABLES WHERE Table_type = 'VIEW';

-- Check the COMVNUSERMASTER view DDL if present
SHOW CREATE VIEW COMVNUSERMASTER;

