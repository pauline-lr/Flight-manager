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

INSERT INTO passenger
VALUES
    ('EF4854263', 'Pauline', 'Loréa', '0458595663', 'etu40989@student.henallux.be', '1999-06-13'),
    ('EF7856324', 'Jonathan', 'Smith', '0474855596', 'etu42989@student.henallux.be', '1999-06-13'),
    ('EF3568956', 'Bruce', 'Wayne', '0412458699', 'etu40489@student.henallux.be', '1999-06-13'),
    ('EF1774626', 'Nathan', 'Drake', '0428969565', 'etu40949@student.henallux.be', '1999-06-13'),
    ('EF7474256', 'Edward', 'Snowden', '0445896323', 'etu40982@student.henallux.be', '1999-06-13')
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

INSERT INTO gate
VALUES
    ('A13LHR', 'A', 13, 'LHR'),
    ('B24JFK', 'B', 24, 'JFK'),
    ('A11LHR', 'A', 11, 'LHR'),
    ('B1BRU', 'B', 1, 'BRU'),
    ('B2BRU', 'B', 2, 'BRU'),
    ('B3BRU', 'B', 3, 'BRU'),
    ('B4BRU', 'B', 4, 'BRU'),
    ('B5BRU', 'B', 5, 'BRU'),
    ('A1BRU', 'A', 1, 'BRU'),
    ('A2BRU', 'A', 2, 'BRU'),
    ('A3BRU', 'A', 3, 'BRU'),
    ('A4BRU', 'A', 4, 'BRU'),
    ('A5BRU', 'A', 5, 'BRU'),
    ('A6BRU', 'A', 6, 'BRU')
;

INSERT INTO flight
VALUES
	('SA7346', '2022-07-05 16:48:00', '2022-07-05 17:48:00', true, 'Choux rouge avec des brocolis et une banane en dessert', 'A13LHR', 'B24JFK', 'DRW1563', 1),
    ('JF4325', '2021-08-05 13:31:00', '2021-08-06 01:34:00', false, null, 'B1BRU', 'A11LHR', 'DBS1562', 3),
    ('OD1124', '2022-02-05 12:20:00', '2022-02-06 10:54:00', false, null, 'B24JFK', 'A2BRU', 'BTD4596', 5)
;

INSERT INTO seat
VALUES
    ('13FSA7346', 13, 'F', true, 'EF4854263', 'SA7346', 1),
    ('25AJF4325', 25, 'A', true, 'EF7856324', 'JF4325', 3),
    ('1BJF4325', 1, 'B', false, 'EF3568956', 'JF4325', 3)
;