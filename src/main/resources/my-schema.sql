CREATE TABLE TEMP (
  "ID" BIGINT   AUTO_INCREMENT PRIMARY KEY,
  "NAME" varchar(50)  NOT NULL
);
CREATE TABLE ITEM (
  "ID" BIGINT  AUTO_INCREMENT PRIMARY KEY,
  "NAME" varchar(50)  NOT NULL,
  "PRICE" DECIMAL NOT NULL,
  "DESCRIPTION" varchar(115) DEFAULT NULL,
  "CURRENCY" varchar(15) DEFAULT 'RUB',
  "IMAGE" MEDIUMBLOB DEFAULT  NULL
);
CREATE TABLE CATEGORY (
  "ID" INTEGER NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  "NAME" varchar(50) NOT NULL,
  "ORDERS" INTEGER NOT NULL
);
CREATE TABLE CATEGORY_ITEM (
  "CATEGORY_ID" INTEGER NOT NULL,
  "ITEM_ID" BIGINT NOT NULL,
  PRIMARY KEY ("ITEM_ID", "CATEGORY_ID")
);
CREATE TABLE shoppingcart (
  "ID"         BIGINT    NOT NULL AUTO_INCREMENT PRIMARY KEY,
  "USERNAME"   VARCHAR   NOT NULL,
  "CURRENCY"   VARCHAR   NOT NULL,
  "PRICE" DECIMAL    NOT NULL,
  "DATE"       TIMESTAMP NOT NULL
);
CREATE TABLE products (
  "ID" bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  "ITEM_ID" bigint NOT NULL,
  "QUANTITY" int NOT NULL,
  "SHOPPINGCART_ID" bigint NOT NULL
);
CREATE TABLE users
(
    USERNAME varchar(50) PRIMARY KEY NOT NULL,
    PASSWORD varchar(50) NOT NULL,
    ENABLED TINYINT NOT NULL,
    PHONENUM varchar(50) NOT NULL,
    EMAIL varchar(50),
    STREETHOME varchar(50) NOT NULL,
    LEVEL int(11),
    ENTRANCE int(11),
    APPARTMENT int(11),
    DATE timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE authorities
(
  ID bigint  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  USERNAME varchar(20) NOT NULL,
  AUTHORITY varchar(50) NOT NULL,
  CONSTRAINT authorities_users_USERNAME_fk FOREIGN KEY (USERNAME) REFERENCES users (USERNAME) ON UPDATE CASCADE
)
