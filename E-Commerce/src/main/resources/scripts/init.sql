-- Drop tables if they exist (in reverse order of dependencies)
DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;

-- Create categories table
CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Create users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(70) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Create index on users.email
CREATE INDEX idx_users_email ON users(email);

-- Create carts table
CREATE TABLE carts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_carts_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create products table
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    picture_url VARCHAR(255),
    category_id BIGINT NOT NULL,
    size VARCHAR(50),
    color VARCHAR(50),
    price NUMERIC(10,2) NOT NULL,
    CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Create cart_items table
CREATE TABLE cart_items (
    id BIGSERIAL PRIMARY KEY,
    cart_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    CONSTRAINT fk_cart_items_cart FOREIGN KEY (cart_id) REFERENCES carts(id),
    CONSTRAINT fk_cart_items_product FOREIGN KEY (product_id) REFERENCES products(id)
);


-- 1. Insert Categories
INSERT INTO categories (name) VALUES
('Men''s Clothing'),
('Women''s Clothing'),
('Accessories'),
('Footwear'),
('Outerwear');

-- 2. Insert Users
INSERT INTO users (full_name, email, password) VALUES
('John Doe', 'john.doe@example.com', '$2a$10$Yz8tQx2TXMqK4v.TfW6p7eJpUvJ8kaBD1S.AQrMEeJ7omO6ke3hb2'),
('Jane Smith', 'jane.smith@example.com', '$2a$10$QoOd4xzJLPZQxiQG/OhpwOFU1yY0Y4VN3hfveIlCDK4l2zxwrKbx2'),
('Michael Johnson', 'michael.johnson@example.com', '$2a$10$RA/gqyQ2QUuPDXJ9DwMYjeZOZhZIbvhCaAjcnbDVZJlj.S3YM7xLq');


-- 3. Insert Carts for each User
INSERT INTO carts (user_id) VALUES
(1),
(2),
(3);

-- 4. Insert Products (with category_id references)
INSERT INTO products (name, description, picture_url, category_id, size, color, price) VALUES
('Classic Oxford Shirt', 'Premium cotton oxford shirt with button-down collar', 'https://images.unsplash.com/photo-1603252109303-2751441dd157', 1, 'M', 'Blue', 59.99),
('Slim Fit Chinos', 'Comfortable slim fit chinos for everyday wear', 'https://images.unsplash.com/photo-1624378439575-d8705ad7ae80', 1, '32', 'Khaki', 49.99),
('Floral Summer Dress', 'Lightweight floral print dress perfect for summer', 'https://images.unsplash.com/photo-1612336307429-8a898d10e223', 2, 'S', 'Multicolor', 79.99),
('High-Waisted Jeans', 'Stretchy high-waisted jeans with modern fit', 'https://images.unsplash.com/photo-1541099649105-f69ad21f3246', 2, '28', 'Dark Blue', 69.99),
('Leather Messenger Bag', 'Handcrafted leather messenger bag with multiple compartments', 'https://images.unsplash.com/photo-1590874103328-eac38a683ce7', 3, NULL, 'Brown', 129.99),
('Round Sunglasses', 'Vintage-inspired round sunglasses with UV protection', 'https://images.unsplash.com/photo-1511499767150-a48a237f0083', 3, NULL, 'Black', 39.99),
('Chelsea Boots', 'Classic leather Chelsea boots with elastic sides', 'https://images.unsplash.com/photo-1638247025967-b4e38f787b76', 4, '42', 'Black', 159.99),
('Canvas Sneakers', 'Casual low-top canvas sneakers', 'https://images.unsplash.com/photo-1603808033192-082d6919d3e1', 4, '38', 'White', 49.99),
('Wool Peacoat', 'Elegant wool peacoat for winter', 'https://images.unsplash.com/photo-1608063615781-e2ef8c73d114', 5, 'L', 'Navy', 199.99),
('Lightweight Raincoat', 'Waterproof packable raincoat for all seasons', 'https://www.worldwidecyclery.com/cdn/shop/files/ketl-bodbrella-rainshell-blue-titan_e577a7ba-4332-4e88-9ca7-6d5a7562fdc3_1024x.jpg?v=1739477779', 5, 'M', 'Yellow', 89.99),
('Silk Scarf', 'Luxurious patterned silk scarf', 'https://furiousgoose.co.uk/cdn/shop/products/SS40-SelimsHammer-Cannes-2.jpg?v=1530043659&width=1080', 3, NULL, 'Multicolor', 45.99),
('Gold Hoop Earrings', 'Classic gold-plated hoop earrings', 'https://m.media-amazon.com/images/I/51ksCdrNfcL._AC_SL1500_.jpg', 3, NULL, 'Gold', 29.99);

-- 5. Insert CartItems (with cart_id and product_id references)
INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
(1, 1, 1),  -- John's cart: 1 Classic Oxford Shirt
(1, 7, 1),  -- John's cart: 1 Chelsea Boots
(2, 3, 1),  -- Jane's cart: 1 Floral Summer Dress
(2, 6, 1),  -- Jane's cart: 1 Round Sunglasses
(2, 12, 2), -- Jane's cart: 2 Gold Hoop Earrings
(3, 5, 1),  -- Michael's cart: 1 Leather Messenger Bag
(3, 9, 1);  -- Michael's cart: 1 Wool Peacoat