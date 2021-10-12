-- First, remove all data from the database.
-- TRUNCATE is like DELETE, but only for removing all records from a table.
-- CASCADE means that records associated with foreign keys will also be removed rather than preventing deletion.
TRUNCATE users, food, breweries, user_breweries, beers, reviews,beer_reviews, brewery_reviews CASCADE;

-

INSERT INTO users (username, password_hash, role)
VALUES ('brewer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER'), ('brewer2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');

INSERT INTO food (food_name)
 VALUES ('N/A'), ('Thai'), ('Korean');

 INSERT INTO breweries (id, brewery_name, contact_info, brewery_history, operation_time, address, city, state_abrev, zip, website, image, active, food_served)
 VALUES (1, 'HitchHiker', '(412) 343-1950', 'Our original pub (and our former brewery) is located in the heart of Mt. Lebanon.',
 'Mon: Closed, Tues: 4-10PM, Wed: 4-10PM, Thur: 4-10PM, Fri: 4-10PM, Sat: 12-10PM, Sun: 12-8PM', '1500 S Canal St #2541', 'Pittsburgh', 'PA', '15215', 'https://hitchhiker.beer/',
 'https://hitchhiker.beer/wp-content/uploads/hbbc-social-share-image.png', true, 8),
 (2, 'Eleventh Hour', '(412) 676-8034', 'No History available.', 'Mon: Closed, Tues: Closed, Wed: 5-11PM, Thur: 5-11PM, Fri: 5-11PM, Sat: 12-11PM, Sun: 1-7PM', '3711 Charlotte St','Pittsburgh','PA','15201',
 'https://www.11thhourbrews.com/',
 'https://lh3.googleusercontent.com/Fg-N3e4p6PI3df8y3zL1H3jl-2KHdZFqnl0bIxRhLlSbW5nONMXhn4vAofvcnrJv6RoeCoPnjmwQOcV41oHUA8Oj0azFIQ=s750', true, 8),
 (3, 'The Church Brew Works', '(412) 688-8200', 'On the 6th of August in 1993.' ,
 'Mon: 4-10:15PM, Tues: 4-10:15PM, Wed: 4-10:15PM, Thur: 4-10:15PM, Fri: 12:30-11PM, Sat: 12-11PM, Sun: 12:30-9PM', '3525 Liberty Ave','Pittsburgh','PA','15201',
 'https://churchbrew.com/', 'https://churchbrew.com/wp-content/themes/yeast/img/logo.png', true, 7);

INSERT INTO user_breweries (user_id, brewery_id) VALUES
(3, 1),(3, 2),(3, 3);

INSERT INTO beers (beer_id, beer_name, beer_type, beer_description, abv, image, gluten_free, brewery_id) VALUES
(1, 'Cosmic Void', 'IPA', 'Brewed and dry hopped with Sabro. Notes of Pineapple, Coconut, Tropical Fruit, and Cedar.', 7.0,
'https://hitchhiker.beer/wp-content/uploads/HHBC_CosmicVoid_bc_01102019_CMYK.jpg', false, 1),
(2, 'Theories Without Data', 'Double IPA', 'Brewed with Oats and Wheat. Hopped with Nelson Sauvin, Amarillo, and Simcoe.', 8.5,
'https://hitchhiker.beer/wp-content/uploads/HHBC_TheorieswoDataDIPA_bc_49375x7-1200x757.jpg', false, 1),
(3, 'Fruit Rush- Raspberry', 'Smoothie Sour Shandy', 'Brewed with Oats and Wheat. Conditioned on Lemon and Raspberry.', 4.8,
'https://hitchhiker.beer/wp-content/uploads/HHBC_FruitRushRaspberry_bc_49375x7-1200x757.jpg', false, 1),
(4, 'Dream Cannon No.3 Starburst', 'Gose', 'Dream Cannon No. 3 bursts out at you with a healthy dose of fresh strawberry, cherry, orange and lemon zest. This slightly tart, crisp sour ale is balanced by the addition of lactobacillus and pink himalayan sea salt. The resulting elixir is light, fruity and refreshing. At only 4.4% it is a perfect way to sip away the sunlight.', 4.4,
'https://images.squarespace-cdn.com/content/v1/581b7f8f5016e11e7853bdb8/1625017619420-ER67IHDL4U7QT25SA1IZ/DC3+2.jpg?format=750w', false, 2),
(5, 'Alternate Course No.2 Watermelon', 'Sour Ale', 'Starting with burst of watermelon aroma, this sour ale features a sharp, tangy jolt of lemon that meanders across your tongue before finishing with a satisfying sweet and sour twang. A change of direction from our previous fruited sours, Alternate Course No. 2 also reveals a defined Lemon Drop hop character that amplifies the beer’s citrus notes. So grab a can and set off in a new direction... you might end up in a more pleasant place than where you started.', 7.0,
'https://images.squarespace-cdn.com/content/v1/581b7f8f5016e11e7853bdb8/1627573087970-7QMUT5OS4CQWWRKFZ8VU/AltCrsWatermelon.jpg?format=750w', false, 2),
(6, 'Paper Birds 2021', 'Sour, Farmhouse', 'Our newest edition of Paper Birds, we took a wonderful sour base fermented with a farmhouse strain and stuffed it full of Pineapple, Tangerine, Mango, Apricot, Raspberry & Blackberry. A crazy pastel color and a delicious edition to our lineup.', 5.7,
'https://images.squarespace-cdn.com/content/v1/581b7f8f5016e11e7853bdb8/1623179594014-DZ6O9MY3WHAJWU8B72UE/COleBearSQncr.jpg?format=750w', false, 2),
(7, 'Pious Monk Dunkel', 'German-Style Dunkel', 'Our flagship brew is dark in color, but clean & refreshing.  Caramel & Chocolate carry throughout with a slightly grassy finish.', 5.5,
'https://churchbrew.com/wp-content/themes/yeast/img/logo.png', false, 3),
(8, 'Tropical Seltzer', 'Seltzer', 'A crisp mango infused seltzer.  One hundred calories of refreshment!', 4.8,
'https://churchbrew.com/wp-content/themes/yeast/img/logo.png', false, 3),
(9, 'Southern Tier Nu Haze', 'IPA', 'Citrus and ripe melon notes in this hazy IPA', 6.0,
'https://churchbrew.com/wp-content/themes/yeast/img/logo.png', false, 3);
;

INSERT INTO reviews (reviewer_id, reviewer_name, review_title, review_text, review_stars, review_type, user_id, target_id) VALUES
(1, 'Brandon Czekalski', 'I love this place', 'Hitchhiker is a really cool brewery.', 4, 'Brewery', 4, 1),
(2, 'Brandon Czekalski', 'This place is okay', 'Eleventh Hour has really interesting beers, but the atmosphere in the tap room sucks.', 3, 'Brewery', 4, 2),
(3, 'Brandon Czekalski', 'Amazing place', 'Church Brew has a really cool design. The beer is good too.', 5, 'Brewery', 4, 3),
(4, 'Celeste', 'Worst service ever', 'Church Brew sucks.', 1, 'Brewery', 5, 3),
(5, 'Brandon Czekalski', 'Do not recommend', 'Cosmic Void tastes okay.', 2, 'Beer', 4, 4),
(6, 'Brandon Czekalski', 'Heavy improvments', 'This years installment of Paper Birds is much better than last years', 4, 'Beer', 4, 6),
(7, 'Brandon Czekalski', 'Top tier beer', 'This Tropical Seltzer is the greatest beer ever', 5, 'Beer', 4, 8),
(8, 'Celeste', 'I have no taste buds', 'This IPA could use some work', 1, 'Beer', 5, 9);

INSERT INTO brewery_reviews (review_id, brewery_id)
VALUES (1, 1), (2, 2), (3, 3), (4, 3);

INSERT INTO beer_reviews (review_id, beer_id)
VALUES (5, 1), (6, 6), (7, 8), (8, 9);