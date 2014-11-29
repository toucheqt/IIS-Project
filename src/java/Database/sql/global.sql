CREATE TABLE userTable (
    username VARCHAR(25) NOT NULL,
    surname VARCHAR(25) NOT NULL,
    birthNum VARCHAR(11),
    address VARCHAR(35),
    city VARCHAR(20),
    email VARCHAR(35) UNIQUE NOT NULL,
    tel VARCHAR(15),
    roleType VARCHAR(4) NOT NULL,
    password VARCHAR(40) NOT NULL,
    PRIMARY KEY (email)
);

CREATE TABLE department (
    id INT AUTO_INCREMENT,
    depName VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE nurse (
    id INT AUTO_INCREMENT,
    username VARCHAR(25) NOT NULL,
    surname VARCHAR(25) NOT NULL,
    birthNum VARCHAR(11),
    address VARCHAR(35),
    city VARCHAR(20),
    departmentNum INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (departmentNum) REFERENCES department(id)
);

CREATE TABLE isWorking (
    tel NUMERIC(9),
    workingTime VARCHAR(15) NOT NULL,
    departmentNum INT NOT NULL,
    doctor VARCHAR(35) NOT NULL,
    FOREIGN KEY (departmentNum) REFERENCES department(id),
    FOREIGN KEY (doctor) REFERENCES usertable(email),
    PRIMARY KEY (departmentNum, doctor)
);


INSERT INTO department (depName) VALUES ("ORL");
INSERT INTO department (depName) VALUES ("Ortopedie");
INSERT INTO department (depName) VALUES ("Urologie");
INSERT INTO department (depName) VALUES ("Chirurgie");
INSERT INTO department (depName) VALUES ("Traumatologie");

INSERT INTO userTable (username, surname, email, roleType, password) VALUES ("admin", "admin", "admin", "root", "21232f297a57a5a743894a0e4a801fc3");

