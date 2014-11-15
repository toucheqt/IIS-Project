CREATE TABLE isWorking (
    tel NUMERIC(9) NOT NULL,
    workingTime VARCHAR(15) NOT NULL,
    departmentNum INT NOT NULL,
    doctor VARCHAR(25) NOT NULL,
    FOREIGN KEY (departmentNum) REFERENCES department(id),
    FOREIGN KEY (doctor) REFERENCES usertable(email)
);
