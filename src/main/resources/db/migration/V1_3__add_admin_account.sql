INSERT INTO food_delivery.account
VALUES (1, 'admin', '$2a$10$0OJdEJnFh4NhQk74Vuzr8.eHT3GqrjbR10gSlsesSylcJ3UWbKbRa', TRUE); -- admin admin

INSERT INTO food_delivery.role
VALUES (1, 'ACCOUNT'),
       (2, 'SELLER'),
       (3, 'CLIENT'),
       (4, 'ADMIN');
INSERT INTO food_delivery.account_role
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4)