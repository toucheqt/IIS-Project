CREATE TABLE examination (
    id INT AUTO_INCREMENT,
    description VARCHAR(150) NOT NULL,
    examTime DATE NOT NULL,
    hospitalizationId INT NOT NULL,
    doctor VARCHAR(35),
    PRIMARY KEY (id),
    FOREIGN KEY (hospitalizationId) REFERENCES hospitalization(id),
    FOREIGN KEY (doctor) REFERENCES usertable(email)
);
