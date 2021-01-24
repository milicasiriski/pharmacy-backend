INSERT INTO pharmacy_user(id, user_type, email, password, patient_address, patient_city, patient_country, patient_name, patient_phone_num, patient_surname, enabled, last_password_reset_date) VALUES ('1', 'PATIENT', 'mika@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mikin bulevar 123', 'Mikici', 'Srbija', 'Mika', '0601231234', 'Mikic', true, '2017-10-01 21:58:58.508-07');
INSERT INTO pharmacy_user(id, user_type, email, password, patient_address, patient_city, patient_country, patient_name, patient_phone_num, patient_surname, enabled, last_password_reset_date) VALUES ('2', 'PATIENT', 'pera@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Perin bulevar 34', 'Perici', 'Srbija', 'Pera', '0601231234', 'Peric', true, '2017-10-01 21:58:58.508-07');
INSERT INTO pharmacy_user(id, user_type, email, password, patient_address, patient_city, patient_country, patient_name, patient_phone_num, patient_surname, enabled, last_password_reset_date) VALUES ('3', 'PATIENT', 'duja@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Dujina ulica 7', 'Liman', 'Srbija', 'Duja', '0601231234', 'Dujic', true, '2017-10-01 21:58:58.508-07');

INSERT INTO pharmacy(id, about, address, name) VALUES ('1', 'This is a pharmacy.', 'Bulevar Oslobodjenja 123', 'Nova Apoteka');
INSERT INTO pharmacy(id, about, address, name) VALUES ('2', 'This is a pharmacy.', 'Bulevar Oslobodjenja 666', 'Sputnik');
INSERT INTO pharmacy(id, about, address, name) VALUES ('3', 'This is a pharmacy.', 'Hadziruvimova 54', 'Jankovic');
INSERT INTO pharmacy(id, about, address, name) VALUES ('4', 'This is a pharmacy.', 'Bulevar Evrope 43', 'Apoteka');
INSERT INTO pharmacy(id, about, address, name) VALUES ('5', 'This is a pharmacy.', 'Strazilovska 19', 'Biljana i Luka');
INSERT INTO pharmacy(id, about, address, name) VALUES ('6', 'This is a pharmacy.', 'Bulevar Oslobodjenja 100', 'Memfis');
INSERT INTO pharmacy(id, about, address, name) VALUES ('7', 'This is a pharmacy.', 'Maksima Gorkog 20', 'Phizer');
INSERT INTO pharmacy(id, about, address, name) VALUES ('8', 'This is a pharmacy.', 'Bul Evrope 32', 'Apoteka Jovanovic');

INSERT INTO medicine(id, description, form, name, pharmacy_id) VALUES ('1', 'Antinflamatory meidicine. Use on adults only.', 'tablet', 'Ibuprofen', '1');
INSERT INTO medicine(id, description, form, name, pharmacy_id) VALUES ('2', 'Heart medicine. Reduces cholesterol.', 'tablet', 'Roxera', '1');
INSERT INTO medicine(id, description, form, name, pharmacy_id) VALUES ('3', 'Antibiotic medicine.', 'syrup', 'Panklav', '1');
INSERT INTO medicine(id, description, form, name, pharmacy_id) VALUES ('4', 'Painkiller.', 'tablet', 'Paracetamol', '1');

INSERT INTO public.pharmacy_medicine_stock_mapping(pharmacy_id, stock, medicine_id) VALUES ('1', '10', '1');
INSERT INTO public.pharmacy_medicine_stock_mapping(pharmacy_id, stock, medicine_id) VALUES ('1', '20', '2');
INSERT INTO public.pharmacy_medicine_stock_mapping(pharmacy_id, stock, medicine_id) VALUES ('1', '0', '3');
INSERT INTO public.pharmacy_medicine_stock_mapping(pharmacy_id, stock, medicine_id) VALUES ('1', '4', '4');

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

INSERT INTO USERS_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USERS_AUTHORITY (user_id, authority_id) VALUES (2, 1);
INSERT INTO USERS_AUTHORITY (user_id, authority_id) VALUES (3, 1);
INSERT INTO USERS_AUTHORITY (user_id, authority_id) VALUES (4, 3);
INSERT INTO USERS_AUTHORITY (user_id, authority_id) VALUES (5, 3);
INSERT INTO USERS_AUTHORITY (user_id, authority_id) VALUES (6, 3);
INSERT INTO USERS_AUTHORITY (user_id, authority_id) VALUES (7, 2);
INSERT INTO USERS_AUTHORITY (user_id, authority_id) VALUES (8, 2);
INSERT INTO USERS_AUTHORITY (user_id, authority_id) VALUES (9, 2);


