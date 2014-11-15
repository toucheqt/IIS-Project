CREATE TABLE userTable (
    username VARCHAR(25) NOT NULL,
    surname VARCHAR(25) NOT NULL,
    birthNum VARCHAR(11) NOT NULL,
    address VARCHAR(35) NOT NULL,
    city VARCHAR(20) NOT NULL,
    email VARCHAR(30) UNIQUE NOT NULL,
    tel NUMERIC(9),
    roleType VARCHAR(4) NOT NULL,
    password VARCHAR(40) NOT NULL,
    PRIMARY KEY (email)
);
