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

CREATE TABLE drugs (
    id INT AUTO_INCREMENT,
    drugName VARCHAR(30) NOT NULL,
    description VARCHAR(200),
    effectivity VARCHAR(30) NOT NULL,
    contraindication VARCHAR(50),
    PRIMARY KEY(id)    
);

CREATE TABLE patients (
    id INT AUTO_INCREMENT,
    patientName VARCHAR(25) NOT NULL,
    surname VARCHAR(25) NOT NULL,
    birthNum VARCHAR(11),
    address VARCHAR(35),
    city VARCHAR(20),
    PRIMARY KEY (id)
);

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

CREATE TABLE isWorking (
    tel NUMERIC(9),
    workingTime VARCHAR(15) NOT NULL,
    departmentNum INT NOT NULL,
    doctor VARCHAR(35) NOT NULL,
    FOREIGN KEY (departmentNum) REFERENCES department(id),
    FOREIGN KEY (doctor) REFERENCES usertable(email),
    PRIMARY KEY (departmentNum, doctor)
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

CREATE TABLE prescriptions (
    id INT AUTO_INCREMENT,
    startTime DATE NOT NULL,
    endTime DATE NOT NULL,
    dosage VARCHAR(30) NOT NULL,
    patientId INT NOT NULL,
    drugId INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (patientId) REFERENCES patients(id),
    FOREIGN KEY (drugId) REFERENCES drugs(id)
);

CREATE TABLE results (
    id INT AUTO_INCREMENT,
    resultDate DATE NOT NULL,
    resultDsc VARCHAR(150) NOT NULL,
    examinationId INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (examinationId) REFERENCES examination(id)
);

-- INSERT DOCTORS
INSERT INTO userTable (username, surname, email, roleType, password) VALUES ("admin", "admin", "admin", "root", "21232f297a57a5a743894a0e4a801fc3");
INSERT INTO userTable (username, surname, birthNum, address, city, email, tel, roleType, password)
VALUES ("Richard", "Setlelý", "903125/4513", "Pionýrská 9, 610 00", "Brno", "user", "654 214 324", "user", "ee11cbb19052e40b07aac0ca060c23ee");
INSERT INTO userTable (username, surname, birthNum, address, city, email, tel, roleType, password)
VALUES ("Ondřej", "Zátopek", "759862/1243", "Česká 69, 612 00", "Brno", "zatop@seznam.cz", "741 234 452", "user", "ee11cbb190457aac0ca060c23ee");
INSERT INTO userTable (username, surname, birthNum, address, city, email, roleType, password)
VALUES ("Jiří", "Kulda", "931245/4569", "Dírová 13, 764 00", "Martinice", "kulda@naveleslavine.cz", "user", "ee11cbb190457aac0ca060c23ee");
INSERT INTO userTable (username, surname, birthNum, city, email, roleType, password)
VALUES ("Andrea", "Zelná", "453612/2456", "Kroměříž", "zelda@example.com", "user", "ee11cbb190457aac0ca060c23ee");
INSERT INTO userTable (username, surname, birthNum, address, city, email, tel, roleType, password)
VALUES ("Tereza", "Němcová", "586532/1243", "Višňovce 619, 764 22", "Hulín", "nemc@gmail.com", "743 555 452", "user", "ee11cbb190457aac0ca060c23ee");
INSERT INTO userTable (username, surname, birthNum, address, city, email, roleType, password)
VALUES ("Oliver", "Queen", "658495/4513", "255 Second Street", "Starling City", "queen@arrow.com", "user", "ee11cbb190457aac0ca060c23ee");
INSERT INTO userTable (username, surname, birthNum, address, city, email, tel, roleType, password)
VALUES ("Petr", "Novák", "543562/4532", "Technologický park 456, 612 00", "Brno", "p.novak@seznam.cz", "412 354 124", "user", "ee11cbb190457aac0ca060c23ee");

-- INSERT DEPARTMENTS
INSERT INTO department (depName) VALUES ("ORL");
INSERT INTO department (depName) VALUES ("Ortopedie");
INSERT INTO department (depName) VALUES ("Urologie");
INSERT INTO department (depName) VALUES ("Chirurgie");
INSERT INTO department (depName) VALUES ("Traumatologie");

-- INSERT DRUGS
INSERT INTO drugs (drugName, description, effectivity, contraindication) 
VALUES ('Kinedryl', 'Lék při léčbě nevolností', 'moxastini tenoclas', 'Ospalost,slabost,podrážděnost');
INSERT INTO drugs (drugName, description, effectivity, contraindication)  
VALUES('Aspirin', 'Lék při léčbě bolesti a chřipky', 'kyselina acetylsaciliová', 'Zvracení, dušnost');
INSERT INTO drugs (drugName, description, effectivity, contraindication) 
VALUES('Paralen', 'Lék při léčbě bolesti a chřipky', 'Paracetamol', 'Ospalost,slabost');
INSERT INTO drugs (drugName, description, effectivity, contraindication) 
VALUES('Endiaron', 'Lék při léčbě průjmů', 'Cloroxinum', 'Ospalost,svalové stahy,podrážděnost');
INSERT INTO drugs (drugName, description, effectivity, contraindication) 
VALUES('Ibalgin', 'Lék při léčbě bolesti a zánětu', 'ibuprofen', 'slabost,podrážděnost');

-- INSERT PATIENTS
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
INSERT INTO patients (patientName, surname, birthNum, address, city) 
VALUES ("Stanislav", "Nechutný", "685435/4568", "Veleslavínova 592, 612 00", "Brno");

-- INSERT HOSPITALIYATION
INSERT INTO hospitalization (hospitalized, released, patientId, departmentNum, doctor)
VALUES ("2014-02-07", "2014-03-02", 2, 1, "zelda@example.com");
INSERT INTO hospitalization (hospitalized, patientId, departmentNum, doctor)
VALUES ("2014-05-12", 1, 4, "zatop@seznam.cz");
INSERT INTO hospitalization (hospitalized, released, patientId, departmentNum, doctor)
VALUES ("2014-03-27", "2014-04-1", 3, 2, "kulda@naveleslavine.cz");
INSERT INTO hospitalization (hospitalized, released, patientId, departmentNum, doctor)
VALUES ("2014-10-08", "2014-11-08", 2, 1, "zelda@example.com");
INSERT INTO hospitalization (hospitalized, patientId, departmentNum, doctor)
VALUES ("2014-12-01", 2, 1, "zelda@example.com");
INSERT INTO hospitalization (hospitalized, released, patientId, departmentNum, doctor)
VALUES ("2014-05-21", "2014-05-27", 6, 5, "p.novak@seznam.cz");

