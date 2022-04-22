INSERT INTO user_roles (user_role)
VALUES  ('buyer'),
('seller'),
('admin');

INSERT INTO categories (category_name)
VALUES ('planets'),
('vehicles'),
('miscellaneous');

INSERT INTO addresses (address_line_one, address_line_two, city, state, country, zip, solar_system, planet)
VALUES ('9194 North College Ave', '512 South Pennsylvania St', 'Grand Haven', 'MI', 'United States', '49417', 'solar system', 'earth'),
('14 East Jefferson Court', '', 'Fairhope', 'AL', 'United States', '36532', 'solar system', 'earth'),
('605 Hawthorne Ave', '', 'Clinton', 'MD', 'United States', '20735', 'solar system', 'earth'),
('584 Hello Ave', '', 'Clinton', 'CA', 'United States', '52347', 'solar system', 'earth');

INSERT INTO billing_details (billing_card_type, billing_card_number, billing_security_number, billing_name, billing_address)
VALUES ('Visa', 1234456778971547, 142, 'John', 1),
('MasterCard', 9874159823651478, 951, 'Tenzin', 2),
('MasterCard', 9874159823651478, 951, 'Jane', 2),
('MasterCard', 9874159823651478, 951, 'Aruna', 2);

INSERT INTO users (user_username, user_password, user_email, user_first_name, user_last_name, role_user_id, primary_address_id, primary_billing_id, profile_image)
VALUES ('buyer1', 'password', 'buyer@email.com', 'John', 'Doe', 1, 1, 1, 'www.profileimage.com/1.jpg'),
('buyer2', 'password', 'tenzin@email.com', 'Tenzin', 'Chandrakant', 1, 2, 2, 'www.profileimage.com/2.jpg'),
('seller1', 'password', 'seller@email.com', 'Jane', 'Doe', 2, 3, 3, 'www.profileimage.com/2.jpg'),
('admin1', 'password', 'admin@email.com', 'Aruna', 'Pero', 3, 4, 4, 'www.profileimage.com/3.jpg');

INSERT INTO payments (billing_details, payment_status)
VALUES (1, 'Pending'),
(2, 'Approved');

INSERT INTO products (product_name, product_description, product_cost, categories_category_id, product_image, product_seller_id_user_id)
VALUES ('reach', 'A wonderful planet', 150, 1, 'www.image.com/reach.jpg', 3),
('eridanus II', 'A wonderful planet', 100, 1, 'www.image.com/eridanusII.jpg', 3),
('Pilar of autumn', 'A powerful rocket', 50, 2, 'www.image.com/pillar.jpg', 3);

INSERT INTO orders (buyer, order_date, order_status, shipping_address_id, payment_id)
VALUES (1, '2022-04-20 08:26:24.166', 'Pending', 1, 1),
(2, '2022-04-20 08:30:24.166', 'Approved', 2, 2);

INSERT INTO orders_items (order_id, items_product_id)
VALUES (1, 1),
(1, 2),
(2, 2),
(2, 3);
--=======
--INSERT INTO user_roles(user_role_id,user_role)VALUES(1,'ADMIN');
--INSERT INTO categories(category_id,category_name) VALUES(1,'ELECTRONICS');
--
--INSERT INTO users (user_id,user_email,user_first_name,profile_image,user_last_name,user_password,user_username,role_user_id)
--VALUES(1,'jeno@.com','jneob','image.src','jo','password','jenob',1);
--
--
--
--
--
--
--INSERT into products ( product_id,product_cost,product_description,product_image,product_name,categories_category_id,product_seller_id_user_id )
--VALUES(1,100,'product description','image.jpg','product',1, 1);
-->>>>>>> 42755edb160d9e1bd1738ae2caaeb966a81520cf
