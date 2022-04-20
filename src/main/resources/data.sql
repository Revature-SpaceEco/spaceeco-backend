INSERT INTO user_roles(user_role_id,user_role)VALUES(1,'ADMIN');
INSERT INTO categories(category_id,category_name) VALUES(1,'ELECTRONICS');

INSERT INTO users (user_id,user_email,user_first_name,profile_image,user_last_name,user_password,user_username,role_user_id)
VALUES(1,'jeno@.com','jneob','image.src','jo','password','jenob',1);






INSERT into products ( product_id,product_cost,product_description,product_image,product_name,categories_category_id,product_seller_id_user_id )
VALUES(1,100,'product description','image.jpg','product',1, 1);
