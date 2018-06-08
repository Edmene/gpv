CREATE VIEW availability_stop_address AS
  SELECT a.*, s.time, s.address_id, address.name as address_name FROM availabilities a
    JOIN stops s on s.id = a.stop_id JOIN addresses address on s.address_id = address.id;

CREATE VIEW count_passenger AS
  SELECT COUNT(passenger_id) as num_passengers, re.plan_id as plan_id FROM reservations re
  GROUP BY re.plan_id;

CREATE VIEW stops_of_base AS
  SELECT s.id, s.time, a.city_id, 'base' FROM stops s JOIN addresses a ON s.address_id = a.id;

CREATE VIEW stops_of_destinations AS
  SELECT s.id, s.time, a.name, a.city_id, 'target' FROM stops s JOIN addresses a ON s.address_id = a.id;