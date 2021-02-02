INSERT INTO pharmacy_user(id, user_type, email, password, patient_address, patient_city, patient_country, name, patient_phone_num, surname, enabled, last_password_reset_date, patient_penalty_points) VALUES ('1', 'PATIENT', 'mika@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mikin bulevar 123', 'Mikici', 'Srbija', 'Mika', '0601231234', 'Mikic', true, '2017-10-01 21:58:58.508-07', 1);
INSERT INTO pharmacy_user(id, user_type, email, password, patient_address, patient_city, patient_country, name, patient_phone_num, surname, enabled, last_password_reset_date, patient_penalty_points) VALUES ('2', 'PATIENT', 'pera@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Perin bulevar 34', 'Perici', 'Srbija', 'Pera', '0601231234', 'Peric', true, '2017-10-01 21:58:58.508-07', 2);
INSERT INTO pharmacy_user(id, user_type, email, password, patient_address, patient_city, patient_country, name, patient_phone_num, surname, enabled, last_password_reset_date, patient_penalty_points) VALUES ('3', 'PATIENT', 'pharmacyisa6+duja@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Dujina ulica 7', 'Liman', 'Srbija', 'Duja', '0601231234', 'Dujic', true, '2017-10-01 21:58:58.508-07', 0);

INSERT INTO pharmacy(id, about, city, country, latitude, longitude, street, name, rating) VALUES ('1', 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Bulevar Oslobodjenja 123', 'Nova Apoteka', 5);
INSERT INTO pharmacy(id, about, city, country, latitude, longitude, street, name, rating) VALUES ('2', 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Bulevar Oslobodjenja 666', 'Sputnik', 5);
INSERT INTO pharmacy(id, about, city, country, latitude, longitude, street, name, rating) VALUES ('3', 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Hadziruvimova 54', 'Jankovic', 5);
INSERT INTO pharmacy(id, about, city, country, latitude, longitude, street, name, rating) VALUES ('4', 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Bulevar Slobodana Jovanovica 35', 'Apoteka', 5);
INSERT INTO pharmacy(id, about, city, country, latitude, longitude, street, name, rating) VALUES ('5', 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Strazilovska 19', 'Biljana i Luka', 5);
INSERT INTO pharmacy(id, about, city, country, latitude, longitude, street, name, rating) VALUES ('6', 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Bulevar Oslobodjenja 100', 'Memfis', 5);
INSERT INTO pharmacy(id, about, city, country, latitude, longitude, street, name, rating) VALUES ('7', 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Maksima Gorkog 20', 'Phizer', 5);
INSERT INTO pharmacy(id, about, city, country, latitude, longitude, street, name, rating) VALUES ('8', 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Bul Evrope 32', 'Apoteka Jovanovic', 5);

INSERT INTO medicine(id, name, description, form, manufacturer, composition, type, prescribed, ratings, uuid, recommended_dose, side_effects, points) VALUES ('1','Ibuprofen' ,'Anti-inflammatory medicine. Use on adults only.', '0', 'Advil®', 'Microcrystalline, cellulose, lactose, hypromellose', '0', true, 5, 'A1', '1 tablet', 'Death', 0);
INSERT INTO medicine(id, name, description, form, manufacturer, composition, type, prescribed, ratings, uuid, recommended_dose, side_effects, points) VALUES ('2', 'Roxera', 'Heart medicine. Reduces cholesterol.', '0','KRKA-FARMA D.O.O Beograd', 'Rosuvastatin calcium', '0', false, 4,  'A2', '1 tablet', 'Death', 0);
INSERT INTO medicine(id, name, description, form, manufacturer, composition, type, prescribed, ratings, uuid, recommended_dose, side_effects, points) VALUES ('3', 'Panklav', 'Antibiotic medicine.', '1', 'Hemofarm', 'Amoxicillin','0', true,4,  'A3', '1 spoon', 'Death' , 0);
INSERT INTO medicine(id, name, description, form, manufacturer, composition, type, prescribed, ratings, uuid, recommended_dose, side_effects, points) VALUES ('4', 'Paracetamol', 'Painkiller.', '0', 'KRKA-FARMA D.O.O Beograd', 'Paracetamol', '0', false, 3,  'A4', '2 tablets', 'Death', 0);
INSERT INTO medicine(id, name, description, form, manufacturer, composition, type, prescribed, ratings, uuid, recommended_dose, side_effects, points) VALUES ('5', 'Bromazepam', 'Painkiller.', '0', 'KRKA-FARMA D.O.O Beograd', 'Paracetamol', '0', false, 3,  'A5', '3 tablets', 'Headache', 0);

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

INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('1', '4');
INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('4', '1');
INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('5', '1');
INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('1', '5');
INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('4', '5');
INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('5', '4');

INSERT INTO medicine_reservation(id, expiration_date, unique_number, medicine_id, patient_id, pharmacy_id) VALUES ('1', '2021-01-30 23:59:59', '1111', '1', '3', '1');
INSERT INTO medicine_reservation(id, expiration_date, unique_number, medicine_id, patient_id, pharmacy_id) VALUES ('2', '2021-03-02 23:59:59', '2222', '2', '3', '1');
INSERT INTO medicine_reservation(id, expiration_date, unique_number, medicine_id, patient_id, pharmacy_id) VALUES ('3', '2021-02-13 23:59:59', '3333', '3', '3', '5');

INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname, rating) VALUES ('4', 'DERMATOLOGIST', 'pharmacyisa6+djuro@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Djuro', 'Djuric', '4.5');
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname, rating) VALUES ('5', 'DERMATOLOGIST', 'pharmacyisa6+pera@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Pera', 'Peric', '4.8');
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname, rating) VALUES ('6', 'DERMATOLOGIST', 'pharmacyisa6+mika@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Mika', 'Mikic', '3.9');

INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname) VALUES ('7', 'PHARMACIST', 'pharmacyisa6+savo@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Savo', 'Oroz');
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname) VALUES ('8', 'PHARMACIST', 'pharmacyisa6+dujo@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Dujo', 'Damjanovic');
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname) VALUES ('9', 'PHARMACIST', 'pharmacyisa6+marko@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Marko', 'Markovic');

INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, pharmacy_id) VALUES ('10', 'PHARMACY_ADMIN', 'pharmacyisa6+admin1@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 4);
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, pharmacy_id) VALUES ('11', 'PHARMACY_ADMIN', 'pharmacyisa6+admin2@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 5);
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, pharmacy_id) VALUES ('12', 'PHARMACY_ADMIN', 'pharmacyisa6+admin3@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 6);

INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date) VALUES ('13', 'SUPPLIER', 'pharmacyisa6+supplier1@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07');
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date) VALUES ('14', 'SUPPLIER', 'pharmacyisa6+supplier2@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07');
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date) VALUES ('15', 'SUPPLIER', 'pharmacyisa6+supplier3@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07');
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date) VALUES ('16', 'SUPPLIER', 'pharmacyisa6+supplier4@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07');

INSERT INTO medicine_order(id, deadline, pharmacy_admin_id) VALUES (1, '2021-06-16 00:00:00', 10);
INSERT INTO medicine_order(id, deadline, pharmacy_admin_id) VALUES (2, '2021-06-16 00:00:00', 11);
INSERT INTO medicine_order(id, deadline, pharmacy_admin_id) VALUES (3, '2021-07-16 00:00:00', 11);
INSERT INTO medicine_order(id, deadline, pharmacy_admin_id) VALUES (4, '2021-08-16 00:00:00', 11);

INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (4, 3, 1);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (4, 2, 2);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (4, 3, 3);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (3, 2, 3);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (3, 3, 4);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (1, 2, 4);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (2, 3, 4);

INSERT INTO supplier_medicine_mapping(supplier_id, medicine_amount, medicine_id) VALUES (13, 8, 4);
INSERT INTO supplier_medicine_mapping(supplier_id, medicine_amount, medicine_id) VALUES (13, 5, 1);
INSERT INTO supplier_medicine_mapping(supplier_id, medicine_amount, medicine_id) VALUES (13, 6, 2);
INSERT INTO supplier_medicine_mapping(supplier_id, medicine_amount, medicine_id) VALUES (13, 3, 3);
INSERT INTO supplier_medicine_mapping(supplier_id, medicine_amount, medicine_id) VALUES (13, 91, 5);

INSERT INTO order_offer(id, price, shipping_days, status,order_id, supplier_id) VALUES (1, 100, 10, 0, 1, 13);
INSERT INTO order_offer(id, price, shipping_days, status,order_id, supplier_id) VALUES (2, 100, 20, 0, 2, 13);

INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('1', '8');
INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('2', '8');
INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('3', '8');
INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('4', '7');
INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('4', '7');
INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES ('6', '9');

INSERT INTO vacation_time_request(id, approved, rejected_reason, time_end, time_start, status) VALUES (1, false, '', '2021-06-16 00:00:00', '2021-05-16 00:00:00', 'Waiting for response');
INSERT INTO vacation_time_request(id, approved, rejected_reason, time_end, time_start, status) VALUES (2, false, '', '2021-07-16 00:00:00', '2021-06-16 00:00:00', 'Waiting for response');
INSERT INTO vacation_time_request(id, approved, rejected_reason, time_end, time_start, status) VALUES (3, false, '', '2021-08-16 00:00:00', '2021-07-16 00:00:00', 'Waiting for response');
INSERT INTO vacation_time_request(id, approved, rejected_reason, time_end, time_start, status) VALUES (4, false, '', '2021-09-16 00:00:00', '2021-10-16 00:00:00', 'Waiting for response');

INSERT INTO vacation_request_pharmacist(id, pharmacist_id) VALUES ('1', '7');
INSERT INTO vacation_request_pharmacist(id, pharmacist_id) VALUES ('2', '8');

INSERT INTO vacation_request_dermatologist(id, dermatologist_id, pharmacy_id) VALUES ('3', '4', '4');
INSERT INTO vacation_request_dermatologist(id, dermatologist_id, pharmacy_id) VALUES ('4', '4', '4');

INSERT INTO employment(id, duration, price) VALUES ('1', '20', '20');
INSERT INTO employment(id, duration, price) VALUES ('2', '20', '30');

INSERT INTO exam(id, price, end_time, start_time, employment_id) VALUES ('1', '20', '2021-02-15 15:00:00', '2021-02-15 14:40:00', '1');
INSERT INTO exam(id, price, end_time, start_time, employment_id) VALUES ('2', '40', '2021-02-02 14:40:00', '2021-02-02 14:20:00', '2');
INSERT INTO exam(id, price, end_time, start_time, employment_id) VALUES ('3', '10', '2021-02-27 11:40:00', '2021-02-27 11:20:00', '1');
INSERT INTO exam(id, price, end_time, start_time, employment_id) VALUES ('4', '30', '2021-02-12 10:40:00', '2021-02-12 10:20:00', '2');

INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 16:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 16:00:00', '1970-01-01 10:00:00', '1');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 12:00:00', '1970-01-01 08:00:00', '2');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 16:00:00', '1970-01-01 09:00:00', '3');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 16:00:00', '1970-01-01 08:00:00', '4');

INSERT INTO dermatologist_employment_mapping(pharmacy_id, dermatologist_employment_id, dermatologist_id) VALUES ('4', '1', '4');
INSERT INTO dermatologist_employment_mapping(pharmacy_id, dermatologist_employment_id, dermatologist_id) VALUES ('4', '2', '5');

INSERT INTO AUTHORITY (id ,name) VALUES (1, 'ROLE_PATIENT');
INSERT INTO AUTHORITY (id ,name) VALUES (2, 'ROLE_PHARMACIST');
INSERT INTO AUTHORITY (id ,name) VALUES (3, 'ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (id ,name) VALUES (4, 'ROLE_PHARMACY_ADMINISTRATOR');
INSERT INTO AUTHORITY (id ,name) VALUES (5, 'ROLE_SYSTEM_ADMINISTRATOR');
INSERT INTO AUTHORITY (id ,name) VALUES (6, 'ROLE_SUPPLIER');
INSERT INTO AUTHORITY (id ,name) VALUES (7, 'ROLE_UNAUTHORISED');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (6, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (7, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (9, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (10, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (11, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (12, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (13, 6);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (14, 6);