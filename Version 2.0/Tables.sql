CREATE TABLE passenger (
    passport_number CHAR(9),
    last_name VARCHAR2(30) NOT NULL,
    first_name VARCHAR2(30) NOT NULL,
    phone_number VARCHAR2(20) NOT NULL,
    email_address VARCHAR2(30) NOT NULL,
    birthdate DATE NOT NULL,
    
    CONSTRAINT passenger_pk PRIMARY KEY (passport_number),
    CONSTRAINT phone_number UNIQUE (phone_number),
    CONSTRAINT email_address UNIQUE (email_address)
);

CREATE TABLE plane (
    model VARCHAR2(7),
    number NUMBER(3,0),
    brand VARCHAR2(30) NOT NULL,

    CONSTRAINT plane_pk PRIMARY KEY (model, number)
);

CREATE TABLE pilot (
    licence_number CHAR(7),
    name VARCHAR2(30) NOT NULL,
    flying_time TIMESTAMP NOT NULL,

    CONSTRAINT pilot_pk PRIMARY KEY (licence_number)
);

CREATE TABLE airport (
    code CHAR(3),
    name VARCHAR2(30),
    country VARCHAR2(30),

    CONSTRAINT airport_pk PRIMARY KEY (code)
);

CREATE TABLE departure_gate (
    departure_airport VARCHAR2(50),
    terminal CHAR(1),
    number CHAR(2),

    CONSTRAINT departure_gate_pk PRIMARY KEY (departure_airport, terminal, number),
    CONSTRAINT departure_airport_fk FOREIGN KEY (departure_airport) REFERENCES airport,
);

CREATE TABLE arrival_gate (
    arrival_airport VARCHAR2(50),
    terminal CHAR(1),
    number CHAR(2),

    CONSTRAINT arrival_gate_pk PRIMARY KEY (arrival_airport, terminal, number),
    CONSTRAINT arrival_airport_fk FOREIGN KEY (arrival_airport) REFERENCES airport,
);

CREATE TABLE flight (
	number CHAR(6),
    departure_time DATE NOT NULL,
    arrival_time DATE NOT NULL,
    is_meal_on_board BOOLEAN NOT NULL,
    meal_description CLOB,
    departure_gate VARCHAR2(50),
    arrival_gate VARCHAR2(50),
    pilot VARCHAR2(50),
    plane VARCHAR2(50),
    
    CONSTRAINT flight_pk PRIMARY KEY (number),
    CONSTRAINT departure_gate_fk FOREIGN KEY (departure_gate) REFERENCES departure_gate,
    CONSTRAINT arrival_gate_fk FOREIGN KEY (arrival_gate) REFERENCES arrival_gate,
    CONSTRAINT pilot_fk FOREIGN KEY (pilot) REFERENCES pilot,
    CONSTRAINT plane_fk FOREIGN KEY (plane) REFERENCES plane
);

CREATE TABLE class (
    name VARCHAR2(30),

    CONSTRAINT class_pk PRIMARY KEY (name)
);

CREATE TABLE seat (
    row NUMBER(2,0),
    column CHAR(1),
    is_on_window_side BOOLEAN NOT NULL,
    passenger VARCHAR2(50),
    flight VARCHAR2(50),
    class VARCHAR2(50),
    
    CONSTRAINT seat_pk PRIMARY KEY (row, column, flight),
	CONSTRAINT passenger_fk FOREIGN KEY (passenger) REFERENCES passenger,
	CONSTRAINT flight_fk FOREIGN KEY (flight) REFERENCES flight,
	CONSTRAINT class_fk FOREIGN KEY (class) REFERENCES class
);