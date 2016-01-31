CREATE TABLE Patient(
    id        serial PRIMARY KEY,
    first_name       varchar(40) NOT NULL,
    last_name       varchar(40) NOT NULL
);

CREATE TABLE Address(
    id        serial PRIMARY KEY,
    address1       varchar(60) NOT NULL,
    address2       varchar(60),
    city       varchar(30) NOT NULL,
    state       varchar(30) NOT NULL,
    zip_code       varchar(30) NOT NULL,
    type       varchar(30) NOT NULL
);

CREATE TABLE PatientAddress(
    id        serial PRIMARY KEY,
    patient_id INT references Patient(id),
    address_id INT references Address(id)
);

CREATE TABLE Refill(
    id        serial PRIMARY KEY,
    prescription_name varchar(60) NOT NULL
);

CREATE TABLE PrescriptionOrder(
    id        serial PRIMARY KEY,
    address_id INT references Address(id),
    patient_id INT references Patient(id)
);

CREATE TABLE OrderRefill(
    id        serial PRIMARY KEY,
    order_id INT references PrescriptionOrder(id),
    refill_id INT references Refill(id)
);
