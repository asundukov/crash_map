ALTER INDEX accident_message_media_accident_message_id
    RENAME TO accident_message_file_accident_message_id;

CREATE TABLE accident_message_contact (
        accident_message_contact_id SERIAL not null PRIMARY KEY ,
        accident_message_id int REFERENCES accident_message(accident_message_id),
        first_name varchar(1024) not null,
        last_name varchar(1024) not null,
        phone varchar(255) not null,
        telegram_user_id bigint,
        vcard TEXT not null DEFAULT '',
        created_on TIMESTAMP not null DEFAULT now()
);

CREATE INDEX accident_message_contact_accident_message_id
    on accident_message_contact(accident_message_id);

CREATE TABLE accident_message_location (
    accident_message_location_id SERIAL not null PRIMARY KEY ,
    accident_message_id int REFERENCES accident_message(accident_message_id),
    latitude double precision not null,
    longitude double precision not null,
    address varchar(1024) not null,
    title varchar(255) not null,
    foursquareId varchar(1024),
    foursquareType varchar(1024),
    created_on TIMESTAMP not null DEFAULT now()
);

CREATE INDEX accident_message_location_accident_message_id
    on accident_message_location(accident_message_id);
