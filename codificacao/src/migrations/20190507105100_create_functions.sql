CREATE OR REPLACE FUNCTION passenger_creation(sys_user users%rowtype, passenger passengers%rowtype) AS
        $$
        declare
            user_id integer;
        begin
            INSERT INTO users(name,password,extra,type) VALUES (sys_user)
                RETURNING id INTO user_id;

            passenger.user_id = user_id;

            INSERT into passengers(user_id,name,surname,cpf,rg,
                                    birth_date,telephone,email)
                                    VALUES (passenger);
        end;
    $$;