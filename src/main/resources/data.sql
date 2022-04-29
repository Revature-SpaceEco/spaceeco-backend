INSERT INTO user_roles (user_role)
VALUES  ('ROLE_BUYER'),
('ROLE_SELLER'),
('ROLE_ADMIN');

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

INSERT INTO users (user_username, user_password, user_email, user_first_name, user_last_name, role_user_id, primary_address_id, primary_billing_id, profile_image, is_user_active)
VALUES ('buyer1', '{bcrypt}$2a$10$7BPBYvGVY/IOKqX.jvNNuOn7gXyQXDNsDf.IV/YrRwEpRibNIMfWm', 'buyer@email.com', 'John', 'Doe', 1, 1, 1, 'www.profileimage.com/1.jpg', true),
('buyer2', '{bcrypt}$2a$10$7BPBYvGVY/IOKqX.jvNNuOn7gXyQXDNsDf.IV/YrRwEpRibNIMfWm', 'tenzin@email.com', 'Tenzin', 'Chandrakant', 1, 2, 2, 'www.profileimage.com/2.jpg', true),
('seller1', '{bcrypt}$2a$10$7BPBYvGVY/IOKqX.jvNNuOn7gXyQXDNsDf.IV/YrRwEpRibNIMfWm', 'seller@email.com', 'Jane', 'Doe', 2, 3, 3, 'www.profileimage.com/2.jpg', true),
('admin1', '{bcrypt}$2a$10$7BPBYvGVY/IOKqX.jvNNuOn7gXyQXDNsDf.IV/YrRwEpRibNIMfWm', 'admin@email.com', 'Aruna', 'Pero', 3, 4, 4, 'www.profileimage.com/3.jpg', true);

INSERT INTO payments (billing_details)
VALUES (1),
(2);

INSERT INTO products (product_name, product_description, product_cost, categories_id, product_image, seller_user_id)
VALUES ('reach', 'A wonderful planet', 150, 1, 'https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg', 3),
('eridanus II', 'A wonderful planet', 100, 1, 'www.image.com/eridanusII.jpg', 3),
('Pilar of autumn', 'A powerful rocket', 50, 2, 'www.image.com/pillar.jpg', 3);

INSERT INTO orders (buyer, order_date, order_status, shipping_address_id, payment_id)
VALUES (1, '2022-04-20 08:26:24.166', 'Pending', 1, 1),
(2, '2022-04-20 08:30:24.166', 'Approved', 2, 2);

INSERT INTO orders_items (order_id, items_id)
VALUES (1, 1),
(1, 2),
(2, 2),
(2, 3);