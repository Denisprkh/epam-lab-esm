CREATE TABLE tag (
  tag_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (tag_id)
 );

CREATE TABLE gift_certificate (
  gift_certificate_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  price DECIMAL(5,2) NOT NULL,
  create_date TIMESTAMP NOT NULL,
  last_update_date TIMESTAMP NOT NULL,
  duration INT NOT NULL,
  description TEXT NOT NULL,
  PRIMARY KEY (gift_certificate_id)
  );


CREATE TABLE gift_certificates_tag (
  tag_id INT NOT NULL,
  gift_certificate_id INT NOT NULL,
  PRIMARY KEY (tag_id, gift_certificate_id),
    FOREIGN KEY (tag_id) REFERENCES tag (tag_id) ON DELETE CASCADE ,
    FOREIGN KEY (gift_certificate_id) REFERENCES gift_certificate (gift_certificate_id) ON DELETE CASCADE);