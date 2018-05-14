CREATE VIEW availability_stop_address AS
  SELECT a.*, s.time, s.address_id, address.name as address_name FROM availabilities a
    JOIN stops s on s.id = a.stop_id JOIN addresses address on s.address_id = address.id;

CREATE VIEW count_passenger AS
  SELECT COUNT(passenger_id) as num_passengers, re.plan_id as plan_id FROM reservations re
  GROUP BY re.plan_id;