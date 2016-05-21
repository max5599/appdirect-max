CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NULL,
  last_name VARCHAR(255) NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX email_unique (email ASC));
