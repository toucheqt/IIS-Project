CREATE TABLE nurse (
    id INT AUTO_INCREMENT,
    username VARCHAR(25) NOT NULL,
    surname VARCHAR(25) NOT NULL,
    birthNum VARCHAR(11),
    address VARCHAR(35),
    city VARCHAR(20),
    departmentNum INT NOT NULL,
    PRIMARY KEY (id)
    FOREIGN KEY (departmentNum) REFERENCES department(id)
);
