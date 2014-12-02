CREATE TABLE results (
    id INT AUTO_INCREMENT,
    resultDate DATE NOT NULL,
    resultDsc VARCHAR(150) NOT NULL,
    examinationId INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (examinationId) REFERENCES examination(id)
);

