DROP TABLE seat;
DROP TABLE class;
DROP TABLE flight;
DROP TABLE passenger;
DROP TABLE pilot;
DROP TABLE gate;
DROP TABLE airport;
DROP TABLE plane;

#AirlineDataBase

CREATE TABLE passenger (
    passport_number CHAR(9),
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email_address VARCHAR(50) NOT NULL,
    birthdate DATE NOT NULL,
    
    CONSTRAINT passenger_pk PRIMARY KEY (passport_number),
    CONSTRAINT phone_number UNIQUE (phone_number),
    CONSTRAINT email_address UNIQUE (email_address)
);

CREATE TABLE pilot (
    licence_number CHAR(7),
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email_address VARCHAR(50) NOT NULL,
    flying_time TIME NOT NULL,

    CONSTRAINT pilot_pk PRIMARY KEY (licence_number),
    CONSTRAINT phone_number UNIQUE (phone_number),
    CONSTRAINT email_address UNIQUE (email_address)
);

CREATE TABLE plane (
    plane_id INT AUTO_INCREMENT,
    model VARCHAR(7) NOT NULL,
    brand VARCHAR(30) NOT NULL,
    
    CONSTRAINT plane_pk PRIMARY KEY (plane_id)
);

CREATE TABLE airport (
    code CHAR(3),
    name VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,

    CONSTRAINT airport_pk PRIMARY KEY (code)
);

CREATE TABLE gate (
    gate_id VARCHAR(3),
    terminal CHAR(1),
    number INT,
    airport VARCHAR(50),
	
    CONSTRAINT gate_id UNIQUE (terminal, number, airport),
    CONSTRAINT gate_pk PRIMARY KEY (gate_id),
    CONSTRAINT airport_fk FOREIGN KEY (airport) REFERENCES airport (code)
);

CREATE TABLE flight (
	number CHAR(6),
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    is_meal_on_board BOOLEAN NOT NULL,
    meal_description VARCHAR(400),
    departure_gate VARCHAR(50) NOT NULL,
    arrival_gate VARCHAR(50) NOT NULL,
    pilot VARCHAR(50) NOT NULL,
    plane INT NOT NULL,
    
    CONSTRAINT flight_pk PRIMARY KEY (number),
    CONSTRAINT departure_gate_fk FOREIGN KEY (departure_gate) REFERENCES gate (gate_id),
    CONSTRAINT arrival_gate_fk FOREIGN KEY (arrival_gate) REFERENCES gate (gate_id),
    CONSTRAINT pilot_fk FOREIGN KEY (pilot) REFERENCES pilot (licence_number),
    CONSTRAINT plane_fk FOREIGN KEY (plane) REFERENCES plane (plane_id)
);

CREATE TABLE class (
    class_id INT AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,

    CONSTRAINT class_pk PRIMARY KEY (class_id)
);

CREATE TABLE seat (
    seat_id VARCHAR(4),
    seat_row INT,
    seat_column CHAR(1),
    is_on_window_side BOOLEAN NOT NULL,
    passenger VARCHAR(50),
    flight VARCHAR(50),
    seat_class INT NOT NULL,
    
    CONSTRAINT seat_id UNIQUE (seat_row, seat_column, flight),
    CONSTRAINT seat_pk PRIMARY KEY (seat_id),
	CONSTRAINT passenger_fk FOREIGN KEY (passenger) REFERENCES passenger (passport_number),
	CONSTRAINT flight_fk FOREIGN KEY (flight) REFERENCES flight (number),
	CONSTRAINT seat_class_fk FOREIGN KEY (seat_class) REFERENCES class (class_id)
);

INSERT INTO pilot
VALUES
    ('BTD4596', 'Chantal', 'Bertrand', '0485966655', 'chantal.bertrand@henallux.be', '113:32:52'),
    ('DBS1562', 'Françoise', 'Dubisy', '0426352633', 'francoise.dubisy@henallux.be', '22:48:33'),
    ('LCR1632', 'Christophe', 'Leclere', '0456969896', 'christophe.leclere@henallux.be', '84:56:10'),
    ('PRT5595', 'Cécile', 'Pirotte', '0477665595', 'cecile.pirotte@henallux.be', '21:25:47'),
    ('CRL5632', 'Christine', 'Charlier', '0477879632', 'christine.charlier@henallux.be', '68:12:55'),
    ('DRW1563', 'Corinne', 'Derwa', '0412552638', 'corinne.derwa@henallux.be', '23:45:13'),
    ('DHR1266', 'Natalie', 'Dheur', '0477576635', 'natalie.dheur@henallux.be', '2:18:03'),
    ('SLT7787', 'Samuel', 'Scholtes', '0485996321', 'samuel.scholtes@henallux.be', '52:47:31')
;

INSERT INTO class (name)
VALUES
    ('Première'),
    ('Affaire'),
    ('Économique')
;

INSERT INTO plane (model, brand)
VALUES
    ('A380', 'Airbus'),
    ('A380', 'Airbus'),
    ('A380', 'Airbus'),
    ('A330', 'Airbus'),
    ('A330', 'Airbus'),
    ('737', 'Boeing'),
    ('737', 'Boeing'),
    ('727', 'Boeing')
;

INSERT INTO airport
VALUES
    ('LHR', 'Londres Heathrow', 'Angleterre'),
    ('LAX', 'Los Angeles', 'Californie'),
    ('JFK', 'John F. Kennedy', 'New-York'),
    ('CDG', 'Paris-Charles-de-Gaulle', 'France'),
    ('BRU', 'Bruxelles-National', 'Belgique'),
    ('FCO', 'Léonard-de-Vinci de Rome Fiumicino', 'Italie'),
    ('HND', 'Tokyo-Haneda', 'Japon'),
    ('GIG', 'Rio de Janeiro/Galeão', 'Brésil')
;

INSERT INTO passenger
VALUES
    ('EF4854263', 'Pauline', 'Loréa', '0458595663', 'etu40989@student.henallux.be', '1999-06-13'),
    ('EF7856324', 'Jonathan', 'Smith', '0474855596', 'etu42989@student.henallux.be', '1999-06-13'),
    ('EF3568956', 'Bruce', 'Wayne', '0412458699', 'etu40489@student.henallux.be', '1999-06-13'),
    ('EF1774626', 'Nathan', 'Drake', '0428969565', 'etu40949@student.henallux.be', '1999-06-13'),
    ('EF7474256', 'Edward', 'Snowden', '0445896323', 'etu40982@student.henallux.be', '1999-06-13')
;