INSERT INTO food_delivery.account
VALUES (1, 'admin', '$2a$10$0OJdEJnFh4NhQk74Vuzr8.eHT3GqrjbR10gSlsesSylcJ3UWbKbRa', TRUE), -- admin admin
       (2, 'anonymousUser', '$2a$12$MfGyeULE5.TUU/L5BQ9MHeexlwSbHr1ze3gX/0eCSyrW8bOzHqEwy', TRUE),  -- anonymousUser anonymousUser
       (3, 'test', 'test', TRUE)
;



INSERT INTO food_delivery.role
VALUES (1, 'ACCOUNT'),
       (2, 'SELLER'),
       (3, 'CLIENT'),
       (4, 'ADMIN');
INSERT INTO food_delivery.account_role
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4)