USE topjava;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;


CREATE TABLE users
(
 id INT NOT NULL AUTO_INCREMENT,
 name VARCHAR(255) NOT NULL,
 email VARCHAR(255) NOT NULL,
 password VARCHAR(255) NOT NULL,
 registered TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 enabled TINYINT(1) NOT NULL DEFAULT 1,
 calories_per_day INT(11) NOT NULL DEFAULT 2000,
 PRIMARY KEY (id)
 );

CREATE TABLE user_roles
(
 user_id INT NOT NULL,
 role    VARCHAR(255),
 CONSTRAINT FK_ID_User_id FOREIGN KEY (user_id) REFERENCES users(id)
)
