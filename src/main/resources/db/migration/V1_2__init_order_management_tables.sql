CREATE TABLE client_order
(
    id SERIAL NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE order_position
(
    id SERIAL NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE menu_position_code
(
    id SERIAL NOT NULL,
    PRIMARY KEY (id)
);