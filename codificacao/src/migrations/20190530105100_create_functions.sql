DELIMITER #
#
CREATE OR REPLACE FUNCTION passenger_creation(user_name users.name%type, secret users.password%type,
                                              u_extra users.extra%type, p_name passengers.name%type, p_surname passengers.surname%type,
                                              p_cpf passengers.cpf%type, p_rg passengers.rg%type, p_telephone passengers.telephone%type,
                                              p_email passengers.email%type, p_birth_date passengers.birth_date%type) RETURNS BOOLEAN
AS $$
declare

    userId integer;
begin

    begin
        INSERT INTO users(name,password,extra,type) VALUES (user_name,
                                                            secret,
                                                            u_extra,
                                                            'P')
                                                           RETURNING id INTO userId;
    exception
        when unique_violation then
            return false;
    end;

    begin
        INSERT into passengers(user_id,name,surname,cpf,rg,
                               birth_date,telephone,email)
        VALUES (userId,
                p_name,
                p_surname,
                p_cpf,
                p_rg,
                p_birth_date,
                p_telephone,
                p_email);
        return true;
    exception
        when unique_violation then
            DELETE FROM users WHERE id = userId;
            return false;
    end;
end
$$ language plpgsql;

CREATE OR REPLACE FUNCTION funtr_delete_passenger_check() RETURNS TRIGGER AS
$$
declare
    planN integer = 0;
    reservationN integer = 0;
begin
    SELECT 1 FROM passenger_plans pp WHERE pp.passenger_id = old.user_id AND pp.status = true LIMIT 1
        into planN;
    SELECT 1 FROM reservations r WHERE r.passenger_id = old.user_id AND r.status = true LIMIT 1
        into reservationN;

    if (
                (case planN
                     when null then 0
                     else planN
                    end) +
                (case reservationN
                     when null then 0
                     else reservationN
                    end)
            != 0) then
        raise exception 'Has active connections with plans or reservations' using errcode = 'HASPR';

    else
        DELETE FROM reservations r WHERE r.passenger_id = old.user_id;
        DELETE FROM passenger_plans pp WHERE pp.passenger_id = old.user_id;
    end if;

    return old;

end;
$$ language plpgsql;

CREATE OR REPLACE FUNCTION funtr_delete_passenger() RETURNS TRIGGER AS
$$
begin
    DELETE FROM users u WHERE u.id = old.user_id;
    return old;
end;
$$ language plpgsql;

CREATE TRIGGER tr_delete_passenger after delete on passengers
    for each row execute procedure funtr_delete_passenger();

CREATE TRIGGER tr_delete_passenger_check before delete on passengers
    for each row execute procedure funtr_delete_passenger_check();


CREATE OR REPLACE FUNCTION passenger_plan_unsubscribe(cod_pas integer, cod_plan integer) returns boolean AS
$$
declare
    reservation_cur cursor (plan integer, pass integer) for SELECT * FROM reservations r WHERE
            r.plan_id = plan AND r.passenger_id = pass;
    pass_destination_cur cursor (plan integer, pass integer) for SELECT * FROM passenger_plans pp WHERE
            pp.passenger_id = pass AND pp.plan_id = plan;
    cursor_data record;

    has_executed integer = 0;

begin

    for cursor_data in reservation_cur(cod_plan, cod_pas) loop
        if(cursor_data.reservation_type != 'P') then
            UPDATE reservations r SET status = false WHERE r.plan_id = cod_plan
                                                       AND r.passenger_id = cod_pas AND r.day = cursor_data.day
                                                       AND r.direction = cursor_data.direction AND r.driver_id = cursor_data.driver_id
                                                       AND r.shift = cursor_data.shift AND r.stop_id = cursor_data.stop_id
                                                       AND r.vehicle_id = cursor_data.vehicle_id;

        else

            UPDATE reservations r SET alteration_date = current_date + 15 WHERE r.plan_id = cod_plan
                                                                            AND r.passenger_id = cod_pas AND r.day = cursor_data.day
                                                                            AND r.direction = cursor_data.direction AND r.driver_id = cursor_data.driver_id
                                                                            AND r.shift = cursor_data.shift AND r.stop_id = cursor_data.stop_id
                                                                            AND r.vehicle_id = cursor_data.vehicle_id;

        end if;

        has_executed := has_executed + 1;
    end loop;

    for cursor_data in pass_destination_cur(cod_plan, cod_pas) loop

        if(!EXISTS(SELECT 1 FROM reservations WHERE plan_id = cursor_data.plan_id
                                                AND passenger_id = cursor_data.passenger_id
                                                AND reservation_type != 'P' AND status = true)) then

            UPDATE passenger_plans pp SET status = false WHERE pp.passenger_id = cursor_data.passenger_id
                                                           AND pp.plan_id = cursor_data.plan_id;
            has_executed := has_executed + 1;

        end if;

    end loop;

    if(has_executed != 0) then
        return true;
    end if;

    return false;

end;


$$ language plpgsql;

CREATE OR REPLACE FUNCTION funtr_delete_availability() RETURNS TRIGGER AS
$$

begin

    if((SELECT COUNT(*) FROM reservations r
        WHERE r.plan_id = old.plan_id AND
                r.vehicle_id = old.vehicle_id AND
                r.stop_id = old.stop_id AND
                r.shift = old.shift AND
                r.driver_id = old.driver_id AND
                r.direction = old.direction AND
                r.day = old.day AND r.status = true
          AND r.alteration_date is null) = 0) then

        delete from reservations r  WHERE r.plan_id = old.plan_id AND
                r.vehicle_id = old.vehicle_id AND
                r.stop_id = old.stop_id AND
                r.shift = old.shift AND
                r.driver_id = old.driver_id AND
                r.direction = old.direction AND
                r.day = old.day;

    else

        update reservations r set alteration_date = current_date+15 WHERE r.plan_id = old.plan_id AND
                r.vehicle_id = old.vehicle_id AND
                r.stop_id = old.stop_id AND
                r.shift = old.shift AND
                r.driver_id = old.driver_id AND
                r.direction = old.direction AND
                r.day = old.day;

    end if;

    return old;

end;

$$ language plpgsql;

CREATE TRIGGER tr_delete_availability before delete on availabilities
    for each row execute procedure funtr_delete_availability();


CREATE OR REPLACE FUNCTION funtr_create_reservation() RETURNS TRIGGER AS
$$

begin

    if((SELECT COUNT(*) FROM reservations r
        WHERE r.plan_id = new.plan_id AND
                r.vehicle_id = new.vehicle_id AND
                r.stop_id = new.stop_id AND
                r.shift = new.shift AND
                r.driver_id = new.driver_id AND
                r.direction = new.direction AND
                r.day = new.day AND r.passenger_id = new.passenger_id
          AND r.status = true) != 0) then

        raise exception 'Attempted insertion of record already present in database';


    end if;

    return new;

end;

$$ language plpgsql;

CREATE TRIGGER tr_create_reservation before insert on reservations
    for each row execute procedure funtr_create_reservation();

CREATE OR REPLACE FUNCTION funtr_create_availability() RETURNS TRIGGER AS
$$

begin

    if((SELECT COUNT(*) FROM availabilities a
        WHERE a.plan_id = new.plan_id AND
                a.vehicle_id = new.vehicle_id AND
                a.stop_id = new.stop_id AND
                a.shift = new.shift AND
                a.driver_id = new.driver_id AND
                a.direction = new.direction AND
                a.day = new.day AND a.status = true) != 0) then

        raise exception 'Attempted insertion of record already present in database';

    end if;

    return new;

end;

$$ language plpgsql;

CREATE TRIGGER tr_create_availability before insert on availabilities
    for each row execute procedure funtr_create_availability();#