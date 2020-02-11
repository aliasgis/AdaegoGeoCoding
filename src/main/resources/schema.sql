CREATE SEQUENCE IF NOT EXISTS SEQ;

CREATE TABLE IF NOT EXISTS RESULTADDR (
	result_addr_id 			BIGINT NOT NULL default SEQ.nextval,
	address   	            VARCHAR(500) NOT NULL,
	x 		                DOUBLE NULL,
	y		                DOUBLE NULL,
	PRIMARY KEY (result_addr_id)
);