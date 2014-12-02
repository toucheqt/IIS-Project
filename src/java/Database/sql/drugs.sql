CREATE TABLE drugs (
    id INT AUTO_INCREMENT,
    drugName VARCHAR(30) NOT NULL,
    description VARCHAR(200),
    effectivity VARCHAR(30) NOT NULL,
    contraindication VARCHAR(50),
    PRIMARY KEY(id)    
);

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