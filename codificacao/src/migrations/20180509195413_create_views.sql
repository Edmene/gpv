CREATE VIEW availability_stop_address AS
  SELECT a.*, s.time, s.address_id, address.name as address_name FROM availabilities a
    JOIN stops s on s.id = a.stop_id JOIN addresses address on s.address_id = address.id;

CREATE VIEW count_passenger AS
  SELECT COUNT(*) as num_passengers, c.plan_id FROM
    (SELECT
       re.plan_id,
       re.passenger_id
     FROM reservations re
     GROUP BY re.plan_id, re.passenger_id) c
  GROUP BY c.plan_id;

CREATE VIEW stops_info AS
  SELECT s.id, s.time, a.name as address, a.extra, a.city_id FROM stops s JOIN addresses a ON s.address_id = a.id;

CREATE VIEW destination_plan_city AS
  SELECT dp.plan_id, a.city_id FROM destination_plans dp
    JOIN destinations d ON dp.destination_id = d.id
    JOIN addresses a ON d.address_id = a.id
    GROUP BY dp.plan_id, a.city_id;

CREATE VIEW destination_address AS
  SELECT d.*, a.city_id FROM destinations d
    JOIN addresses a ON d.address_id = a.id;

CREATE VIEW reservation_info_passenger AS
  SELECT * FROM reservations r
    JOIN stops_info si ON si.id = r.stop_id;

CREATE VIEW reservation_info AS
  SELECT rip.*, d.name as driver, v.license_plate as vehicle,
    concat(p.name,' ',p.surname) as passenger FROM reservation_info_passenger rip
    JOIN drivers d ON rip.driver_id = d.id
    JOIN vehicles v ON rip.vehicle_id = v.id
    JOIN passengers p ON rip.passenger_id = p.user_id;

CREATE VIEW passenger_destination_with_info AS
  SELECT pp.*, d.name as destination, a.name as address, a.extra, c.name as city, s.acronym as state FROM passenger_plans pp
    JOIN destinations d ON pp.destination_id = d.id
    JOIN addresses a on d.address_id = a.id
    JOIN cities c on a.city_id = c.id
    JOIN states s on c.state_id = s.id;