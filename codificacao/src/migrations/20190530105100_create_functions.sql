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
$$ language plpgsql;#