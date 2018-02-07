DROP TABLE IF EXISTS PURCHASINGS;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS GOODS;

CREATE TABLE IF NOT EXISTS USERS (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  USERNAME VARCHAR(60) NOT NULL,
  PASSWORD  VARCHAR(12) NOT NULL,
  ROLE VARCHAR(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS GOODS (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR(60) NOT NULL,
  DESCRIPTION VARCHAR(60),
  PRICE DOUBLE NOT NULL,
  QUANTITY INTEGER
);

CREATE TABLE IF NOT EXISTS PURCHASINGS (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  USER_ID INTEGER NOT NULL,
  GOOD_ID INTEGER NOT NULL,
  PRICE INTEGER NOT NULL,
  QUANTITY INTEGER,
  CONSTRAINT FK_PUR_USER FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
  CONSTRAINT FK_PUR_GOOD FOREIGN KEY (GOOD_ID) REFERENCES GOODS(ID)
);