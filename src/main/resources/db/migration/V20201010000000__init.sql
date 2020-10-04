CREATE TABLE usr (
    usr_id BIGINT NOT NULL PRIMARY KEY,
    first_name VARCHAR(1024),
    last_name VARCHAR(1024),
    user_name VARCHAR(1042),
    language_code VARCHAR(256),
    created_on TIMESTAMP NOT NULL
);

CREATE TABLE accident (
    accident_id serial not null primary key,
    usr_id bigint not null REFERENCES usr(usr_id),
    is_submitted boolean not null,
    created_on timestamp not null default now()
);

CREATE index accident_usr_id
    ON accident(usr_id);

CREATE TABLE accident_message (
    accident_message_id SERIAL not null PRIMARY KEY ,
    accident_id int REFERENCES accident(accident_id),
    message text not null,
    created_on TIMESTAMP not null DEFAULT now()
);

CREATE INDEX accident_message_accident_id
    on accident_message (accident_id);

CREATE TABLE accident_message_media (
    accident_message_media_id SERIAL not null PRIMARY KEY ,
    accident_message_id int REFERENCES accident_message(accident_message_id),
    file_path varchar(1024) not null,
    media_type VARCHAR(16),
    created_on TIMESTAMP not null DEFAULT now()
);

CREATE INDEX accident_message_media_accident_message_id
    on accident_message_media(accident_message_id);
