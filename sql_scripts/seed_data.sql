INSERT INTO PATIENT (first_name, last_name) values ('fname1', 'lname1');
INSERT INTO PATIENT (first_name, last_name) values ('fname2', 'lname2');

INSERT INTO ADDRESS (address1, address2, city, state, zip_code, type) values ('add1', 'add2', 'city1', 'state1', 'zip1', 'home1');
INSERT INTO ADDRESS (address1, address2, city, state, zip_code, type) values ('add12', 'add22', 'city1', 'state1', 'zip2', 'home2');

INSERT INTO PATIENTADDRESS (patient_id, address_id) values (1, 1);
INSERT INTO PATIENTADDRESS (patient_id, address_id) values (1, 2);
INSERT INTO PATIENTADDRESS (patient_id, address_id) values (2, 1);
INSERT INTO PATIENTADDRESS (patient_id, address_id) values (2, 2);

INSERT INTO REFILL (prescription_name) values('prescription1');
INSERT INTO REFILL (prescription_name) values('prescription2');
INSERT INTO REFILL (prescription_name) values('prescription3');
INSERT INTO REFILL (prescription_name) values('prescription4');

INSERT INTO PRESCRIPTIONORDER (address_id, patient_id) values (1, 1);
INSERT INTO PRESCRIPTIONORDER (address_id, patient_id) values (2, 2);

INSERT INTO ORDERREFILL (order_id, refill_id) values (1, 1);
INSERT INTO ORDERREFILL (order_id, refill_id) values (1, 2);
INSERT INTO ORDERREFILL (order_id, refill_id) values (2, 3);
INSERT INTO ORDERREFILL (order_id, refill_id) values (1, 4);
