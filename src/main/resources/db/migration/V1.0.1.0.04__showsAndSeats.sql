CREATE TABLE IF NOT EXISTS Shows (
  show_id bigint(20) NOT NULL AUTO_INCREMENT,
  price bigint(20) NOT NULL,
  starttime datetime NOT NULL,
  endtime datetime NOT NULL,
  theatre_id bigint(20),
  movie_id bigint(20),
  PRIMARY KEY (show_id),
  FOREIGN KEY (movie_id) REFERENCES Movie(movie_id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (theatre_id) REFERENCES Theatre(theatre_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Seat (
  seat_id bigint(20) NOT NULL AUTO_INCREMENT,
  show_id bigint(20)NOT NULL,
  user_name varchar(50) CHARACTER SET utf8,
  reserved boolean DEFAULT false,
  PRIMARY KEY (seat_id),
  FOREIGN KEY (show_id) REFERENCES Shows(show_id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (user_name) REFERENCES User(user_name) ON DELETE SET NULL ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;