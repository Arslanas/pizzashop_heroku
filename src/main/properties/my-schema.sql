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
  "SHOPPINGCART_ID" bigint NOT NULL,
)