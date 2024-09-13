CREATE TABLE ITEM (
    item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(19, 2) NOT NULL
);

CREATE TABLE CART (
    cart_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL
);

CREATE TABLE CART_ITEM (
    cart_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(19, 2) NULL,
    cart_id BIGINT NOT NULL,

    CONSTRAINT fk_item
        FOREIGN KEY (item_id)
        REFERENCES ITEM(item_id),
    CONSTRAINT fk_cart
        FOREIGN KEY (cart_id)
        REFERENCES CART(cart_id)
);



