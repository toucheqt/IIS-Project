CREATE TABLE prescriptions (
    id INT AUTO_INCREMENT,
    startTime DATE NOT NULL,
    endTime DATE NOT NULL,
    dosage VARCHAR(30) NOT NULL,
    patientId INT NOT NULL,
    drugId INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (patientId) REFERENCES patient(id),
    FOREIGN KEY (drugId) REFERENCES drugs(id)
);


