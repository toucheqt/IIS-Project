CREATE TABLE userTable (
    username VARCHAR(25) NOT NULL,
    surname VARCHAR(25) NOT NULL,
    birthNum VARCHAR(11),
    address VARCHAR(35),
    city VARCHAR(20),
    email VARCHAR(30) UNIQUE NOT NULL,
    tel NUMERIC(9),
    roleType VARCHAR(4) NOT NULL,
    password VARCHAR(40) NOT NULL,
    PRIMARY KEY (email)
);

INSERT INTO userTable (username, email, roleType, password) VALUES ("admin", "admin", "root", "21232f297a57a5a743894a0e4a801fc3");
