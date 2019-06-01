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
#