CREATE TABLE states (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  acronym VARCHAR(3) NOT NULL
);

CREATE TABLE cities(
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  state_id INT REFERENCES states
);

CREATE TABLE addresses(
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  extra VARCHAR(100),
  city_id INT REFERENCES cities
);

CREATE TABLE destinations(
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  address_id INT REFERENCES addresses
);

CREATE TABLE users(
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  password VARCHAR(600) NOT NULL,
  extra VARCHAR(200) NOT NULL
);

CREATE TABLE passengers (
  id INT REFERENCES users PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  surname VARCHAR(200) NOT NULL,
  cpf VARCHAR(11) CHECK(cpf ~ '^[0-9]{11}$') NOT NULL UNIQUE,
  rg VARCHAR(20) NOT NULL,
  birth_date DATE NOT NULL,
  telephone VARCHAR(20) CHECK(telephone ~ '^9[0-9]{8}|[0-9]{8}$'),
  email VARCHAR(200) NOT NULL CHECK(email ~ '^[A-Z0-9._%-]+@[A-Z0-9._%-]+\.[A-Z]{2,4}$')
);

CREATE TABLE drivers (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  surname VARCHAR(200) NOT NULL,
  rg VARCHAR(20) NOT NULL,
  cnh VARCHAR(12) NOT NULL UNIQUE
);

CREATE TABLE vehicles (
  id SERIAL NOT NULL PRIMARY KEY,
  capacity SMALLINT NOT NULL CHECK (capacity > 0 AND capacity < 200),
  license_plate VARCHAR(7) NOT NULL UNIQUE CHECK (license_plate ~ '^[A-Z]{3}[0-9]{4}$'),
  model VARCHAR(50) NOT NULL,
  year SMALLINT NOT NULL CHECK (year > 0)
);

CREATE TABLE holidays(
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  date DATE NOT NULL
);

CREATE TABLE plans(
  id SERIAL NOT NULL PRIMARY KEY,
  availability_condition CHAR(1) NOT NULL,
  ticket_price FLOAT CHECK (ticket_price > 0),
  daily_value FLOAT CHECK (daily_value > 0) NOT NULL,
  available_reservations SMALLINT NOT NULL CHECK (plans.available_reservations > 0),
  destination_id INT REFERENCES destinations NOT NULL
);

CREATE TABLE availabilities(
  id SERIAL NOT NULL,
  shift INT NOT NULL,
  day INT NOT NULL,
  plan INT NOT NULL REFERENCES plans,
  PRIMARY KEY (id, shift, day, plan)
);

CREATE TABLE allocations(
  driver_id INT NOT NULL REFERENCES drivers,
  id SERIAL NOT NULL REFERENCES availabilities(id),
  shift INT NOT NULL REFERENCES availabilities(shift),
  day INT NOT NULL REFERENCES availabilities(day),
  plan INT NOT NULL REFERENCES availabilities(plan),
  PRIMARY KEY (driver_id, id, shift, day, plan)
);

CREATE TABLE vehicles_allocations(
  vehicle_id INT NOT NULL REFERENCES vehicles,
  allocation_id INT NOT NULL REFERENCES allocations,
  PRIMARY KEY (vehicle_id, allocation_id)
);

CREATE TABLE stops(
  id SERIAL NOT NULL PRIMARY KEY,
  time TIME NOT NULL,
  address INT NOT NULL REFERENCES addresses
);

CREATE TABLE reservations(
  reservation_type CHAR(1) NOT NULL,
  status BOOLEAN NOT NULL,
  date DATE NOT NULL,
  alteration_date DATE,
  passenger_id INT NOT NULL REFERENCES passengers,
  stop_id INT NOT NULL REFERENCES stops
);
