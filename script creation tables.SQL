-- DROP TABLE seat;
-- DROP TABLE class;
-- DROP TABLE flight;
-- DROP TABLE passenger;
-- DROP TABLE pilot;
-- DROP TABLE gate;
-- DROP TABLE airport;
-- DROP TABLE plane;

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
    CONSTRAINT email_address UNIQUE (email_address),
    CONSTRAINT passport_number_check 
                CHECK (LENGTH(passport_number) = 9),
    CONSTRAINT first_name_check
				CHECK(LENGTH(first_name) > 0),
    CONSTRAINT last_name_check
				CHECK(LENGTH(last_name) > 0),
    CONSTRAINT phone_number_passenger  
                CHECK(phone_number REGEXP '^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$') ,
    CONSTRAINT email_address_passenger
                CHECK(email_address REGEXP '[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+'),
    CONSTRAINT birthdate_check
                CHECK(birthdate < CURDATE())
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
    CONSTRAINT email_address UNIQUE (email_address),
    CONSTRAINT license_number_check
				CHECK(LENGTH(passport_number) = 7),
    CONSTRAINT first_name_check
				CHECK(LENGTH(first_name) > 0),
    CONSTRAINT last_name_check
				CHECK(LENGTH(last_name) > 0),
	CONSTRAINT phone_number_check
                CHECK(phone_number REGEXP '^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$') ,
    CONSTRAINT email_address_check
                CHECK(email_address REGEXP '[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+')
);

CREATE TABLE plane (
    plane_id INT AUTO_INCREMENT,
    model VARCHAR(7) NOT NULL,
    brand VARCHAR(30) NOT NULL,
    
    CONSTRAINT plane_pk PRIMARY KEY (plane_id),
    CONSTRAINT model_check
				CHECK(LENGTH(model) >= 2),
	CONSTRAINT brand_check
				CHECK(LENGTH(brand) >= 2)
);

CREATE TABLE airport (
    code CHAR(3),
    name VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,

    CONSTRAINT airport_pk PRIMARY KEY (code),
    CONSTRAINT code_check CHECK(code REGEXP '^[A-Z][A-Z][A-Z]$'),
    CONSTRAINT name_check CHECK(LENGTH(name) >= 2),
    CONSTRAINT country_check CHECK(LENGTH(country) >= 2)
);

CREATE TABLE gate (
    gate_id VARCHAR(6),
    terminal CHAR(1),
    number INT,
    airport VARCHAR(50),
	
    CONSTRAINT gate_id UNIQUE (terminal, number, airport),
    CONSTRAINT gate_pk PRIMARY KEY (gate_id),
    CONSTRAINT airport_fk FOREIGN KEY (airport) REFERENCES airport (code),
    CONSTRAINT terminal_gate CHECK(terminal REGEXP '^[A-Z]$')
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
    CONSTRAINT plane_fk FOREIGN KEY (plane) REFERENCES plane (plane_id),
    CONSTRAINT terminal_gate CHECK(terminal REGEXP '^[A-Z]$'), 
    CONSTRAINT arrival_time_check CHECK(departure_time < arrival_time)
);

CREATE TABLE class (
    class_id INT AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,

    CONSTRAINT class_pk PRIMARY KEY (class_id),
	CONSTRAINT name_class CHECK(name IN('Première','Affaire', 'Économique')) 
);

CREATE TABLE seat (
    seat_id VARCHAR(10),
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
	CONSTRAINT seat_class_fk FOREIGN KEY (seat_class) REFERENCES class (class_id),
 	CONSTRAINT seat_row_condition CHECK(seat_row BETWEEN 0 AND 99),
 	CONSTRAINT seat_column_condition CHECK(seat_column REGEXP '^[A-Z]$')
);

