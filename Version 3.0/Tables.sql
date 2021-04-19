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
    model VARCHAR(7),
    plane_number INT,
    brand VARCHAR(30) NOT NULL,

    CONSTRAINT plane_pk PRIMARY KEY (model, plane_number)
);

CREATE TABLE airport (
    identifier CHAR(3),
    full_name VARCHAR(30),
    country VARCHAR(30),

    CONSTRAINT airport_pk PRIMARY KEY (identifier)
);

CREATE TABLE gate (
    terminal CHAR(1),
    gate_number CHAR(2),
    airport VARCHAR(50),

    CONSTRAINT gate_pk PRIMARY KEY (terminal, gate_number, airport),
    CONSTRAINT airport_fk FOREIGN KEY (airport) REFERENCES airport (identifier)
);

CREATE TABLE flight (
	flight_number CHAR(6),
    departure_time DATE NOT NULL,
    arrival_time DATE NOT NULL,
    is_meal_on_board BOOLEAN NOT NULL,
    meal_description TEXT,
    departure_gate VARCHAR(50),
    arrival_gate VARCHAR(50),
    pilot VARCHAR(50),
    plane VARCHAR(50),
    
    CONSTRAINT flight_pk PRIMARY KEY (flight_number),
    CONSTRAINT departure_gate_fk FOREIGN KEY (departure_gate) REFERENCES gate (terminal, gate_number, airport),
    CONSTRAINT arrival_gate_fk FOREIGN KEY (arrival_gate) REFERENCES gate (terminal, gate_number, airport),
    CONSTRAINT pilot_fk FOREIGN KEY (pilot) REFERENCES pilot (licence_number),
    CONSTRAINT plane_fk FOREIGN KEY (plane) REFERENCES plane (model, plane_number)
);

CREATE TABLE class (
    full_name VARCHAR(30),

    CONSTRAINT class_pk PRIMARY KEY (full_name)
);

CREATE TABLE seat (
    seat_row INT,
    seat_column CHAR(1),
    is_on_window_side BOOLEAN NOT NULL,
    passenger VARCHAR(50),
    flight VARCHAR(50),
    class VARCHAR(50),
    
    CONSTRAINT seat_pk PRIMARY KEY (seat_row, seat_column, flight),
	CONSTRAINT passenger_fk FOREIGN KEY (passenger) REFERENCES passenger (passport_number),
	CONSTRAINT flight_fk FOREIGN KEY (flight) REFERENCES flight (flight_number),
	CONSTRAINT class_fk FOREIGN KEY (class) REFERENCES class (full_name)
);