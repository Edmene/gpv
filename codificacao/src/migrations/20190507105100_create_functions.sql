DELIMITER '
'
CREATE OR REPLACE FUNCTION passenger_creation(sys_user users, passenger passengers) RETURNS BOOLEAN
    AS $$
declare

    user_id integer;
begin
INSERT INTO users(name,password,extra,type) VALUES (sys_user.name,
                                                    sys_user.password,
                                                    sys_user.extra,
                                                    sys_user.type)
                                                   RETURNING id INTO user_id;

passenger.user_id = user_id;

INSERT into passengers(user_id,name,surname,cpf,rg,
                       birth_date,telephone,email)
VALUES (passenger.user_id,
        passenger.name,
        passenger.surname,
        passenger.cpf,
        passenger.rg,
        passenger.birth_date,
        passenger.telephone,
        passenger.email);
return true;
end
    $$ language plpgsql;'