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

INSERT INTO users (user_username, user_password, user_email, user_first_name, user_last_name, role_user_id, primary_address_id, primary_billing_id, profile_image, is_user_active, user_secret)
VALUES
('buyer1', '$2a$10$Z.LeqmZEqwoBKnjWWXA04.oYta1kpqvzPSOJsv1bdDWenG3yfPrrK', 'buyer@email.com', 'John', 'Doe', 1, 1, 1, 'www.profileimage.com/1.jpg', true, '4DFRBLQLAJ7YMYKQSVDL3MCA3E36ZXNY7VHKMWTMMKP4RDPXFJ7DA57W3JXPHLJOVVTG7PP5XVUXF557ULSMHHDS6IY23ZYCVOPIVNASEFRC4TOXLJRW4P3I635PNV5P'),
('buyer2', '$2a$10$Z.LeqmZEqwoBKnjWWXA04.oYta1kpqvzPSOJsv1bdDWenG3yfPrrK', 'tenzin@email.com', 'Tenzin', 'Chandrakant', 1, 2, 2, 'www.profileimage.com/2.jpg', true, '4DFRBLQLAJ7YMYKQSVDL3MCA3E36ZXNY7VHKMWTMMKP4RDPXFJ7DA57W3JXPHLJOVVTG7PP5XVUXF557ULSMHHDS6IY23ZYCVOPIVNASEFRC4TOXLJRW4P3I635PNV5P'),
('seller1', '$2a$10$Z.LeqmZEqwoBKnjWWXA04.oYta1kpqvzPSOJsv1bdDWenG3yfPrrK', 'seller@email.com', 'Jane', 'Doe', 2, 3, 3, 'www.profileimage.com/2.jpg', true, '4DFRBLQLAJ7YMYKQSVDL3MCA3E36ZXNY7VHKMWTMMKP4RDPXFJ7DA57W3JXPHLJOVVTG7PP5XVUXF557ULSMHHDS6IY23ZYCVOPIVNASEFRC4TOXLJRW4P3I635PNV5P'),
('admin1', '$2a$10$Z.LeqmZEqwoBKnjWWXA04.oYta1kpqvzPSOJsv1bdDWenG3yfPrrK', 'admin@email.com', 'Aruna', 'Pero', 3, 4, 4, 'www.profileimage.com/3.jpg', true, '4DFRBLQLAJ7YMYKQSVDL3MCA3E36ZXNY7VHKMWTMMKP4RDPXFJ7DA57W3JXPHLJOVVTG7PP5XVUXF557ULSMHHDS6IY23ZYCVOPIVNASEFRC4TOXLJRW4P3I635PNV5P');

INSERT INTO payments (billing_details)
VALUES (1),
(2);

INSERT INTO products (product_name, product_description, product_cost, categories_id, product_image, seller_user_id)
VALUES ('Reach', 'A wonderful planet', 150, 1, 'https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg', 3),
('Eridanus II', 'A wonderful planet', 100, 1, 'https://static.wikia.nocookie.net/halo/images/0/0d/HWF_Eridanus_II.png', 3),
('Pillar of Autumn', 'A powerful rocket', 50, 2, 'https://static.wikia.nocookie.net/halo/images/d/d0/PoA.jpg', 3);

INSERT INTO orders (buyer, order_date, order_status, shipping_address_id, payment_id)
VALUES (1, '2022-04-20 08:26:24.166', 'Pending', 1, 1),
(2, '2022-04-20 08:30:24.166', 'Approved', 2, 2);

INSERT INTO orders_items (order_id, items_id)
VALUES (1, 1),
(1, 2),
(2, 2),
(2, 3);
