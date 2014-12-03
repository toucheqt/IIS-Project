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
    patientId INT NOT NULL,
    doctor VARCHAR(35) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (patientId) REFERENCES patients(id),
    FOREIGN KEY (doctor) REFERENCES usertable(email)
);

CREATE TABLE isWorking (
    tel VARCHAR(15),
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
    endTime DATE,
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

-- INSERT NURSES
INSERT INTO nurse (username, surname, birthNum, address, city, departmentNum)
VALUES ("Lenka", "Páčková", "926453/1354", "Na Louce 13, 611 00", "Šlapanice", 1);
INSERT INTO nurse (username, surname, birthNum, address, city, departmentNum)
VALUES ("Pavlína", "Orlová", "856645/5642", "Příčná 245, 612 00", "Brno", 2);
INSERT INTO nurse (username, surname, birthNum, address, city, departmentNum)
VALUES ("Stanislav", "Nechtěný", "936542/4521", "Božetěchova 5, 612 00", "Brno", 5);
INSERT INTO nurse (username, surname, birthNum, city, departmentNum)
VALUES ("Tereza", "Zelná", "756423/7254", "Kroměříž", 3);
INSERT INTO nurse (username, surname, birthNum, address, city, departmentNum)
VALUES ("Anna", "Bočková", "854621/5432", "ˇŘečkovice 5641, 612 00", "Brno", 4);
INSERT INTO nurse (username, surname, birthNum, departmentNum)
VALUES ("Matylda", "Zett", "845612/5454", 2);
INSERT INTO nurse (username, surname, birthNum, address, city, departmentNum)
VALUES ("Barbora", "Svatá", "846532/4568", "Hrnčířská 12/134, 612 00", "Brno", 1);

-- INSERT ISWORKING
INSERT INTO isworking (tel, workingTime, departmentNum, doctor)
VALUES ("576 234 516", "Plný úvazek", 1, "zelda@example.com");
INSERT INTO isworking (tel, workingTime, departmentNum, doctor)
VALUES ("586 456 452", "Plný úvazek", 2, "kulda@naveleslavine.cz");  
INSERT INTO isworking (workingTime, departmentNum, doctor)
VALUES ("Internship", 2, "queen@arrow.com");  
INSERT INTO isworking (tel, workingTime, departmentNum, doctor)
VALUES ("534 264 345", "Částečný úvazek", 3, "kulda@naveleslavine.cz");
INSERT INTO isworking (tel, workingTime, departmentNum, doctor)
VALUES ("512 546 241", "Plný úvazek", 4, "zatop@seznam.cz");
INSERT INTO isworking (workingTime, departmentNum, doctor)
VALUES ("Plný úvazek", 5, "p.novak@seznam.cz");
INSERT INTO isworking (workingTime, departmentNum, doctor)
VALUES ("Internship", 5, "nemc@gmail.com");


-- INSERT DRUGS
INSERT INTO drugs (drugName, description, effectivity, contraindication) 
VALUES ('Kinedryl', 'Lék při léčbě nevolností', 'moxastini tenoclas', 'Ospalost, slabost, podrážděnost');
INSERT INTO drugs (drugName, description, effectivity, contraindication)  
VALUES('Aspirin', 'Lék při léčbě bolesti a chřipky', 'kyselina acetylsaciliová', 'Zvracení, dušnost');
INSERT INTO drugs (drugName, description, effectivity, contraindication) 
VALUES('Paralen', 'Lék při léčbě bolesti a chřipky', 'Paracetamol', 'Ospalost, slabost');
INSERT INTO drugs (drugName, description, effectivity, contraindication) 
VALUES('Endiaron', 'Lék při léčbě průjmů', 'Cloroxinum', 'Ospalost, svalové stahy, podrážděnost');
INSERT INTO drugs (drugName, description, effectivity, contraindication) 
VALUES('Ibalgin', 'Lék při léčbě bolesti a zánětu', 'ibuprofen', 'Slabost, podrážděnost');

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

-- INSERT HOSPITALIZATION
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

-- INSERT PRESCRIPTIONS
INSERT INTO prescriptions (startTime, endTime, dosage, patientId, drugId)
VALUES ("2014-05-21", "2014-06-01", "ráno", 6, 4);
INSERT INTO prescriptions (startTime, endTime, dosage, patientId, drugId)
VALUES ("2014-10-08", "2014-11-01", "ráno-večer", 2, 1);
INSERT INTO prescriptions (startTime, endTime, dosage, patientId, drugId)
VALUES ("2013-01-01", "2013-01-07", "poledne", 2, 5);
INSERT INTO prescriptions (startTime, dosage, patientId, drugId)
VALUES ("2014-05-12", "ráno-poledne-večer", 4, 3);
INSERT INTO prescriptions (startTime, endTime, dosage, patientId, drugId)
VALUES ("2014-03-27", "2014-04-10", "ráno-večer", 7, 2);
INSERT INTO prescriptions (startTime, endTime, dosage, patientId, drugId)
VALUES ("2014-05-11", "2014-06-29", "večer", 1, 3);
INSERT INTO prescriptions (startTime, endTime, dosage, patientId, drugId)
VALUES ("2014-07-23", "2014-08-23", "ráno-večer", 3, 4);

-- INSERT EXAMINATION
INSERT INTO examination (description, examTime, patientId, doctor)
VALUES ("Infarkt", "2014-02-07", 2, "zelda@example.com"); 
INSERT INTO examination (description, examTime, patientId, doctor)
VALUES ("Problémy s erekcí", "2014-05-12", 6, "p.novak@seznam.cz");
INSERT INTO examination (description, examTime, patientId, doctor)
VALUES ("Bolest loket", "2014-05-11", 4, "zatop@seznam.cz");
INSERT INTO examination (description, examTime, patientId, doctor)
VALUES ("Sedřená kůže", "2014-07-23", 1, "p.novak@seznam.cz");
INSERT INTO examination (description, examTime, patientId, doctor)
VALUES ("Zlomená pravá paže", "2014-10-08", 3, "kulda@naveleslavine.cz");
INSERT INTO examination (description, examTime, patientId, doctor)
VALUES ("Výron kotníku", "2013-01-01", 5, "nemc@gmail.com");
INSERT INTO examination (description, examTime, patientId, doctor)
VALUES ("Infarkt", "2014-08-09", 2, "zelda@example.com");

-- INSERT RESULTS
INSERT INTO results (resultDate, resultDsc, examinationId)
VALUES ("2014-02-10", "Prasklá aorta", 1);
INSERT INTO results (resultDate, resultDsc, examinationId)
VALUES ("2014-05-12", "Impotence", 2);
INSERT INTO results (resultDate, resultDsc, examinationId)
VALUES ("2014-05-14", "Tenisový loket", 3);
INSERT INTO results (resultDate, resultDsc, examinationId)
VALUES ("2014-07-26", "Tuberkulóza", 4);
INSERT INTO results (resultDate, resultDsc, examinationId)
VALUES ("2014-10-08", "Zlomenina kosti", 5);
INSERT INTO results (resultDate, resultDsc, examinationId)
VALUES ("2013-01-05", "Přetrhnutý vnitřní vaz kotníku", 6);
INSERT INTO results (resultDate, resultDsc, examinationId)
VALUES ("2014-08-12", "ZPrasklá aorta", 7);