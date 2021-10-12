DROP TABLE IF EXISTS food CASCADE;
CREATE TABLE food (
       food_id SERIAL,
       food_name varchar(255) NOT NULL DEFAULT '',
        CONSTRAINT PK_food PRIMARY KEY (food_id)
);