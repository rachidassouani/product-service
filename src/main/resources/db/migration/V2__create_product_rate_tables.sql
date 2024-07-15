CREATE TABLE product (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    title varchar(100) NOT NULL,
    subtitle varchar(300),
    description varchar(1000) DEFAULT NULL,
    price float NOT NULL);

CREATE TABLE rate (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    title varchar(100),
    comment varchar(300) ,
    number_of_starts int,
    product_id bigint NOT NULL,
    KEY `FKrwqxo7w4ksxh4g3qgjdqdl4w5` (`product_id`),
    CONSTRAINT `FKrwqxo7w4ksxh4g3qgjdqdl4w5` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`));