create database BuyTopiaDataBase;

CREATE TABLE BuyTopiaDataBase.customers (
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE BuyTopiaDataBase.sellers (
    username VARCHAR(50),
    password VARCHAR(50),
    approved BOOLEAN
);

CREATE TABLE BuyTopiaDataBase.technicians (
    username VARCHAR(50),
    password VARCHAR(50),
    approved BOOLEAN
);

CREATE TABLE BuyTopiaDataBase.products (
    product_id INT,
    name VARCHAR(100),
    type VARCHAR(50),
    items INT ,
    price INT,
    description TEXT,
    seller_name VARCHAR(50)
);

CREATE TABLE BuyTopiaDataBase.services (
    service_id INT,
    name VARCHAR(100),
    price INT,
    description TEXT,
    technician_name VARCHAR(50)
);

CREATE TABLE BuyTopiaDataBase.product_transactions (
    transaction_id INT,
    seller_name VARCHAR(50),
    customer_name VARCHAR(50),
    product_id INT,
    approved BOOLEAN,
    rating DOUBLE,
    user_cancelled BOOLEAN,
    admin_cancelled_approved BOOLEAN
);

CREATE TABLE BuyTopiaDataBase.service_transactions (
    transaction_id INT,
    technician_name VARCHAR(50),
    customer_name VARCHAR(50),
    service_id INT,
    approved BOOLEAN,
    rating DOUBLE
);






