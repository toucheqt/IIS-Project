CREATE TABLE isWorking (
    tel NUMERIC(9),
    workingTime VARCHAR(15) NOT NULL,
    departmentNum INT NOT NULL,
    doctor VARCHAR(35) NOT NULL,
    FOREIGN KEY (departmentNum) REFERENCES department(id),
    FOREIGN KEY (doctor) REFERENCES usertable(email),
    PRIMARY KEY (departmentNum, doctor)
);
