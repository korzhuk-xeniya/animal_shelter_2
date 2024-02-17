CREATE TABLE pet_shelters (
id bigint generated by default as identity primary key,
name_of_shelter VARCHAR,
about_shelter VARCHAR,
address VARCHAR,
phone_number VARCHAR(50)

);
CREATE TABLE user_tg
(
    id                bigint generated by default as identity primary key,
    first_name        VARCHAR not null,
    took_a_pet        BOOLEAN,
    date_time_to_took TIMESTAMP,
    chat_id           bigint not null,
    number  VARCHAR(50)

);

CREATE TABLE report_tg
(
    id                 BIGINT generated by default as identity primary key,
    date_added         TIMESTAMP,
    general_well_being TEXT,
    photo_name         TEXT,
    user_id            BIGINT ,
    check_report       bool
);

CREATE TABLE volunteers
(
    id_volunteer      BIGINT generated by default as identity primary key,
   name_volunteer     TEXT   not null,
   last_name_volunteer     TEXT   not null,
    chat_id_volunteer BIGINT   not null

);
CREATE TABLE animals
(
    id_animal      BIGINT generated by default as identity primary key,
    age_in_month     bigint ,
   name_of_animal     TEXT   ,
   gender            TEXT ,
   photo_link     TEXT   ,
   pet_type         TEXT
);
INSERT INTO animals
(name_of_animal, gender, age_in_month, pet_type, photo_link)
VALUES ('Барсик','м', 5, 'щенок', 'https://img-fotki.yandex.ru/get/6444/28699592.23/0_8fe45_9f57d864_XL.jpg'),
('Джеси','ж', 15, 'собака', 'https://k9rl.com/wp-content/uploads/2015/11/Beagle-1-825x491.jpg'),
('Альма','ж', 34, 'собака с ОВЗ', 'https://storage.myseldon.com/news-pict-7d/7D80726A97A8592B932E12D049072EEB');
