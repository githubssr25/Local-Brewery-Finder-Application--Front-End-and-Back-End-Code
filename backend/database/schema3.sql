BEGIN TRANSACTION;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE food (
        food_id SERIAL, 
        food_name varchar(255) NOT NULL DEFAULT '',
        CONSTRAINT PK_food PRIMARY KEY (food_id)
);

CREATE TABLE breweries (
        brewery_id SERIAL NOT NULL,
        brewery_name varchar(255) NOT NULL,
        contact_info varchar(255) NOT NULL DEFAULT '',
        brewery_history varchar(2000) NOT NULL DEFAULT '',
        operation_time varchar (255) NOT NULL DEFAULT '',
        address varchar(255) NOT NULL DEFAULT '',
        city varchar(255) NOT NULL DEFAULT '',
        state_abrev varchar(255) NOT NULL DEFAULT '',
        zip varchar(255) NOT NULL DEFAULT '',
        website varchar(255) NOT NULL DEFAULT '',
        image varchar(1000) NOT NULL DEFAULT '',
        active boolean NOT NULL DEFAULT true,
        food_served int NOT NULL DEFAULT 1,
        CONSTRAINT PK_breweries PRIMARY KEY (brewery_id),
        CONSTRAINT FK_breweries_food FOREIGN KEY (food_served) REFERENCES food(food_id)

);

CREATE TABLE user_breweries (
        user_id int NOT NULL,
        brewery_id int NOT NULL,
        CONSTRAINT PK_user_breweries PRIMARY KEY (user_id, brewery_id),
        CONSTRAINT FK_user_breweries_users FOREIGN KEY (user_id) REFERENCES users(user_id),
        CONSTRAINT FK_user_breweries_breweries FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id)
);

CREATE TABLE beers (
        beer_id SERIAL,
        beer_name varchar(255) NOT NULL,
        beer_type varchar(50) NOT NULL,
        beer_description varchar(255) NOT NULL,
        abv float NOT NULL,
        image varchar(1000) NOT NULL,
        gluten_free boolean DEFAULT FALSE,
        brewery_id int NOT NULL,
        CONSTRAINT PK_beers PRIMARY KEY (beer_id),
        CONSTRAINT FK_beers_breweries FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id)
);


CREATE TABLE reviews (
        review_id SERIAL NOT NULL,
        reviewer_name varchar(50) NOT NULL,
        review_text varchar(1000) NOT NULL,
        review_stars int NOT NULL,
        review_type varchar(10),
        user_id int NOT NULL,
        CONSTRAINT PK_reviews PRIMARY KEY (review_id),
        CONSTRAINT FK_reviews_users FOREIGN KEY (user_id) REFERENCES users(user_id)
        
);

CREATE TABLE review_of (
        review_id int NOT NULL,
        brewery_id int,
        beer_id int,
        CONSTRAINT PK_review_of PRIMARY KEY (review_id, brewery_id, beer_id),
        CONSTRAINT FK_review_of_reviews FOREIGN KEY (review_id) REFERENCES reviews(review_id),
        CONSTRAINT FK_review_of_breweries FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id),
        CONSTRAINT FK_review_of_beers FOREIGN KEY (beer_id) REFERENCES beers(beer_id)
        
);


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('brewer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');

INSERT INTO food (food_name) VALUES ('N/A'), ('Bar Food'), ('American'), ('Seafood'), ('Italian'), ('Vegan'), ('Global Cuisine'), ('Food Trucks');

INSERT INTO breweries (brewery_name, contact_info, brewery_history, operation_time, address, city, state_abrev, zip, website, image, active, food_served)
VALUES ('HitchHiker', '(412) 343-1950', 'Our original pub (and our former brewery) is located in the heart of Mt. Lebanon and offers both beer and food.
Our new brewery and taproom located in historic Sharpsburg, in a building once home to Fort Pitt Brewing Co., is an unique spot to enjoy all of our beers, as well as our pub fare.
Our knowledgeable beer staff will guide you through a great experience at both locations. We remain committed to brewing the best beer possible with the lowest environmental impact.', 
'Mon: Closed, Tues: 4-10PM, Wed: 4-10PM, Thur: 4-10PM, Fri: 4-10PM, Sat:12-10PM, Sun:12-8PM', '1500 S Canal St #2541', 'Pittsburgh', 'PA', '15215', 'https://hitchhiker.beer/', 'https://hitchhiker.beer/wp-content/uploads/hbbc-social-share-image.png', true, 8), 
('Eleventh Hour', '(412) 676-8034', 'No History available.', 'Mon: Closed, Tues: Closed, Wed: 5-11PM, Thur: 5-11PM, Fri:5-11PM, Sat: 12-11PM, Sun: 1-7PM', '3711 Charlotte St','Pittsburgh','PA','15201',
'https://www.11thhourbrews.com/', 'https://lh3.googleusercontent.com/Fg-N3e4p6PI3df8y3zL1H3jl-2KHdZFqnl0bIxRhLlSbW5nONMXhn4vAofvcnrJv6RoeCoPnjmwQOcV41oHUA8Oj0azFIQ=s750', true, 8),
('The Church Brew Works', '(412) 688-8200', 'On the 6th of August in 1993, the church was put under an act of suppression by the Bishop of Pittsburgh. The building lay dormant until the construction began in early 1996. It was almost three years to the day when The Church Brew Works reopened the doors of St. John
Church Restored To Former Glory As much painstaking effort was taken in the original construction of the building, the same care was used when the renovation of the church and the rectory was undertaken. Attention to detail and the reuse of existing fixtures all help to create a spectacular atmosphere.' ,
'Mon: 4-10:15PM, Tues: 4-10:15PM, Wed: 4-10:15PM, Thur: 4-10:15PM, Fri: 12:30-11PM, Sat: 12-11PM, Sun: 12:30-9PM', '3525 Liberty Ave','Pittsburgh','PA','15201',
'https://churchbrew.com/', 'https://churchbrew.com/wp-content/themes/yeast/img/logo.png', true, 7);



COMMIT TRANSACTION;