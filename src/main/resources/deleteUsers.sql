create function delete_users_in_range(id_user_from integer, id_user_to integer) returns void
    language plpgsql
as
$$

declare
    v_current_user_id integer;

begin
    v_current_user_id
        := id_user_from;

    while
        v_current_user_id <= id_user_to
        loop
            begin
                execute 'delete from public.users where id = ' || v_current_user_id;
                v_current_user_id := v_current_user_id + 1;
            end;
        end loop;
end;
$$;

alter function delete_users_in_range(integer, integer) owner to postgres;

