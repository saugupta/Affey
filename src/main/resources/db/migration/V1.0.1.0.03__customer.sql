CREATE TABLE IF NOT EXISTS User (
  user_name varchar(50) CHARACTER SET utf8 NOT NULL,
  password varchar(100) CHARACTER SET utf8 NOT NULL,
  emailid varchar(50)  CHARACTER SET utf8 NOT NULL,
  enabled boolean NOT NULL,
  PRIMARY KEY (user_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT IGNORE INTO User VALUES("admin", '\$2a\$08\$D69Vub3Sc0Ewi5Ku23d9fuFRx/96dQ6shOaGKgj1KLHIp68BGxhI2',"admin@admin.com", true);
INSERT IGNORE INTO User VALUES("saurabh", '\$2a\$08\$D69Vub3Sc0Ewi5Ku23d9fuFRx/96dQ6shOaGKgj1KLHIp68BGxhI2',"saugupta@adobe.com",true);

CREATE TABLE IF NOT EXISTS role (
  role_name varchar(100) NOT NULL,
  description longtext,
  PRIMARY KEY (role_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

INSERT IGNORE INTO role VALUES("ADMIN", "Administrator role");
INSERT IGNORE INTO role VALUES("CUSTOMER", "Normal user");

CREATE TABLE IF NOT EXISTS user_role (
  row_id bigint(20) NOT NULL AUTO_INCREMENT,
  user_name varchar(100) NOT NULL,
  role_name varchar(100) NOT NULL,
  PRIMARY KEY (row_id),
  UNIQUE (user_name, role_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

INSERT IGNORE INTO user_role (user_name,role_name) VALUES("admin", "ADMIN");
INSERT IGNORE INTO user_role (user_name,role_name) VALUES("saurabh", "CUSTOMER");


CREATE TABLE IF NOT EXISTS role_permission (
  role_name varchar(100) NOT NULL,
  permission_name varchar(100) NOT NULL,
  PRIMARY KEY (role_name, permission_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

INSERT IGNORE INTO role_permission VALUES("ADMIN", "ALL_PERMISSIONS");
INSERT IGNORE INTO role_permission VALUES("ADMIN", "ADMIN_API_CALL");
INSERT IGNORE INTO role_permission VALUES("ADMIN", "CUSTOMER_API_CALL");
INSERT IGNORE INTO role_permission VALUES("CUSTOMER", "CUSTOMER_API_CALL");