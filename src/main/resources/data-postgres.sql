INSERT INTO pharmacy_user(id, user_type, email, password, patient_address, patient_city, patient_country, patient_name, patient_phone_num, patient_surname, enabled, last_password_reset_date) VALUES ('1', 'PATIENT', 'mika@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mikin bulevar 123', 'Mikici', 'Srbija', 'Mika', '0601231234', 'Mikic', true, '2017-10-01 21:58:58.508-07');
INSERT INTO pharmacy_user(id, user_type, email, password, patient_address, patient_city, patient_country, patient_name, patient_phone_num, patient_surname, enabled, last_password_reset_date) VALUES ('2', 'PATIENT', 'pera@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Perin bulevar 34', 'Perici', 'Srbija', 'Pera', '0601231234', 'Peric', true, '2017-10-01 21:58:58.508-07');
INSERT INTO pharmacy_user(id, user_type, email, password, patient_address, patient_city, patient_country, patient_name, patient_phone_num, patient_surname, enabled, last_password_reset_date) VALUES ('3', 'PATIENT', 'pharmacyisa6+duja@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Dujina ulica 7', 'Liman', 'Srbija', 'Duja', '0601231234', 'Dujic', true, '2017-10-01 21:58:58.508-07');

INSERT INTO pharmacy(id, about, address, name, rating) VALUES ('1', 'This is a pharmacy.', 'Bulevar Oslobodjenja 123', 'Nova Apoteka', 5);
INSERT INTO pharmacy(id, about, address, name, rating) VALUES ('2', 'This is a pharmacy.', 'Bulevar Oslobodjenja 666', 'Sputnik', 5);
INSERT INTO pharmacy(id, about, address, name, rating) VALUES ('3', 'This is a pharmacy.', 'Hadziruvimova 54', 'Jankovic', 5);
INSERT INTO pharmacy(id, about, address, name, rating) VALUES ('4', 'This is a pharmacy.', 'Bulevar Evrope 43', 'Apoteka', 5);
INSERT INTO pharmacy(id, about, address, name, rating) VALUES ('5', 'This is a pharmacy.', 'Strazilovska 19', 'Biljana i Luka', 5);
INSERT INTO pharmacy(id, about, address, name, rating) VALUES ('6', 'This is a pharmacy.', 'Bulevar Oslobodjenja 100', 'Memfis', 5);
INSERT INTO pharmacy(id, about, address, name, rating) VALUES ('7', 'This is a pharmacy.', 'Maksima Gorkog 20', 'Phizer', 5);
INSERT INTO pharmacy(id, about, address, name, rating) VALUES ('8', 'This is a pharmacy.', 'Bul Evrope 32', 'Apoteka Jovanovic', 5);

INSERT INTO medicine(id, description, form, name) VALUES ('1', 'Anti-inflammatory medicine. Use on adults only.', 'tablet', 'Ibuprofen');
INSERT INTO medicine(id, description, form, name) VALUES ('2', 'Heart medicine. Reduces cholesterol.', 'tablet', 'Roxera');
INSERT INTO medicine(id, description, form, name) VALUES ('3', 'Antibiotic medicine.', 'syrup', 'Panklav');
INSERT INTO medicine(id, description, form, name) VALUES ('4', 'Painkiller.', 'tablet', 'Paracetamol');

INSERT INTO medicine_status(id, stock) VALUES ('1', '20');
INSERT INTO medicine_status(id, stock) VALUES ('2', '40');
INSERT INTO medicine_status(id, stock) VALUES ('3', '60');
INSERT INTO medicine_status(id, stock) VALUES ('4', '80');
INSERT INTO medicine_status(id, stock) VALUES ('5', '0');
INSERT INTO medicine_status(id, stock) VALUES ('6', '20');
INSERT INTO medicine_status(id, stock) VALUES ('7', '10');
INSERT INTO medicine_status(id, stock) VALUES ('8', '50');
INSERT INTO medicine_status(id, stock) VALUES ('9', '0');

INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('1', '20', '2021-07-16', '2021-06-16', '1');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('2', '40', '2021-03-16', '2020-12-16', '1');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('3', '23', '2021-03-16', '2020-12-16', '2');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('4', '25', '2021-03-16', '2020-12-16', '3');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('5', '27', '2021-03-16', '2020-12-16', '4');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('6', '10', '2021-03-16', '2020-12-16', '5');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('7', '12', '2021-03-16', '2020-12-16', '6');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('8', '23', '2021-03-16', '2020-12-16', '7');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('9', '32', '2021-03-16', '2020-12-16', '8');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('10', '31', '2021-03-16', '2020-12-16', '9');

INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('1', '1', '1');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('1', '2', '2');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('2', '3', '3');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('2', '4', '4');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('3', '5', '1');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('3', '6', '4');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('4', '7', '1');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('4', '8', '2');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('5', '9', '3');

INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname) VALUES ('4', 'DERMATOLOGIST', 'pharmacyisa6+djuro@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Djuro', 'Djuric');
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname) VALUES ('5', 'DERMATOLOGIST', 'pharmacyisa6+pera@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Pera', 'Peric');
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname) VALUES ('6', 'DERMATOLOGIST', 'pharmacyisa6+mika@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Mika', 'Mikic');

INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname) VALUES ('7', 'PHARMACIST', 'pharmacyisa6+savo@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Savo', 'Oroz');
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname) VALUES ('8', 'PHARMACIST', 'pharmacyisa6+dujo@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Dujo', 'Damjanovic');
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname) VALUES ('9', 'PHARMACIST', 'pharmacyisa6+marko@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Marko', 'Markovic');

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

INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES ('1', '4');
INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES ('2', '5');
INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES ('3', '6');
INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES ('4', '4');
INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES ('5', '5');

INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('1', '7');
INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('2', '7');
INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('3', '8');
INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('4', '8');
INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('5', '9');
INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('6', '9');

INSERT INTO vacation_time_request(id, approved, rejected_reason, time_end, time_start, status) VALUES (1, false, '', '2021-06-16 00:00:00', '2021-05-16 00:00:00', 'Waiting for response');
INSERT INTO vacation_time_request(id, approved, rejected_reason, time_end, time_start, status) VALUES (2, false, '', '2021-07-16 00:00:00', '2021-06-16 00:00:00', 'Waiting for response');
INSERT INTO vacation_time_request(id, approved, rejected_reason, time_end, time_start, status) VALUES (3, false, '', '2021-08-16 00:00:00', '2021-07-16 00:00:00', 'Waiting for response');
INSERT INTO vacation_time_request(id, approved, rejected_reason, time_end, time_start, status) VALUES (4, false, '', '2021-09-16 00:00:00', '2021-10-16 00:00:00', 'Waiting for response');

INSERT INTO vacation_request_pharmacist(id, pharmacist_id) VALUES (1, 7);
INSERT INTO vacation_request_pharmacist(id, pharmacist_id) VALUES (2, 8);
INSERT INTO vacation_request_dermatologist(id, dermatologist_id) VALUES (3, 4);
INSERT INTO vacation_request_dermatologist(id, dermatologist_id) VALUES (4, 5);

INSERT INTO AUTHORITY (id ,name) VALUES (1, 'ROLE_PATIENT');
INSERT INTO AUTHORITY (id ,name) VALUES (2, 'ROLE_PHARMACIST');
INSERT INTO AUTHORITY (id ,name) VALUES (3, 'ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (id ,name) VALUES (4, 'ROLE_PHARMACY_ADMINISTRATOR');
INSERT INTO AUTHORITY (id ,name) VALUES (5, 'ROLE_SYSTEM_ADMINISTRATOR');
INSERT INTO AUTHORITY (id ,name) VALUES (6, 'ROLE_UNAUTHORISED');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (6, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (7, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (9, 2);