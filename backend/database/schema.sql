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
        food_name varchar(255) NOT NULL,
        CONSTRAINT PK_food PRIMARY KEY (food_id)
);

CREATE TABLE breweries (
        brewery_id SERIAL NOT NULL,
        brewery_name varchar(255) NOT NULL,
        contact_info varchar(255) NOT NULL DEFAULT 'No contact info available.',
        brewery_history varchar(2000) NOT NULL DEFAULT 'No history available.',
        operation_time varchar (255) NOT NULL DEFAULT 'No operation times available.',
        address varchar(255) NOT NULL DEFAULT 'No address available.',
        city varchar(255) NOT NULL DEFAULT 'No city available.',
        state_abrev varchar(255) NOT NULL DEFAULT 'No state available.',
        zip varchar(255) NOT NULL DEFAULT 'No zip code available.',
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
        beer_name varchar(100) NOT NULL,
        beer_type varchar(50) NOT NULL,
        beer_description varchar(1000) NOT NULL,
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
        review_title varchar(100) NOT NULL,
        review_text varchar(1000) NOT NULL,
        review_stars int NOT NULL,
        review_type varchar(100),
        user_id int NOT NULL,
        target_id int NOT NULL,
        CONSTRAINT PK_reviews PRIMARY KEY (review_id),
        CONSTRAINT FK_reviews_users FOREIGN KEY (user_id) REFERENCES users(user_id)
        
);

CREATE TABLE beer_reviews (
        review_id int NOT NULL,
        beer_id int NOT NULL,
        CONSTRAINT PK_beer_reviews PRIMARY KEY (review_id, beer_id),
        CONSTRAINT FK_beer_reviews_reviews FOREIGN KEY (review_id) REFERENCES reviews(review_id),
        CONSTRAINT FK_beer_reviews_beers FOREIGN KEY (beer_id) REFERENCES beers(beer_id)

);

CREATE TABLE brewery_reviews (
        review_id int NOT NULL,
        brewery_id int NOT NULL,
        CONSTRAINT PK_brewery_reviews PRIMARY KEY (review_id, brewery_id),
        CONSTRAINT FK_brewery_reviews_reviews FOREIGN KEY (review_id) REFERENCES reviews(review_id),
        CONSTRAINT FK_brewery_reviews_breweries FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id)

);

CREATE TABLE events (
        event_id SERIAL NOT NULL,
        event_name varchar(100) NOT NULL,
        brewery_id int NOT NULL,
        event_description varchar(2000) NOT NULL,
        event_date date NOT NULL,
        event_start_time time NOT NULL,
        event_end_time time NOT NULL,
        CONSTRAINT PK_events PRIMARY KEY (event_id),
        CONSTRAINT FK_events_breweries FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id)
);


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('brewer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');
INSERT INTO users (username,password_hash,role) VALUES ('brewer2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');
INSERT INTO users (username,password_hash,role) VALUES ('bcze','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('cel','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO food (food_name) VALUES ('N/A'), ('Bar Food'), ('American'), ('Seafood'), ('Italian'), ('Vegan'), ('Global Cuisine'), ('Food Trucks');

INSERT INTO breweries (brewery_name, contact_info, brewery_history, operation_time, address, city, state_abrev, zip, website, image, active, food_served)
VALUES ('HitchHiker', '(412) 343-1950', 'Our original pub (and our former brewery) is located in the heart of Mt. Lebanon and offers both beer and food.
Our new brewery and taproom located in historic Sharpsburg, in a building once home to Fort Pitt Brewing Co., is an unique spot to enjoy all of our beers, as well as our pub fare.
Our knowledgeable beer staff will guide you through a great experience at both locations. We remain committed to brewing the best beer possible with the lowest environmental impact.', 
'Mon: Closed, Tues: 4-10PM, Wed: 4-10PM, Thur: 4-10PM, Fri: 4-10PM, Sat: 12-10PM, Sun: 12-8PM', '1500 S Canal St #2541', 'Pittsburgh', 'PA', '15215', 'https://hitchhiker.beer/', 'https://hitchhiker.beer/wp-content/uploads/hbbc-social-share-image.png', true, 8),
('Eleventh Hour', '(412) 676-8034', 'No History available.', 'Mon: Closed, Tues: Closed, Wed: 5-11PM, Thur: 5-11PM, Fri: 5-11PM, Sat: 12-11PM, Sun: 1-7PM', '3711 Charlotte St','Pittsburgh','PA','15201',
'https://www.11thhourbrews.com/', 'https://lh3.googleusercontent.com/Fg-N3e4p6PI3df8y3zL1H3jl-2KHdZFqnl0bIxRhLlSbW5nONMXhn4vAofvcnrJv6RoeCoPnjmwQOcV41oHUA8Oj0azFIQ=s750', true, 8),
('The Church Brew Works', '(412) 688-8200', 'On the 6th of August in 1993, the church was put under an act of suppression by the Bishop of Pittsburgh. The building lay dormant until the construction began in early 1996. It was almost three years to the day when The Church Brew Works reopened the doors of St. John
Church Restored To Former Glory As much painstaking effort was taken in the original construction of the building, the same care was used when the renovation of the church and the rectory was undertaken. Attention to detail and the reuse of existing fixtures all help to create a spectacular atmosphere.' ,
'Mon: 4-10:15PM, Tues: 4-10:15PM, Wed: 4-10:15PM, Thur: 4-10:15PM, Fri: 12:30-11PM, Sat: 12-11PM, Sun: 12:30-9PM', '3525 Liberty Ave','Pittsburgh','PA','15201',
'https://churchbrew.com/', 'https://churchbrew.com/wp-content/themes/yeast/img/logo.png', true, 7);

INSERT INTO user_breweries (user_id, brewery_id) VALUES 
(3, 1),(3, 2),(3, 3);

INSERT INTO beers (beer_name, beer_type, beer_description, abv, image, gluten_free, brewery_id) VALUES
('Cosmic Void', 'IPA', 'Brewed and dry hopped with Sabro. Notes of Pineapple, Coconut, Tropical Fruit, and Cedar.', 7.0,
'https://hitchhiker.beer/wp-content/uploads/HHBC_CosmicVoid_bc_01102019_CMYK.jpg', false, 1),
('Theories Without Data', 'Double IPA', 'Brewed with Oats and Wheat. Hopped with Nelson Sauvin, Amarillo, and Simcoe.', 8.5,
'https://hitchhiker.beer/wp-content/uploads/HHBC_TheorieswoDataDIPA_bc_49375x7-1200x757.jpg', false, 1),
('Fruit Rush- Raspberry', 'Smoothie Sour Shandy', 'Brewed with Oats and Wheat. Conditioned on Lemon and Raspberry.', 4.8,
'https://hitchhiker.beer/wp-content/uploads/HHBC_FruitRushRaspberry_bc_49375x7-1200x757.jpg', false, 1),
('Dream Cannon No.3 Starburst', 'Gose', 'Dream Cannon No. 3 bursts out at you with a healthy dose of fresh strawberry, cherry, orange and lemon zest. This slightly tart, crisp sour ale is balanced by the addition of lactobacillus and pink himalayan sea salt. The resulting elixir is light, fruity and refreshing. At only 4.4% it is a perfect way to sip away the sunlight.', 4.4,
'https://images.squarespace-cdn.com/content/v1/581b7f8f5016e11e7853bdb8/1625017619420-ER67IHDL4U7QT25SA1IZ/DC3+2.jpg?format=750w', false, 2), 
('Alternate Course No.2 Watermelon', 'Sour Ale', 'Starting with burst of watermelon aroma, this sour ale features a sharp, tangy jolt of lemon that meanders across your tongue before finishing with a satisfying sweet and sour twang. A change of direction from our previous fruited sours, Alternate Course No. 2 also reveals a defined Lemon Drop hop character that amplifies the beer’s citrus notes. So grab a can and set off in a new direction... you might end up in a more pleasant place than where you started.', 7.0,
'https://images.squarespace-cdn.com/content/v1/581b7f8f5016e11e7853bdb8/1627573087970-7QMUT5OS4CQWWRKFZ8VU/AltCrsWatermelon.jpg?format=750w', false, 2),
('Paper Birds 2021', 'Sour, Farmhouse', 'Our newest edition of Paper Birds, we took a wonderful sour base fermented with a farmhouse strain and stuffed it full of Pineapple, Tangerine, Mango, Apricot, Raspberry & Blackberry. A crazy pastel color and a delicious edition to our lineup.', 5.7,
'https://images.squarespace-cdn.com/content/v1/581b7f8f5016e11e7853bdb8/1623179594014-DZ6O9MY3WHAJWU8B72UE/COleBearSQncr.jpg?format=750w', false, 2),
('Pious Monk Dunkel', 'German-Style Dunkel', 'Our flagship brew is dark in color, but clean & refreshing.  Caramel & Chocolate carry throughout with a slightly grassy finish.', 5.5,
'https://churchbrew.com/wp-content/themes/yeast/img/logo.png', false, 3), 
('Tropical Seltzer', 'Seltzer', 'A crisp mango infused seltzer.  One hundred calories of refreshment!', 4.8,
'https://churchbrew.com/wp-content/themes/yeast/img/logo.png', false, 3),
('Southern Tier Nu Haze', 'IPA', 'Citrus and ripe melon notes in this hazy IPA', 6.0,
'https://churchbrew.com/wp-content/themes/yeast/img/logo.png', false, 3);
;

INSERT INTO reviews (reviewer_name, review_title, review_text, review_stars, review_type, user_id, target_id) VALUES
('Bartholomew', 'I love this place', 'Hitchhiker is a really cool brewery.', 4, 'Brewery', 4, 9),
('Jack', 'This place is okay', 'Eleventh Hour has really interesting beers, but the atmosphere in the tap room sucks.', 3, 'Brewery', 4, 1),
('Craig', 'Amazing place', 'Church Brew has a really cool design. The beer is good too.', 5, 'Brewery', 4, 6),
('Sally', 'Worst service ever', 'Church Brew sucks.', 1, 'Brewery', 5, 10),
('John', 'Do not recommend', 'Cosmic Void tastes okay.', 2, 'Beer', 4, 3),
('Jimmy', 'Heavy improvments', 'This years installment of Paper Birds is much better than last years', 4, 'Beer', 4, 6),
('John', 'Top tier beer', 'This Tropical Seltzer is the greatest beer ever', 5, 'Beer', 4, 8),
('Sally', 'I have no taste buds', 'This IPA could use some work', 1, 'Beer', 5, 9);

INSERT INTO brewery_reviews (review_id, brewery_id) 
VALUES (1, 1), (2, 2), (3, 3), (4, 3);

INSERT INTO beer_reviews (review_id, beer_id) 
VALUES (5, 1), (6, 6), (7, 8), (8, 9);

INSERT INTO events (event_name, brewery_id, event_description, event_date, event_start_time, event_end_time)
VALUES ('Cool Event', 1, 'This event is gonna be really cool, do not miss it!', '2021-09-17', '20:00', '24:00'),
('Another Cool Event', 1, 'This event is gonna be even cooler than the last one, definitely do not miss it!', '2021-09-18', '22:00', '2:00'),
('Cool Event', 2, 'We want to have our own cool event, so here it is!', '2021-09-24', '24:00', '1:00');


COMMIT TRANSACTION;
