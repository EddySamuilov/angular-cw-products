INSERT INTO users (id, username, password, first_name, last_name, email, image_url, created, modified)
VALUES
(1, 'johndoe', '1234', 'John', 'Doe', 'john.doe@example.com', '', NOW(), NOW()),
(2, 'janedoe', '1234', 'Jane', 'Doe', 'jane.doe@example.com', '', NOW(), NOW()),
(3, 'admin', 'admin', 'Admin', 'User', 'admin.user@example.com', NULL, NOW(), NOW());


INSERT INTO categories (id, type, description, image_url, created, modified)
VALUES
(1, 'ELECTRONICS', 'Electronic gadgets and devices', 'https://vavelectronics.com/wp-content/uploads/2024/01/electroniks.jpeg', NOW(), NOW()),
(2, 'BOOKS', 'Books and educational materials', 'https://www.publishcentral.com.au/wp-content/uploads/2023/05/book-pile-of-must-read-books-scaled1.jpeg', NOW(), NOW()),
(3, 'CLOTHES', 'Apparel and accessories', 'https://media.burford.co.uk/images/SNY04089.jpg_edit.width-640_ln7jm6QxYVkHFHaT.jpg', NOW(), NOW()),
(4, 'FURNITURE', 'Home and office furniture', 'https://mojoboutique.com/cdn/shop/articles/organic_modern_furniture_1344x.jpg?v=1704830548', NOW(), NOW()),
(5, 'OTHER', 'All the other stuff', 'https://upload.wikimedia.org/wikipedia/commons/2/20/Resin-identification-code-7-OTHER.svg', NOW(), NOW());


INSERT INTO products (id, title, description, image_url, price, category_id, user_id, created, modified)
VALUES
(1, 'Smartphone', 'A high-end smartphone with 128GB storage.', 'https://i.guim.co.uk/img/media/6ead18d1b23b6cdaa33f6731c0c417a8f0576552/539_452_3726_2236/master/3726.jpg?width=445&dpr=1&s=none&crop=none', 699.99, 1, 1, NOW(), NOW()),
(2, 'Laptop', 'A powerful laptop for gaming and work.', 'https://cdncloudcart.com/402/products/images/152961/laptop-lenovo-ideapad-slim-3-15abr8-82xm0015bm--15-60--amd-ryzen-7-7730u-octa-core--512gb-ssd--16--amd-radeo-graphics--bez-os-6644a6938fa6c_800x800.jpeg?1715775152', 1299.99, 1, 2, NOW(), NOW()),
(3, 'Cookbook', 'A collection of healthy recipes.', 'https://m.media-amazon.com/images/I/91W78VErPbL._AC_UF1000,1000_QL80_.jpg', 29.99, 2, 1, NOW(), NOW()),
(4, 'T-shirt', 'Comfortable cotton t-shirt.', 'https://isto.pt/cdn/shop/files/Heavyweight_Black_ef459afb-ff7a-4f9a-b278-9e9621335444.webp?v=1710414950', 19.99, 3, 2, NOW(), NOW()),
(5, 'Gold', '99.99999% pure gold.', 'https://www.investopedia.com/thmb/ThvG00OiG46V5Savq7aByMUMigw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/HowtoBuyGoldBarsGettyImages-1689908174-dd1c5db2500f4b62a6620a1749beee7d.jpg', 188.13, 5, 1, NOW(), NOW()),
(6, 'Dining Table', 'Wooden dining table for six people.', 'https://cdn02.plentymarkets.com/vji7b8phcm0f/item/images/120947/full/Casa-Padrino-Landhausstil-Esstisch-Braun-330-x-110-x-H--77-cm-Rustikaler-Massivholz-Kuechentisch-Rustikale-Massivholz-Esszimmer-Moebel-Landhausstil-Esszimmer-Moebel-120947_5.JPG', 499.99, 4, 1, NOW(), NOW());
