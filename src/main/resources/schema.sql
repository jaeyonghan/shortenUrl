CREATE TABLE TB_URL(
	  LONG_URL VARCHAR(100) NOT NULL,
	  SHORT_URL VARCHAR(100) NOT NULL,
      COUNT INT(11) DEFAULT 1 NOT NULL,
	  PRIMARY KEY (LONG_URL),
      INDEX TB_URL_IDX1 (SHORT_URL)
	) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;