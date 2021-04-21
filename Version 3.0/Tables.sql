CREATE TABLE passenger (
    passport_number CHAR(9),
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email_address VARCHAR(30) NOT NULL,
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
    email_address VARCHAR(30) NOT NULL,
    flying_time TIMESTAMP NOT NULL,

    CONSTRAINT pilot_pk PRIMARY KEY (licence_number),
    CONSTRAINT phone_number UNIQUE (phone_number),
    CONSTRAINT email_address UNIQUE (email_address)
);

CREATE TABLE plane (
    plane_id VARCHAR(10),
    model VARCHAR(7),
    number INT,
    brand VARCHAR(30) NOT NULL,
    
	CONSTRAINT plane_id UNIQUE (model, number),
    CONSTRAINT plane_pk PRIMARY KEY (plane_id)
);

CREATE TABLE airport (
    code CHAR(3),
    name VARCHAR(30),
    country VARCHAR(30),

    CONSTRAINT airport_pk PRIMARY KEY (code)
);

CREATE TABLE gate (
    gate_id CHAR(3),
    terminal CHAR(1),
    number CHAR(2),
    airport VARCHAR(50),
	
    CONSTRAINT gate_id UNIQUE (terminal, number),
    CONSTRAINT gate_pk PRIMARY KEY (gate_id, airport),
    CONSTRAINT airport_fk FOREIGN KEY (airport) REFERENCES airport (code)
);

CREATE TABLE flight (
	number CHAR(6),
    departure_time DATE NOT NULL,
    arrival_time DATE NOT NULL,
    is_meal_on_board BOOLEAN NOT NULL,
    meal_description VARCHAR(400),
    departure_airport VARCHAR(50),
    departure_gate VARCHAR(50),
    arrival_airport VARCHAR(50),
    arrival_gate VARCHAR(50),
    pilot VARCHAR(50),
    plane VARCHAR(50),
    
    CONSTRAINT flight_pk PRIMARY KEY (number),
    CONSTRAINT departure_gate_fk FOREIGN KEY (departure_gate, departure_airport) REFERENCES gate (gate_id, airport),
    CONSTRAINT arrival_gate_fk FOREIGN KEY (arrival_gate, arrival_airport) REFERENCES gate (gate_id, airport),
    CONSTRAINT pilot_fk FOREIGN KEY (pilot) REFERENCES pilot (licence_number),
    CONSTRAINT plane_fk FOREIGN KEY (plane) REFERENCES plane (plane_id)
);

CREATE TABLE class (
    name VARCHAR(30),

    CONSTRAINT class_pk PRIMARY KEY (name)
);

CREATE TABLE seat (
    seat_id VARCHAR(4),
    seat_row INT,
    seat_column CHAR(1),
    is_on_window_side BOOLEAN NOT NULL,
    passenger VARCHAR(50),
    flight VARCHAR(50),
    class VARCHAR(50),
    
    CONSTRAINT seat_id UNIQUE (seat_row, seat_column),
    CONSTRAINT seat_pk PRIMARY KEY (seat_id, flight),
	CONSTRAINT passenger_fk FOREIGN KEY (passenger) REFERENCES passenger (passport_number),
	CONSTRAINT flight_fk FOREIGN KEY (flight) REFERENCES flight (number),
	CONSTRAINT class_fk FOREIGN KEY (class) REFERENCES class (name)
);