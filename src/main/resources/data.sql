insert into 3_1_2_schema.roles(name)
values ('ROLE_ADMIN');

insert into 3_1_2_schema.roles(name)
values ('ROLE_USER');

insert into 3_1_2_schema.users(first_name, last_name, age, email, phone_number, password)
values ('admin', 'admin', 35, 'admin@gmail.com', '+375297778899',
        '$2a$10$mho1WSFBpQDqSOBPVhcNlOUCUOKrWVOYCS8ntlZmpUTYw3B3lPlPe');

insert into 3_1_2_schema.user_role(user_id, role_id)
values (1, 1);

/*логин: admin@gmail.com
пароль: admin*/