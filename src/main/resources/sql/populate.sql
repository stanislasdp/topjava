INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
SELECT 'ROLE_ADMIN', id
FROM users where name = 'Admin';

INSERT INTO user_roles (role, user_id)
SELECT 'ROLE_USER', id
FROM users where name = 'User';

INSERT INTO meals (date_time, description, calories, user_id) VALUES
('2018-03-03 23:59', 'Meal 1', 100, 1),
('2018-03-04 23:59', 'Meal 2', 200, 1),
('2018-03-05 02:00', 'Meal 3', 300, 1),
('2018-03-06 02:00', 'Meal 4', 100, 1),
('2018-02-05 23:59', 'Meal 5', 150, 2),
('2018-07-06 23:59', 'Meal 6', 100, 2),
('2018-07-07 23:59', 'Meal 7', 150, 2),
('2018-08-08 02:00', 'Meal 8', 120, 2);