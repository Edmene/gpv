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
  address_id INT REFERENCES addresses UNIQUE
);

CREATE TABLE users(
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(150) NOT NULL UNIQUE,
  password VARCHAR(600) NOT NULL,
  extra VARCHAR(200) NOT NULL,
  type CHAR(1) NOT NULL
);

CREATE TABLE passengers (
  user_id INT REFERENCES users PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  surname VARCHAR(200) NOT NULL,
  cpf VARCHAR(11) CHECK(cpf ~ '^[0-9]{11}$') NOT NULL UNIQUE,
  rg VARCHAR(20) NOT NULL,
  birth_date DATE NOT NULL,
  telephone VARCHAR(20) CHECK(telephone ~ '^9[0-9]{8}|[0-9]{8}$') NOT NULL,
  email VARCHAR(200) NOT NULL
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
  month SMALLINT NOT NULL CHECK (month BETWEEN 1 AND 12),
  day SMALLINT NOT NULL CHECK (day BETWEEN 1 AND 31)
);

CREATE TABLE plans(
  id SERIAL NOT NULL PRIMARY KEY,
  availability_condition CHAR(1) NOT NULL,
  ticket_price FLOAT CHECK (ticket_price > 0 AND availability_condition IN ('A','P')
  OR ticket_price = 0 AND availability_condition = 'M'),
  daily_value FLOAT CHECK (daily_value > 0 AND availability_condition IN ('A','M')
  OR daily_value = 0 AND availability_condition = 'P') NOT NULL,
  available_reservations SMALLINT NOT NULL CHECK (plans.available_reservations > 0)
);

CREATE TABLE stops(
  id SERIAL NOT NULL PRIMARY KEY,
  time TIME NOT NULL,
  address_id INT NOT NULL REFERENCES addresses
);

CREATE TABLE availabilities(
  day INT NOT NULL,
  shift INT NOT NULL,
  direction INT NOT NULL,
  plan_id INT NOT NULL REFERENCES plans,
  driver_id INT NOT NULL REFERENCES drivers,
  vehicle_id INT NOT NULL REFERENCES vehicles,
  stop_id INT NOT NULL REFERENCES stops,
  PRIMARY KEY (day, shift, direction, plan_id, driver_id, vehicle_id, stop_id)
);

CREATE TABLE reservations(
  reservation_type CHAR(1) NOT NULL,
  status BOOLEAN NOT NULL,
  date DATE,
  alteration_date DATE,
  passenger_id INT NOT NULL REFERENCES passengers,
  day INT NOT NULL,
  shift INT NOT NULL,
  direction INT NOT NULL,
  plan_id INT NOT NULL,
  driver_id INT NOT NULL,
  vehicle_id INT NOT NULL,
  stop_id INT NOT NULL,
  CONSTRAINT availabilities_fk FOREIGN KEY (day, shift, direction, plan_id, driver_id, vehicle_id, stop_id)
  REFERENCES availabilities(day, shift, direction, plan_id, driver_id, vehicle_id, stop_id),
  PRIMARY KEY (day, shift, direction, plan_id, driver_id, vehicle_id, stop_id)
);

CREATE TABLE destination_plans (
  destination_id INT NOT NULL REFERENCES destinations,
  plan_id INT NOT NULL REFERENCES plans,
  PRIMARY KEY (destination_id, plan_id)
);

CREATE TABLE passenger_plans (
  passenger_id INT NOT NULL REFERENCES passengers,
  destination_id INT NOT NULL,
  plan_id INT NOT NULL,
  CONSTRAINT destination_plans_fk FOREIGN KEY (destination_id, plan_id)
  REFERENCES destination_plans(destination_id, plan_id),
  PRIMARY KEY(passenger_id, destination_id, plan_id)
);
