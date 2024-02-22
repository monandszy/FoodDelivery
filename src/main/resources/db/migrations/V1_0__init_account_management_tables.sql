CREATE TABLE account_details
(
    id SERIAL NOT NULL,
    user_name VARCHAR(32) NOT NULL,
    password VARCHAR(256) NOT NULL,
    active BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE account_role (
    account_id INT NOT NULL,
    role_id INT NOT NULL
);

CREATE TABLE role
(
    id SERIAL NOT NULL,
    role VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);