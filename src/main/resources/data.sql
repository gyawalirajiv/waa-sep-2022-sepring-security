INSERT INTO users (id, email, firstname, lastname, password)
VALUES (3, 'uinan@miu.edu', 'umur', 'inan', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123
INSERT INTO users (id, email, firstname, lastname, password)
VALUES (4, 'john@miu.edu', 'john', 'doe', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123

INSERT INTO role (id, role)
VALUES (1, 'ADMIN');
INSERT INTO role (id, role)
VALUES (2, 'GOLD');

INSERT INTO users_roles (user_id, roles_id)
VALUES (3, 1);
INSERT INTO users_roles (user_id, roles_id)
VALUES (4, 2);

INSERT INTO product (id, name, price, id_user)
VALUES (3, 'iPhone', 1200, 3);
INSERT INTO product (id, name, price, id_user)
VALUES (4, 'iPad', 900, 3);
INSERT INTO product (id, name, price, id_user)
VALUES (5, 'Pen', 5, 3);