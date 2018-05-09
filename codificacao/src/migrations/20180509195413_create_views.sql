CREATE VIEW availability_stop_address AS
  SELECT a.*, s.time, s.address_id, address.name as address_name FROM availabilities a
    JOIN stops s on s.id = a.stop_id JOIN addresses address on s.address_id = address.id;