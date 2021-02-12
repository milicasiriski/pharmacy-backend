
-- Logujete se pomocu e-maila i sifre.
-- Sifra za svakog korisnika je 123.

-- Pacijenti: pharmacyisa6+duja@gmail.com, pharmacyisa6+pera@gmail.com (ima 3 penalty poena)
-- Administrator apoteke: pharmacyisa6+admin1@gmail.com
-- Dobavljac: pharmacyisa6+supplier1@gmail.com
-- Sistem admin: pharmacyisa6+systemadmin4@gmail.com

-- Apoteka sa najvise podataka - id: 4, name: Apoteka

INSERT INTO pharmacy_user(id, user_type, email, password, street, city, country, name, patient_phone_num, surname, enabled, last_password_reset_date, patient_penalty_points, patient_loyalty_points, version) VALUES ('1', 'PATIENT', 'pharmacyisa6+mika@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Gavrila Principa 39a', 'Novi Sad', 'Republika Srbija', 'Mika', '0601231234', 'Mikic', true, '2017-10-01 21:58:58.508-07', 1, 1, 0);
INSERT INTO pharmacy_user(id, user_type, email, password, street, city, country, name, patient_phone_num, surname, enabled, last_password_reset_date, patient_penalty_points, patient_loyalty_points, version) VALUES ('2', 'PATIENT', 'pharmacyisa6+pera@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Bulevar 34', 'Novi Sad', 'Republika Srbija', 'Pera', '0601231234', 'Peric', true, '2017-10-01 21:58:58.508-07', 3, 2, 0);
INSERT INTO pharmacy_user(id, user_type, email, password, street, city, country, name, patient_phone_num, surname, enabled, last_password_reset_date, patient_penalty_points, patient_loyalty_points, version) VALUES ('3', 'PATIENT', 'pharmacyisa6+duja@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Fruskogorska 17', 'Novi Sad', 'Republika Srbija', 'Duja', '0601231234', 'Dujic', true, '2017-10-01 21:58:58.508-07', 0, 0, 0);

INSERT INTO pharmacy(id, version, about, city, country, longitude, latitude, street, name, pharmacist_exam_duration, pharmacist_exam_price, rating) VALUES ('1', 0, 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Bulevar Oslobodjenja 123', 'Nova Apoteka', 30, 45, 4.6);
INSERT INTO pharmacy(id, version, about, city, country, longitude, latitude, street, name, pharmacist_exam_duration, pharmacist_exam_price, rating) VALUES ('2', 0, 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Bulevar Oslobodjenja 6', 'Sputnik', 20, 20, 5);
INSERT INTO pharmacy(id, version, about, city, country, longitude, latitude, street, name, pharmacist_exam_duration, pharmacist_exam_price, rating) VALUES ('3', 0, 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.8109553', '45.2516069', 'Hadziruvimova 54', 'Jankovic', 30, 40, 4.2);
INSERT INTO pharmacy(id, version, about, city, country, longitude, latitude, street, name, pharmacist_exam_duration, pharmacist_exam_price, rating) VALUES ('4', 0, 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Bulevar Slobodana Jovanovica 35', 'Apoteka', 30, 30, 4.2);
INSERT INTO pharmacy(id, version, about, city, country, longitude, latitude, street, name, pharmacist_exam_duration, pharmacist_exam_price, rating) VALUES ('5', 0, 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.565618', '45.276658', 'Strazilovska 19', 'Biljana i Luka', 30, 50, 4.1);
INSERT INTO pharmacy(id, version, about, city, country, longitude, latitude, street, name, pharmacist_exam_duration, pharmacist_exam_price, rating) VALUES ('6', 0, 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Bulevar Oslobodjenja 100', 'Memfis', 20, 45, 4.0);
INSERT INTO pharmacy(id, version, about, city, country, longitude, latitude, street, name, pharmacist_exam_duration, pharmacist_exam_price, rating) VALUES ('7', 0, 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.565618', '45.276658', 'Maksima Gorkog 20', 'Phizer', 45, 50, 3.9);
INSERT INTO pharmacy(id, version, about, city, country, longitude, latitude, street, name, pharmacist_exam_duration, pharmacist_exam_price, rating) VALUES ('8', 0, 'This is a pharmacy.', 'Novi Sad', 'Republika Srbija', '19.801726', '45.255790', 'Bul Evrope 32', 'Apoteka Jovanovic', 15, 20, 4.2);

INSERT INTO medicine(id, name, description, form, manufacturer, composition, type, prescribed, ratings, uuid, recommended_dose, side_effects, points) VALUES ('1','Ibuprofen' ,'Anti-inflammatory medicine. Use on adults only.', '0', 'Advil®', 'Microcrystalline, cellulose, lactose, hypromellose', '0', true, 5, 'A1', '1 tablet', 'Death', 2);
INSERT INTO medicine(id, name, description, form, manufacturer, composition, type, prescribed, ratings, uuid, recommended_dose, side_effects, points) VALUES ('2', 'Roxera', 'Heart medicine. Reduces cholesterol.', '0','KRKA-FARMA D.O.O Beograd', 'Rosuvastatin calcium', '0', false, 4,  'A2', '1 tablet', 'Death', 2);
INSERT INTO medicine(id, name, description, form, manufacturer, composition, type, prescribed, ratings, uuid, recommended_dose, side_effects, points) VALUES ('3', 'Panklav', 'Antibiotic medicine.', '1', 'Hemofarm', 'Amoxicillin','0', true,4,  'A3', '1 spoon', 'Death' , 2);
INSERT INTO medicine(id, name, description, form, manufacturer, composition, type, prescribed, ratings, uuid, recommended_dose, side_effects, points) VALUES ('4', 'Paracetamol', 'Painkiller.', '0', 'KRKA-FARMA D.O.O Beograd', 'Paracetamol', '0', false, 3,  'A4', '2 tablets', 'Death', 2);
INSERT INTO medicine(id, name, description, form, manufacturer, composition, type, prescribed, ratings, uuid, recommended_dose, side_effects, points) VALUES ('5', 'Bromazepam', 'Painkiller.', '0', 'KRKA-FARMA D.O.O Beograd', 'Paracetamol', '0', false, 3,  'A5', '3 tablets', 'Headache', 2);

INSERT INTO medicine_status(id, stock, version) VALUES ('1', '1', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('2', '40', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('3', '60', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('4', '80', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('5', '0', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('6', '20', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('7', '10', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('8', '50', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('9', '0', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('10', '20', 0);

INSERT INTO medicine_status(id, stock, version) VALUES ('11', '20', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('12', '30', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('13', '30', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('14', '30', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('15', '30', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('16', '30', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('17', '30', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('18', '30', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('19', '30', 0);
INSERT INTO medicine_status(id, stock, version) VALUES ('20', '30', 0);

INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('1', '20', '2021-07-16', '2021-06-16', '1');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('2', '40', '2021-03-16', '2020-12-16', '1');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('3', '23', '2021-03-16', '2020-12-16', '2');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('4', '25', '2021-03-16', '2020-12-16', '3');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('5', '27', '2021-03-16', '2020-12-16', '4');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('6', '10', '2021-03-16', '2020-12-16', '5');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('7', '12', '2021-03-16', '2020-12-16', '6');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('8', '23', '2021-03-16', '2020-12-16', '7');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('9', '32', '2021-03-16', '2020-12-16', '8');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('11', '40', '2021-03-16', '2020-12-16', '9');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('12', '50', '2021-03-16', '2020-12-16', '10');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('13', '44', '2021-03-16', '2020-12-16', '11');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('14', '34', '2021-03-16', '2020-12-16', '12');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('15', '31', '2021-03-16', '2020-12-16', '13');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('16', '54', '2021-03-16', '2020-12-16', '14');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('17', '34', '2021-03-16', '2020-12-16', '15');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('18', '54', '2021-03-16', '2020-12-16', '16');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('19', '23', '2021-03-16', '2020-12-16', '17');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('20', '12', '2021-03-16', '2020-12-16', '18');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('21', '11', '2021-03-16', '2020-12-16', '19');
INSERT INTO medicine_price_list_item(id, price, time_end, time_start, medicine_status_id) VALUES ('22', '15', '2021-03-16', '2020-12-16', '20');

INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('1', '1', '1');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('1', '2', '2');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('2', '3', '3');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('2', '4', '4');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('3', '5', '1');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('3', '6', '4');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('4', '7', '1');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('4', '8', '2');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('5', '9', '3');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('4', '10', '4');

INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('1', '11', '3');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('1', '12', '4');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('1', '13', '5');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('2', '14', '1');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('2', '15', '2');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('2', '16', '5');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('3', '17', '2');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('3', '18', '3');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('3', '19', '5');
INSERT INTO medicine_status_mapping(pharmacy_id, medicine_status_id, medicine_id) VALUES ('4', '20', '5');

INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('1', '4');
INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('4', '1');
INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('5', '1');
INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('1', '5');
INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('4', '5');
INSERT INTO medicine_alternatives(medicine_id, alternative_id) VALUES ('5', '4');

-- Istekao rok za preuzimanje, sluzi za testiranje metode koja brise istekle rezervacije i daje penale pacijentu.
INSERT INTO medicine_reservation(id, expiration_date, unique_number, medicine_id, patient_id, pharmacy_id) VALUES ('1', '2021-01-30 23:59:59', '1111', '1', '3', '1');
INSERT INTO medicine_reservation(id, expiration_date, unique_number, medicine_id, patient_id, pharmacy_id) VALUES ('2', '2021-03-02 23:59:59', '2222', '2', '3', '1');
INSERT INTO medicine_reservation(id, expiration_date, unique_number, medicine_id, patient_id, pharmacy_id) VALUES ('3', '2021-02-13 23:59:59', '3333', '3', '3', '5');
INSERT INTO medicine_reservation(id, expiration_date, unique_number, medicine_id, patient_id, pharmacy_id) VALUES ('4', '2021-02-13 23:59:59', '4444', '1', '3', '4');

INSERT INTO prescription(id, version, prescription_date, patient_id) VALUES ('11', 0, '2020-11-11', '3');
INSERT INTO prescription(id, version, prescription_date, patient_id) VALUES ('12', 0, '2020-10-11', '3');

INSERT INTO prescription_medicine_mapping(prescription_id, prescription_medicine_amount, medicine_id) VALUES ('11', 1, '2');
INSERT INTO prescription_medicine_mapping(prescription_id, prescription_medicine_amount, medicine_id) VALUES ('11', 5, '3');
INSERT INTO prescription_medicine_mapping(prescription_id, prescription_medicine_amount, medicine_id) VALUES ('12', 2, '2');
INSERT INTO prescription_medicine_mapping(prescription_id, prescription_medicine_amount, medicine_id) VALUES ('12', 4, '5');

INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname, rating, version) VALUES ('4', 'DERMATOLOGIST', 'pharmacyisa6+derm1@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Djuro', 'Djuric', '4.5', 0);
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname, rating, version) VALUES ('5', 'DERMATOLOGIST', 'pharmacyisa6+derm2@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Pera', 'Peric', '4.8', 0);
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname, rating, version) VALUES ('6', 'DERMATOLOGIST', 'pharmacyisa6+derm3@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Mika', 'Mikic', '3.9', 0);

INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname, pharmacy_id, rating, version) VALUES ('7', 'PHARMACIST', 'pharmacyisa6+pharm1@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Savo', 'Oroz', '1', 4.2, 0);
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname, pharmacy_id, rating, version) VALUES ('8', 'PHARMACIST', 'pharmacyisa6+pharm2@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Dujo', 'Damjanovic', '4', 4.5, 0);
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, name, surname, pharmacy_id, rating, version) VALUES ('9', 'PHARMACIST', 'pharmacyisa6+pharm3@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 'Marko', 'Markovic', '5', 4.1, 0);

INSERT INTO pharmacy_user(id, name, surname, user_type, email, password, city, country, street, enabled, last_password_reset_date, pharmacy_id, version) VALUES ('10', 'Savo', 'Oroz', 'PHARMACY_ADMIN', 'pharmacyisa6+admin1@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Novi Sad', 'Republika Srbija', 'Bul Slobodana Jovanovica 35', true, '2017-10-01 21:58:58.508-07', 4, 0);
INSERT INTO pharmacy_user(id, name, surname, user_type, email, password, city, country, street, enabled, last_password_reset_date, pharmacy_id, version) VALUES ('11', 'Sone', 'Adamovic', 'PHARMACY_ADMIN', 'pharmacyisa6+admin2@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Novi Sad', 'Republika Srbija', 'Bul Slobodana Jovanovica 35', true, '2017-10-01 21:58:58.508-07', 5, 0);
INSERT INTO pharmacy_user(id, name, surname, user_type, email, password, city, country, street, enabled, last_password_reset_date, pharmacy_id, version) VALUES ('12', 'Admin', 'Admin', 'PHARMACY_ADMIN', 'pharmacyisa6+admin3@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',  'Novi Sad', 'Republika Srbija', 'Bul Slobodana Jovanovica 35', true, '2017-10-01 21:58:58.508-07', 6, 0);

INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, street, city, country, version) VALUES ('13', 'SUPPLIER', 'pharmacyisa6+supplier1@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07','Mikin bulevar 123', 'Mikici', 'Srbija', 0);
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, street, city, country, version) VALUES ('14', 'SUPPLIER', 'pharmacyisa6+supplier2@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07','Mikin bulevar 123', 'Mikici', 'Srbija', 0);
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, street, city, country, version) VALUES ('15', 'SUPPLIER', 'pharmacyisa6+supplier3@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07','Mikin bulevar 123', 'Mikici', 'Srbija', 0);
INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, street, city, country, version) VALUES ('16', 'SUPPLIER', 'pharmacyisa6+supplier4@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07','Mikin bulevar 123', 'Mikici', 'Srbija', 0);

INSERT INTO medicine_order(id, deadline, pharmacy_admin_id, offer_accepted, version) VALUES (1, '2021-06-16 00:00:00', 11, false, 0);
INSERT INTO medicine_order(id, deadline, pharmacy_admin_id, offer_accepted, version) VALUES (5, '2021-06-16 00:00:00', 11, false, 0);
INSERT INTO medicine_order(id, deadline, pharmacy_admin_id, offer_accepted, version) VALUES (6, '2021-06-16 00:00:00', 11, false, 0);
INSERT INTO medicine_order(id, deadline, pharmacy_admin_id, offer_accepted, version) VALUES (2, '2021-01-16 00:00:00', 10, false, 0);
INSERT INTO medicine_order(id, deadline, pharmacy_admin_id, offer_accepted, version) VALUES (3, '2021-07-16 00:00:00', 10, false, 0);
INSERT INTO medicine_order(id, deadline, pharmacy_admin_id, offer_accepted, version) VALUES (4, '2021-08-16 00:00:00', 10, false, 0);

INSERT INTO pharmacy_user(id, user_type, email, password, enabled, last_password_reset_date, version) VALUES ('17', 'SYSTEM_ADMIN', 'pharmacyisa6+systemadmin4@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', true, '2017-10-01 21:58:58.508-07', 0);

INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (5, 2, 4);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (1, 2, 1);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (5, 103, 2);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (6, 1, 5);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (6, 1, 4);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (1, 1, 4);

INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (2, 2, 4);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (3, 2, 1);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (2, 103, 2);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (4, 1, 5);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (4, 1, 4);
INSERT INTO order_medicine_mapping(order_id, order_medicine_amount, medicine_id) VALUES (3, 1, 4);

INSERT INTO supplier_medicine_mapping(supplier_id, medicine_amount, medicine_id) VALUES (13, 8, 4);
INSERT INTO supplier_medicine_mapping(supplier_id, medicine_amount, medicine_id) VALUES (13, 5, 1);
INSERT INTO supplier_medicine_mapping(supplier_id, medicine_amount, medicine_id) VALUES (13, 6, 2);
INSERT INTO supplier_medicine_mapping(supplier_id, medicine_amount, medicine_id) VALUES (13, 3, 3);
INSERT INTO supplier_medicine_mapping(supplier_id, medicine_amount, medicine_id) VALUES (13, 91, 5);
INSERT INTO supplier_medicine_mapping(supplier_id, medicine_amount, medicine_id) VALUES (14, 91, 4);

-- INSERT INTO order_offer(id, price, shipping_days, status,order_id, supplier_id) VALUES (1, 100, 10, 0, 1, 13);
-- INSERT INTO order_offer(id, price, shipping_days, status,order_id, supplier_id) VALUES (3, 100, 10, 0, 1, 13);
-- INSERT INTO order_offer(id, price, shipping_days, status,order_id, supplier_id) VALUES (4, 100, 10, 0, 1, 13);

INSERT INTO order_offer(id, price, shipping_days, status,order_id, supplier_id, version) VALUES (2, 100, 20, 0, 2, 13, 0);
INSERT INTO order_offer(id, price, shipping_days, status,order_id, supplier_id, version) VALUES (5, 100, 20, 0, 3, 13, 0);
INSERT INTO order_offer(id, price, shipping_days, status,order_id, supplier_id, version) VALUES (6, 100, 20, 0, 4, 13, 0);
INSERT INTO order_offer(id, price, shipping_days, status,order_id, supplier_id, version) VALUES (7, 150, 20, 0, 4, 14, 0);

INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('7', '1970-01-01 16:00:00', '1970-01-01 8:00:00', '0');
INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('7', '1970-01-01 16:00:00', '1970-01-01 8:00:00', '1');
INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('7', '1970-01-01 12:00:00', '1970-01-01 8:00:00', '2');
INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('7', '1970-01-01 16:00:00', '1970-01-01 8:00:00', '3');
INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('7', '1970-01-01 16:00:00', '1970-01-01 8:00:00', '4');

INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('8', '1970-01-01 20:00:00', '1970-01-01 14:00:00', '0');
INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('8', '1970-01-01 20:00:00', '1970-01-01 16:00:00', '1');
INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('8', '1970-01-01 20:00:00', '1970-01-01 16:00:00', '2');
INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('8', '1970-01-01 20:00:00', '1970-01-01 16:00:00', '3');
INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('8', '1970-01-01 20:00:00', '1970-01-01 14:00:00', '4');

INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('9', '1970-01-01 18:00:00', '1970-01-01 14:00:00', '0');
INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('9', '1970-01-01 18:00:00', '1970-01-01 14:00:00', '2');
INSERT INTO pharmacist_shift(pharmacist_id, end_time, start_time, day_of_week) VALUES ('9', '1970-01-01 18:00:00', '1970-01-01 14:00:00', '4');

INSERT INTO vacation_time_request(id, rejected_reason, time_end, time_start, status, version) VALUES (1, '', '2021-06-16 00:00:00', '2021-05-16 00:00:00', '2', 0);
INSERT INTO vacation_time_request(id, rejected_reason, time_end, time_start, status, version) VALUES (2, '', '2021-07-16 00:00:00', '2021-06-16 00:00:00', '2', 0);
INSERT INTO vacation_time_request(id, rejected_reason, time_end, time_start, status, version) VALUES (3, '', '2021-08-16 00:00:00', '2021-07-16 00:00:00', '2', 0);
INSERT INTO vacation_time_request(id, rejected_reason, time_end, time_start, status, version) VALUES (4, '', '2021-09-16 00:00:00', '2021-10-16 00:00:00', '2', 0);
INSERT INTO vacation_time_request(id, rejected_reason, time_end, time_start, status, version) VALUES (5, '', '2021-03-10 00:00:00', '2021-02-28 00:00:00', '1', 0);

INSERT INTO vacation_request_pharmacist(id, pharmacist_id) VALUES ('1', '7');
INSERT INTO vacation_request_pharmacist(id, pharmacist_id) VALUES ('2', '8');
INSERT INTO vacation_request_pharmacist(id, pharmacist_id) VALUES ('5', '8');

INSERT INTO vacation_request_dermatologist(id, dermatologist_id, pharmacy_id) VALUES ('3', '4', '4');
INSERT INTO vacation_request_dermatologist(id, dermatologist_id, pharmacy_id) VALUES ('4', '4', '4');

INSERT INTO employment(id, version) VALUES ('1', 0);
INSERT INTO employment(id, version) VALUES ('2', 0);

INSERT INTO exam(id, price, end_time, start_time, employment_id, status, version) VALUES ('1', '20', '2021-02-22 15:00:00', '2021-02-22 14:40:00', 1, 2, 0);
INSERT INTO exam(id, price, end_time, start_time, employment_id, status, version) VALUES ('2', '40', '2021-02-23 14:40:00', '2021-02-23 14:20:00', 1, 1, 0);
INSERT INTO exam(id, price, end_time, start_time, employment_id, status, version) VALUES ('3', '10', '2021-02-27 11:40:00', '2021-02-27 11:20:00', 1, 2, 0);
INSERT INTO exam(id, price, end_time, start_time, employment_id, patient_id, status, version) VALUES ('4', '30', '2021-02-19 10:40:00', '2021-02-19 10:20:00', '2', 3, 2, 0);
INSERT INTO exam(id, price, end_time, start_time, employment_id, patient_id, status, version) VALUES ('8', '30', '2021-02-05 10:40:00', '2021-02-05 10:20:00', '2', 3, 0, 0);

INSERT INTO exam(id, price, end_time, start_time, patient_id, pharmacist_id, status, version) VALUES ('5', '20', '2021-01-30 18:00', '2021-01-30 17:30', '1', '8', 2, 0);
INSERT INTO exam(id, price, end_time, start_time, patient_id, pharmacist_id, status, version) VALUES ('6', '20', '2020-03-30 18:00', '2021-03-30 17:30', '3', '8', 1, 0);
INSERT INTO exam(id, price, end_time, start_time, patient_id, pharmacist_id, status, version) VALUES ('7', '20', '2021-05-30 18:00', '2021-03-30 17:30', '3', '9', 2, 0);

INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacist_id) VALUES (4, 8);

INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 16:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 16:00:00', '1970-01-01 10:00:00', '1');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 12:00:00', '1970-01-01 08:00:00', '2');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 16:00:00', '1970-01-01 09:00:00', '3');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 16:00:00', '1970-01-01 08:00:00', '4');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 16:00:00', '1970-01-01 08:00:00', '5');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (1, '1970-01-01 16:00:00', '1970-01-01 08:00:00', '6');

INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (2, '1970-01-01 16:00:00', '1970-01-01 08:00:00', '0');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (2, '1970-01-01 16:00:00', '1970-01-01 10:00:00', '1');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (2, '1970-01-01 12:00:00', '1970-01-01 08:00:00', '2');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (2, '1970-01-01 16:00:00', '1970-01-01 09:00:00', '3');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (2, '1970-01-01 16:00:00', '1970-01-01 08:00:00', '4');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (2, '1970-01-01 16:00:00', '1970-01-01 08:00:00', '5');
INSERT INTO shift_day_mapping(employment_id, end_time, start_time, day_of_week) VALUES (2, '1970-01-01 16:00:00', '1970-01-01 08:00:00', '6');

INSERT INTO dermatologist_employment_mapping(pharmacy_id, dermatologist_employment_id, dermatologist_id) VALUES ('4', '1', '4');
INSERT INTO dermatologist_employment_mapping(pharmacy_id, dermatologist_employment_id, dermatologist_id) VALUES ('4', '2', '5');

INSERT INTO medicine_purchase (id, price, purchase_date, pharmacy_id, patient_id) VALUES (1, 50, '2021-07-16', 4, 3);
INSERT INTO medicine_purchase (id, price, purchase_date, pharmacy_id, patient_id) VALUES (2, 100, '2021-08-16', 4, 3);
INSERT INTO medicine_purchase (id, price, purchase_date, pharmacy_id, patient_id) VALUES (3, 100, '2021-08-16', 4, 3);
INSERT INTO medicine_purchase (id, price, purchase_date, pharmacy_id, patient_id) VALUES (4, 100, '2021-08-16', 4, 3);

INSERT INTO purchase_medicine_mapping (purchase_id, medicine_amount, medicine_id) VALUES (1, 10, 1);
INSERT INTO purchase_medicine_mapping (purchase_id, medicine_amount, medicine_id) VALUES (2, 30, 2);
INSERT INTO purchase_medicine_mapping (purchase_id, medicine_amount, medicine_id) VALUES (3, 40, 1);
INSERT INTO purchase_medicine_mapping (purchase_id, medicine_amount, medicine_id) VALUES (4, 90, 1);

INSERT INTO medicine_search (id, medicine_id, pharmacy_id, search_date) VALUES (1, 5, 4, '2021-07-16');
INSERT INTO medicine_search (id, medicine_id, pharmacy_id, search_date) VALUES (2, 4, 4, '2021-07-16');
INSERT INTO medicine_search (id, medicine_id, pharmacy_id, search_date) VALUES (3, 3, 4, '2021-07-16');

INSERT INTO complaint(id, version, answer_text, complaint_text, resolved, patient_id, pharmacy_id, staff_id) VALUES (1, 0, null, 'Im not happy with a service!', false , 1, null, 1);
INSERT INTO complaint(id, version, answer_text, complaint_text, resolved, patient_id, pharmacy_id, staff_id) VALUES (2, 0, null, 'Im not happy with a service!', false , 1, null, 1);

INSERT INTO pharmacy_subscribers(pharmacy_id, patient_id) VALUES (4, 1);
INSERT INTO pharmacy_subscribers(pharmacy_id, patient_id) VALUES (4, 2);
INSERT INTO pharmacy_subscribers(pharmacy_id, patient_id) VALUES (4, 3);

INSERT INTO rating(id, rating, patient_id) VALUES ('1', '5', '1');
INSERT INTO rating(id, rating, patient_id) VALUES ('2', '4', '1');
INSERT INTO rating(id, rating, patient_id) VALUES ('3', '3', '1');
INSERT INTO rating(id, rating, patient_id) VALUES ('4', '5', '1');
INSERT INTO rating(id, rating, patient_id) VALUES ('5', '4', '1');
INSERT INTO rating(id, rating, patient_id) VALUES ('6', '4', '2');
INSERT INTO rating(id, rating, patient_id) VALUES ('7', '4', '2');
INSERT INTO rating(id, rating, patient_id) VALUES ('8', '3', '1');
INSERT INTO rating(id, rating, patient_id) VALUES ('9', '5', '2');
INSERT INTO rating(id, rating, patient_id) VALUES ('10', '2', '2');
INSERT INTO rating(id, rating, patient_id) VALUES ('11', '5', '1');
INSERT INTO rating(id, rating, patient_id) VALUES ('12', '3', '2');
INSERT INTO rating(id, rating, patient_id) VALUES ('13', '4', '3');
INSERT INTO rating(id, rating, patient_id) VALUES ('14', '5', '1');
INSERT INTO rating(id, rating, patient_id) VALUES ('15', '4', '2');

INSERT INTO rating_dermatologist(id, dermatologist_id) VALUES ('1', '4');
INSERT INTO rating_dermatologist(id, dermatologist_id) VALUES ('2', '5');
INSERT INTO rating_dermatologist(id, dermatologist_id) VALUES ('6', '6');
INSERT INTO rating_dermatologist(id, dermatologist_id) VALUES ('7', '4');

INSERT INTO rating_medicine(id, medicine_id) VALUES ('3', '1');
INSERT INTO rating_medicine(id, medicine_id) VALUES ('4', '3');
INSERT INTO rating_medicine(id, medicine_id) VALUES ('5', '5');

INSERT INTO rating_pharmacist(id, pharmacist_id) VALUES ('8', '7');
INSERT INTO rating_pharmacist(id, pharmacist_id) VALUES ('9', '7');
INSERT INTO rating_pharmacist(id, pharmacist_id) VALUES ('10', '8');

INSERT INTO rating_pharmacy(id, pharmacy_id) VALUES ('11', '4');
INSERT INTO rating_pharmacy(id, pharmacy_id) VALUES ('12', '4');
INSERT INTO rating_pharmacy(id, pharmacy_id) VALUES ('13', '4');
INSERT INTO rating_pharmacy(id, pharmacy_id) VALUES ('14', '1');
INSERT INTO rating_pharmacy(id, pharmacy_id) VALUES ('15', '1');

INSERT INTO patient_allergies(patient_id, medicine_id) VALUES ('3', '2');
INSERT INTO patient_allergies(patient_id, medicine_id) VALUES ('3', '4');

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
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (17, 5);