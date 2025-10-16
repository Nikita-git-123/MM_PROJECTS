drop database ecomapp;
create database ecomapp;
use ecomapp;
show tables;

select * from address;
select * from customer;
select * from order_items;
select * from orders;
select * from product;
select * from product_category;

insert into product_category(category_name) values('Laptops');
insert into product_category(category_name) values('Mobiles');
insert into product_category(category_name) values('Shirts');
insert into product_category(category_name) values('Beauty');
insert into product_category(category_name) values('Grocery');

select * from product_category;


INSERT INTO product (title, name, description, image_url, active, unit_in_stocks,
unit_price, category_id, date_created)
VALUES ('DELL-LAPTOP-1000', 'DELL - Laptop', 'Processor: Intel Core i5-1235U 12th Generation (up to 4.40 GHz, 12MB 10 Cores)',
'assets/images/products/laptops/dell-laptop-1000.png',1,100,19.99,1, NOW());

INSERT INTO product (title, name, description, image_url, active, unit_in_stocks,
unit_price, category_id, date_created)
VALUES ('HP-LAPTOP-1001', 'HP - Laptop', 'Processor: Intel Core i5-1235U 12th Generation (up to 4.40 GHz, 12MB 10 Cores)',
'assets/images/products/laptops/hp-laptop-1001.png',1,100,59.99,1, NOW());

INSERT INTO product (title, name, description, image_url, active, unit_in_stocks,
unit_price, category_id, date_created)
VALUES ('ACER-LAPTOP-1002', 'ACER - Laptop', 'Acer Aspire Lite 12th Gen Intel Core i5-1235U Thin and Light Laptop ',
'assets/images/products/laptops/acer-laptop-1002.png',1,100,49.99,1, NOW());

INSERT INTO product (title, name, description, image_url, active, unit_in_stocks,
unit_price, category_id, date_created)
VALUES ('LENOVO-LAPTOP-1003', 'Lenovo ThinkPad E14', 'Lenovo ThinkPad E14 AMD Ryzen 5',
'assets/images/products/laptops/lenovo-laptop-1003.png',1,100,45.00,1, NOW());
 
 select * from product;
 

INSERT INTO product (title, name, description, image_url, active, unit_in_stocks,
unit_price, category_id, date_created)
VALUES ('Apple-IPhone-1000', 'Apple-Iphone', 'Apple iPhone 13 (128GB) - Green',
'assets/images/products/mobiles/apple-mobile-1000.png',1,100,59,2, NOW());

INSERT INTO product (title, name, description, image_url, active, unit_in_stocks,
unit_price, category_id, date_created)
VALUES ('RED-MI-1001', 'Redmi 13C', 'Redmi 13C (Stardust Black, 6GB RAM, 128GB Storage)',
'assets/images/products/mobiles/redmi-mobile-1001.png',1,100,29,2, NOW());

INSERT INTO product (title, name, description, image_url, active, unit_in_stocks,unit_price, category_id, date_created)
VALUES ('SAMSUNG-Galaxy-1002', 'Samsung Mobile', 'SAMSUNG Galaxy F14 5G 6GB RAM 128GB STORAGE',
'assets/images/products/mobiles/samsung-mobile-1002.png',1,100,39,2, NOW());

INSERT INTO product (title, name, description, image_url, active, unit_in_stocks,unit_price, category_id, date_created)
VALUES ('POCO C65', 'POCO C65', 'POCO C65 (Pastel Green 4GB RAM 128GB Storage)',
'assets/images/products/mobiles/poco-mobile-1003.png',1,100,49,2, NOW());


select * from product;


INSERT INTO product (title, name, description, image_url, active, unit_in_stocks,
unit_price, category_id, date_created)
VALUES ('Mens Cotton Shirt', 'Mens Cotton Shirt', 'Amazon Brand - Symbol Mens Cotton Shirt','assets/images/products/clothes/shirt-1000.png',1,100,5,3, NOW());


select * from product;