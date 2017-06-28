CREATE TABLE IF NOT EXISTS Movie (
  movie_id bigint(20) NOT NULL AUTO_INCREMENT,
  moviename varchar(50) CHARACTER SET utf8 NOT NULL,
  director varchar(50) CHARACTER SET utf8,
  actors varchar(200) CHARACTER SET utf8,
  PRIMARY KEY (movie_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
