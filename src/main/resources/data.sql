DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
                         `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                         `name` varchar(250) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `id` (`id`),
                         UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                         `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                         `first_name` varchar(250) DEFAULT NULL,
                         `last_name` varchar(250) DEFAULT NULL,
                         `age` bigint(20) DEFAULT NULL,
                         `email` varchar(250) DEFAULT NULL,
                         `phone_number` bigint(20) DEFAULT NULL,
                         `password` varchar(250) DEFAULT NULL,

                         PRIMARY KEY (`id`)


) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
                             `user_id` bigint(20) unsigned DEFAULT NULL,
                             `role_id` bigint(20) unsigned DEFAULT NULL,
                             KEY `hasuser` (`user_id`),
                             KEY `hasrole` (`role_id`),
                             CONSTRAINT `hasrole` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT `hasuser` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                             UNIQUE KEY (`user_id` , `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into roles
values (1,'ROLE_ADMIN');

insert into roles
values (2,'ROLE_USER');

insert into users(first_name, last_name, age, email, phone_number, password)
values ('admin', 'admin', 35, 'admin@gmail.com', '+375297778899',
        '$2a$10$mho1WSFBpQDqSOBPVhcNlOUCUOKrWVOYCS8ntlZmpUTYw3B3lPlPe');

insert into user_role
values (1, 1);

/*логин: admin@gmail.com
пароль: admin*/