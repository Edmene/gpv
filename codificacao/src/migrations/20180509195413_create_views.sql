CREATE VIEW availability_stop_address AS
  SELECT a.*, s.time, s.address_id, address.name as address_name FROM availabilities a
    JOIN stops s on s.id = a.stop_id JOIN addresses address on s.address_id = address.id;

CREATE VIEW count_passenger AS
  SELECT COUNT(passenger_id) as num_passengers, re.plan_id as plan_id FROM reservations re
  GROUP BY re.plan_id;

CREATE VIEW stops_info AS
  SELECT s.id, s.time, a.name as address, a.extra, a.city_id FROM stops s JOIN addresses a ON s.address_id = a.id;

CREATE VIEW destination_plan_city AS
  SELECT dp.plan_id, a.city_id FROM destination_plans dp
    JOIN destinations d ON dp.destination_id = d.id
    JOIN addresses a ON d.address_id = a.id
    GROUP BY dp.plan_id, a.city_id;

CREATE VIEW destination_address AS
  SELECT d.*, a.city_id FROM destinations d
    JOIN addresses a ON d.address_id = a.id

