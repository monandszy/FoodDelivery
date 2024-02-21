CREATE TABLE seller_code
(
    id SERIAL NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE restaurant
(
    id SERIAL NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE menu
(
    id SERIAL NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE menu_position
(
    id SERIAL NOT NULL,
    PRIMARY KEY (id)
);