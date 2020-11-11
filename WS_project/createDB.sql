CREATE DATABASE employeeDB;
USE employeeDB;
CREATE TABLE employee (
id INT AUTO_INCREMENT PRIMARY KEY,
first_name varchar(20),
last_name varchar(20),
username varchar(20),
password varchar(20),
email varchar(30)
);


insert into employee(first_name, last_name, username, password, email) values('Natacha', 'GRUMBACH', 'ngrumbach','password','ngrumbach@gmail.com');


CREATE DATABASE vehiclesDB;
USE vehiclesDB;
CREATE TABLE vehicle (
id INT AUTO_INCREMENT PRIMARY KEY,
make varchar(20),
model varchar(20),
year INT,
seating_capacity INT,
fuel_type varchar(20),
transmission varchar(20),
price_euros FLOAT
);


insert into vehicle(make, model, year, seating_capacity, fuel_type, transmission, price_euros) values('Renault', 'Clio', 2019, 5, 'Diesel', 'Manual', 16000);
insert into vehicle(make, model, year, seating_capacity, fuel_type, transmission, price_euros) values('Renault', 'Clio', 2020, 5, 'Gasoline', 'Manual', 18000);
insert into vehicle(make, model, year, seating_capacity, fuel_type, transmission, price_euros) values('Peugeot', '208', 2019, 5, 'Diesel', 'Manual', 17000);
insert into vehicle(make, model, year, seating_capacity, fuel_type, transmission, price_euros) values('Peugeot', '308', 2019, 5, 'Diesel', 'Manual', 22000);
insert into vehicle(make, model, year, seating_capacity, fuel_type, transmission, price_euros) values('Peugeot', '5008', 2020, 7, 'Diesel', 'Manual', 26000);
insert into vehicle(make, model, year, seating_capacity, fuel_type, transmission, price_euros) values('Ford', 'Mustang', 1964, 5, 'Gasoline', 'Manual', 35000);
