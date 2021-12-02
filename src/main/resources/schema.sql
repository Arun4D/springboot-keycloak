CREATE TABLE customer_details (
    customer_id INT NOT NULL IDENTITY(1,1) ,
    first_name varchar(50),
    last_name varchar(50) NOT NULL,
    dob varchar(50) NOT NULL,
    email varchar(100) NOT NULL,
    phone varchar(15),
    country_code varchar(5) NOT NULL,
    building_no  varchar(5) NOT NULL,
    street varchar(100) NOT NULL,
    pin_code varchar(10) NOT NULL,
    city varchar(100) NOT NULL,
    district  varchar(100) NOT NULL,
    state  varchar(100) NOT NULL,
    country_name  varchar(100) NOT NULL,
    PRIMARY KEY (customer_id)
);
