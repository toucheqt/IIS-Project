CREATE TABLE patients (
    id INT AUTO_INCREMENT,
    patientName VARCHAR(25) NOT NULL,
    surname VARCHAR(25) NOT NULL,
    birthNum VARCHAR(11),
    address VARCHAR(35),
    city VARCHAR(20),
    PRIMARY KEY (id)
);

INSERT INTO patients (patientName, surname, birthNum, address, city) 
VALUES ("Pavel", "Rezavý", "745612/3245", "Jungmanova 92, 612 00", "Brno");

INSERT INTO patients (patientName, surname, birthNum, address, city) 
VALUES ("Zuzana", "Kve", "936521/4513", "Palackého třída 22, 612 00", "Brno");

INSERT INTO patients (patientName, surname, birthNum, address, city) 
VALUES ("Lian", "Yu", "846235/1534", "Chacif 2, 446500", "Hong Kong");

INSERT INTO patients (patientName, surname, birthNum, address, city) 
VALUES ("Tereza", "Havlířková", "856423/3245", "Kartouzská 4865, 612 00", "Brno");

INSERT INTO patients (patientName, surname, birthNum, address, city) 
VALUES ("Dušan", "Novák", "953625/4521", "Husitská 486, 612 00", "Brno");

INSERT INTO patients (patientName, surname, birthNum, address, city) 
VALUES ("Igor", "Hnízdo", "426587/5245", "Skácelova 41, 612 00", "Brno");

INSERT INTO patients (patientName, surname, birthNum, address, city) 
VALUES ("Oliver", "Fiat", "559542/4584", "Moravské náměstí 592, 612 00", "Brno");



