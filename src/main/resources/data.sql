INSERT INTO vehicle_makes (id, name)
VALUES
(101, 'Mercedes'),
(102, 'Audi'),
(103, 'Hyundai'),
(104, 'Volkswagen'),
(105, 'Renault');

INSERT INTO vehicles (id, make_id, model_name, minimum_drivers_age, license_plates, vehicle_type, price_per_day, color_code)
VALUES
(5001, 101, 'S-500', 25, 'ZG1234AB', 1, 480.00, 'CFB784'),
(5002, 101, 'C-300', 18, 'DA0443FB', 1, 350.00, '0B4619'),
(5003, 101, 'A-180', 18, 'DA4225SV', 0, 120.00, '68bbe3'),
(5004, 102, 'A8', 27, 'ST0513BA', 1, 450.00, '000000'),
(5005, 102, 'Q7', 25, 'ZG5646GV', 3, 420.00, 'FFCA03'),
(5006, 102, 'A1', 18, 'ST5323AV', 0, 125.00, 'DB7100'),
(5007, 103, 'i20', 18, 'ZG1128GB', 0, 100.00, '003060'),
(5008, 103, 'Tucson', 20, 'ZG9873BE', 3, 275.00, '055c9d'),
(5009, 104, 'Golf VII', 19, 'DA9987BR', 0, 190.00, 'FF5403'),
(5010, 105, 'Clio', 18, 'DA3452NM', 0, 120.00, 'F90716');

INSERT INTO customers (id, first_name, last_name, email)
VALUES
(10001, 'John', 'Smith', 'john@example.com'),
(10002, 'Frank', 'Johnson', 'frank@example.com'),
(10003, 'Jane', 'Doe', 'jane@example.com');

INSERT INTO reservations (id, customer_id, vehicle_id, total_price, pickup_time, return_time)
VALUES
(90001, 10001, 5006, 500.00, '2021-11-04 12:30', '2021-11-07 16:00'),
(90002, 10002, 5008, 825.00, '2021-11-12 15:00', '2021-11-15 12:15'),
(90003, 10002, 5007, 200.00, '2021-11-18 16:30', '2021-11-19 21:00'),
(90004, 10003, 5001, 960.00, '2021-11-23 20:15', '2021-11-25 18:45'),
(90005, 10003, 5002, 1400.00, '2021-11-25 10:00', '2021-11-28 20:30'),
(90006,	10003, 5006, 500.00, '2021-11-13 13:30', '2021-11-16 13:30');

