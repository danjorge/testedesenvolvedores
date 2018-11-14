CREATE DATABASE testeatlanticodb
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

insert into usuario values (1, true, '2018-11-13T15:38:00-0300', 'email@email.com', 'admin', 'usuario', 'admin');
insert into role values(1, 1);