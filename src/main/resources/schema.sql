CREATE TABLE user (
  id INTEGER IDENTITY PRIMARY KEY,
  email VARCHAR(50) NOT NULL,
  first_name VARCHAR(30) NULL,
  last_name VARCHAR(30) NULL,
  UNIQUE INDEX email_unique (email ASC));