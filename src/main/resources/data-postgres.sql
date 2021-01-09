INSERT INTO pharmacy_user(id, type, email, password, patient_address, patient_city, patient_country, patient_name, patient_phone_num, patient_surname) VALUES ('1', 'PATIENT', 'mika@gmail.com', 'mika', 'Mikin bulevar 123', 'Mikici', 'Srbija', 'Mika', '0601231234', 'Mikic');
INSERT INTO pharmacy_user(id, type, email, password, patient_address, patient_city, patient_country, patient_name, patient_phone_num, patient_surname) VALUES ('2', 'PATIENT', 'pera@gmail.com', 'pera', 'Perin bulevar 34', 'Perici', 'Srbija', 'Pera', '0601231234', 'Peric');
INSERT INTO pharmacy_user(id, type, email, password, patient_address, patient_city, patient_country, patient_name, patient_phone_num, patient_surname) VALUES ('3', 'PATIENT', 'duja@gmail.com', 'duja', 'Dujina ulica 7', 'Liman', 'Srbija', 'Duja', '0601231234', 'Dujic');

INSERT INTO pharmacy(id, about, address, name) VALUES ('1', 'This is a pharmacy.', 'Bulevar Oslobodjenja 123', 'Apoteka');

INSERT INTO medicine(id, pharmacy_id) VALUES ('1', '1');
INSERT INTO medicine(id, pharmacy_id) VALUES ('2', '1');
INSERT INTO medicine(id, pharmacy_id) VALUES ('3', '1');

INSERT INTO pharmacy_user(id, type, pharmacy_id) VALUES ('4', 'DERMATOLOGIST', '1');
INSERT INTO pharmacy_user(id, type, pharmacy_id) VALUES ('5', 'DERMATOLOGIST', '1');
INSERT INTO pharmacy_user(id, type, pharmacy_id) VALUES ('6', 'DERMATOLOGIST', '1');

INSERT INTO pharmacy_user(id, type, pharmacy_id) VALUES ('7', 'PHARMACIST', '1');
INSERT INTO pharmacy_user(id, type, pharmacy_id) VALUES ('8', 'PHARMACIST', '1');
INSERT INTO pharmacy_user(id, type, pharmacy_id) VALUES ('9', 'PHARMACIST', '1');

INSERT INTO exam(id, duration) VALUES ('1', '30');
INSERT INTO exam(id, duration) VALUES ('2', '45');
INSERT INTO exam(id, duration) VALUES ('3', '20');
INSERT INTO exam(id, duration) VALUES ('4', '20');
INSERT INTO exam(id, duration) VALUES ('5', '20');
INSERT INTO exam(id, duration) VALUES ('6', '20');

INSERT INTO exam_dermatologist(id, dermatologist_id) VALUES ('1', '4');
INSERT INTO exam_dermatologist(id, dermatologist_id) VALUES ('2', '5');
INSERT INTO exam_dermatologist(id, dermatologist_id) VALUES ('4', '6');

INSERT INTO exam_pharmacist(id, pharmacist_id) VALUES ('3', '7');
INSERT INTO exam_pharmacist(id, pharmacist_id) VALUES ('5', '8');
INSERT INTO exam_pharmacist(id, pharmacist_id) VALUES ('6', '9');