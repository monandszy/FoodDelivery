CREATE TABLE food_delivery.address
(
    id SERIAL NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE food_delivery.restaurant
(
    id             SERIAL     NOT NULL,
    delivery_range NUMERIC(7) NOT NULL,
    seller_id      INT        NOT NULL,
    address_id     INT        ,
    PRIMARY KEY (id),
    CONSTRAINT fk_restaurant_seller
        FOREIGN KEY (seller_id) REFERENCES food_delivery.account (id),
    CONSTRAINT fk_restaurant_address
        FOREIGN KEY (address_id) REFERENCES food_delivery.address (id)
);

CREATE TABLE food_delivery.menu
(
    id            SERIAL NOT NULL,
    restaurant_id INT    NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_menu_restaurant
        FOREIGN KEY (restaurant_id) REFERENCES food_delivery.restaurant (id)
);

CREATE TABLE food_delivery.menu_position
(
    id      SERIAL        NOT NULL,
    menu_id INT           NOT NULL,
    price   NUMERIC(7, 2) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_position_menu
        FOREIGN KEY (menu_id) REFERENCES food_delivery.menu (id)
);