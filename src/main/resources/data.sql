INSERT INTO user_roles (user_role)
VALUES  ('buyer'),
('seller'),
('admin');

INSERT INTO addresses (id, address_line_one, address_line_two, city, state, country, zip, solar_system, planet)
VALUES (1, '9194 North College Ave', '512 South Pennsylvania St', 'Grand Haven', 'MI', 'United States', '49417', 'solar system', 'earth'),
(2, '14 East Jefferson Court', '', 'Fairhope', 'AL', 'United States', '36532', 'solar system', 'earth'),
(3, '605 Hawthorne Ave', '', 'Clinton', 'MD', 'United States', '20735', 'solar system', 'earth');

--REGISTER TEAM: fix and uncomment Many to one columns to Address table, then edit this insert
INSERT INTO users (user_username, user_password, user_email, user_first_name, user_last_name, role_user_id, primary_address_id, primary_billing_address_id, profile_image)
VALUES ('buyer1', 'password', 'buyer@email.com', 'John', 'Doe', 1, 1, 1, 'www.profileimage.com/1.jpg'),
('buyer2', 'password', 'tenzin@email.com', 'Tenzin', 'Chandrakant', 1, 2, 2, 'www.profileimage.com/2.jpg'),
('seller1', 'password', 'seller@email.com', 'Jane', 'Doe', 2, 1, 1, 'www.profileimage.com/2.jpg'),
('admin1', 'password', 'admin@email.com', 'Aruna', 'Pero', 3, 1, 1, 'www.profileimage.com/3.jpg');

INSERT INTO categories (category_name)
VALUES ('planets'),
('vehicles'),
('miscellaneous');

INSERT INTO billing_details (billing_card_type, billing_card_number, billing_security_number, billing_name, billing_address)
VALUES ('Visa', 1234456778971547, 142, 'John', 1),
('MasterCard', 9874159823651478, 951, 'Tenzin', 2);

INSERT INTO payments (billing_details, payment_status)
VALUES (1, 'Pending'),
(2, 'Approved');

INSERT INTO products (product_name, product_description, product_cost, categories_category_id, product_image, product_seller_id_user_id)
VALUES ('reach', 'A wonderful planet', 150, 1, 'www.image.com/reach.jpg', 3),
('eridanus II', 'A wonderful planet', 100, 1, 'www.image.com/eridanusII.jpg', 3),
('Pilar of autumn', 'A powerful rocket', 50, 2, 'www.image.com/pillar.jpg', 3);

INSERT INTO orders (buyer_id, order_date, order_status, shipping_address_id, payment_id)
VALUES (1, '2022-04-20 08:26:24.166', 'Pending', 1, 1),
(2, '2022-04-20 08:30:24.166', 'Approved', 2, 2);

INSERT INTO orders_items (order_id, items_product_id)
VALUES (1, 1),
(1, 2),
(2, 2),
(2, 3);
