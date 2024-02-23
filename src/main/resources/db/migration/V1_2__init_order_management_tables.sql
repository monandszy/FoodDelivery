CREATE TABLE food_delivery.client_order
(
    id     SERIAL      NOT NULL,
    status VARCHAR(20) NOT NULL,
    time_of_order timestamptz NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT status_in CHECK (
        client_order.status IN
        (
         'IN_PROGRESS',
         'TRAVELLING',
         'COMPLETED'
            ))
);

CREATE TABLE food_delivery.order_position
(
    order_id         INT NOT NULL,
    menu_position_id INT NOT NULL,
    CONSTRAINT fk_order_menu
        FOREIGN KEY (menu_position_id)
            REFERENCES food_delivery.menu_position (id),
    CONSTRAINT fk_position_order
        FOREIGN KEY (order_id)
            REFERENCES food_delivery.client_order (id)
);