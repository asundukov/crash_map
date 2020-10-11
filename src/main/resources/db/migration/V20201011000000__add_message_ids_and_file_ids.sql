ALTER TABLE accident_message
    ADD COLUMN tg_message_id bigint not null default 0;

ALTER TABLE accident_message
    ALTER COLUMN tg_message_id drop default ;

ALTER TABLE accident_message_media
    ADD COLUMN tg_file_id varchar(1024) not null default '';

ALTER TABLE accident_message_media
    ALTER COLUMN tg_file_id drop default ;

ALTER TABLE accident_message_media RENAME TO accident_message_file;

ALTER TABLE accident_message_file
    RENAME COLUMN accident_message_media_id TO accident_message_file_id;
ALTER TABLE accident_message_file
    RENAME COLUMN media_type TO file_type;
