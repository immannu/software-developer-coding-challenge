CREATE TABLE account (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  type enum('DEALER','INDIVIDUAL','NONE') NOT NULL DEFAULT 'NONE',
  email varchar(127) CHARACTER SET latin1 DEFAULT NULL,
  phone_number varchar(21) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  email_verified tinyint(1) NOT NULL DEFAULT 0,
  deleted tinyint(1) NOT NULL DEFAULT 0,
  created_time timestamp NULL DEFAULT NULL,
  modified_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY email (email),
  KEY name (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE address (
  id int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_id int(11) NOT NULL,
  address_1 varchar(255) DEFAULT NULL,
  address_2 varchar(255) DEFAULT NULL,
  address_3 varchar(255) DEFAULT NULL,
  city varchar(127) DEFAULT NULL,
  state varchar(127) DEFAULT NULL,
  zip varchar(127) DEFAULT NULL,
  country varchar(127) DEFAULT NULL,
  created_time timestamp NULL DEFAULT NULL,
  modified_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY account_id (account_id),
  CONSTRAINT account_id FOREIGN KEY (account_id) REFERENCES account (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE vehicle (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  year varchar(5)  NOT NULL,
  make varchar(20) DEFAULT NULL,
  model varchar(20) DEFAULT NULL,
  trim varchar(20) DEFAULT NULL,
  vin varchar(127) DEFAULT NULL,
  vehicle_value int(7) DEFAULT 0,
  created_time timestamp NULL DEFAULT NULL,
  modified_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY vin (vin)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE vehicle_account (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_id int(11) NOT NULL,
  vehicle_id int(11) NOT NULL,
  created_time timestamp NULL DEFAULT NULL,
  modified_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT account_id_vehicle_account FOREIGN KEY (account_id) REFERENCES account (id),
  CONSTRAINT vehicle_id_vehicle_account FOREIGN KEY (vehicle_id) REFERENCES vehicle (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE vehicle_auction (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_id int(11) NOT NULL,
  vehicle_id int(11) NOT NULL,
  base_price int(11) NOT NULL,
  final_price int(11),
  status enum('IN_PROGRESS','CANCELLED','COMPLETED','NOT_STARTED') NOT NULL DEFAULT 'NOT_STARTED',
  auction_duration_hr int(4) NOT NULL,
  auction_start_time timestamp NULL DEFAULT NULL,
  auction_end_time timestamp NULL DEFAULT NULL,
  created_time timestamp NULL DEFAULT NULL,
  modified_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT account_id_auction FOREIGN KEY (account_id) REFERENCES account (id),
  CONSTRAINT vehicle_id_auction FOREIGN KEY (vehicle_id) REFERENCES vehicle (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE vehicle_auction_history (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_id int(11) NOT NULL,
  vehicle_id int(11) NOT NULL,
  vehicle_auction_id int(11) NOT NULL,
  vin varchar(127) DEFAULT NULL,
  base_price int(11) NOT NULL,
  auction_price int(11),
  created_time timestamp NULL DEFAULT NULL,
  modified_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT account_id_history FOREIGN KEY (account_id) REFERENCES account (id),
  CONSTRAINT vehicle_id_history FOREIGN KEY (vehicle_id) REFERENCES vehicle (id),
  CONSTRAINT vehicle_auction_id_history FOREIGN KEY (vehicle_auction_id) REFERENCES vehicle_auction (id),
  CONSTRAINT vehicle_auction_id_history_vin FOREIGN KEY (vin) REFERENCES vehicle (vin),
  KEY vin (vin)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



