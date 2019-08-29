CREATE TABLE IF NOT EXISTS SMART_MOCKS
(
  ID NUMBER NOT NULL,
  UPDATE_TIME DATE NOT NULL,
  REQUEST_NAME VARCHAR (256),
  REG_EXPRESSION VARCHAR (1024),
  RESPONSE_BODY TEXT,

  PRIMARY KEY(ID)
);
CREATE INDEX IF NOT EXISTS INDEX_SM_REQ_NAME ON SMART_MOCKS(REQUEST_NAME);

CREATE SEQUENCE IF NOT EXISTS SEQ_SM_ID start with 1 increment by 1 CACHE 10;
