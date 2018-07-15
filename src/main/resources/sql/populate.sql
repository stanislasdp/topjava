INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
SELECT 'ROLE_ADMIN', id
FROM users where name = 'Admin';

INSERT INTO user_roles (role, user_id)
SELECT 'ROLE_USER', id
FROM users where name = 'User';