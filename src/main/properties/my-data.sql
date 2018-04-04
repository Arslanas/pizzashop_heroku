INSERT INTO category("NAME", "ORDERS") VALUES ('pizzas', 1);
INSERT INTO category("NAME", "ORDERS") VALUES ('chickens', 2);
INSERT INTO category("NAME", "ORDERS") VALUES ('dessert', 3);

INSERT INTO category_item("ITEM_ID", "CATEGORY_ID") VALUES (1, 1);
INSERT INTO category_item("ITEM_ID", "CATEGORY_ID") VALUES (1, 2);
INSERT INTO category_item("ITEM_ID", "CATEGORY_ID") VALUES (1, 3);
INSERT INTO category_item("ITEM_ID", "CATEGORY_ID") VALUES (2, 2);

INSERT INTO ITEM ("NAME", "PRICE", "DESCRIPTION") VALUES ('pizza', 455, 'vkusno');
INSERT INTO ITEM ("NAME", "PRICE", "DESCRIPTION") VALUES ('salat', 200, 'vkusno ochen');

INSERT INTO TEMP ("NAME") VALUES ('pizza');

INSERT INTO shoppingcart ("USERNAME", "PRICE", "DATE", "CURRENCY") VALUES ('Svetlana', 4800, '18-03-01 07:51:18', 'RUB');

INSERT INTO Products ("ITEM_ID", "SHOPPINGCART_ID", "QUANTITY") VALUES (1,1,5);
INSERT INTO Products ("ITEM_ID", "SHOPPINGCART_ID", "QUANTITY") VALUES (2,1,12)
