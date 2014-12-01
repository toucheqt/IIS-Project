CREATE TABLE hospitalization (
    id INT AUTO_INCREMENT,
    hospitalized DATE NOT NULL,
    released DATE,
    patientId INT NOT NULL,
    departmentNum INT NOT NULL,
    doctor VARCHAR(35) NOT NULL,
    FOREIGN KEY (patientId) REFERENCES patients(id),
    FOREIGN KEY (departmentNum) REFERENCES department(id),
    FOREIGN KEY (doctor) REFERENCES usertable(email),
    PRIMARY KEY (id)
);
