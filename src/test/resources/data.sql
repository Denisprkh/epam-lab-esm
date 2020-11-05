INSERT INTO tag(name) VALUES ('sport'), ('food'), ('relax');

INSERT INTO gift_certificate(name, description, price, create_date, last_update_date, duration)
VALUES ('Gym', 'Simple description', 10.00, '2020-11-04 22:18:24', '2020-11-04 22:18:24', 30),
       ('Provenance', 'Just try it', 30.00, '2020-11-04 22:18:24', '2020-11-04 22:18:24' , 30),
       ('Robinson spa', 'Luxury relax club', 70, '2020-10-25 22:18:24', '2020-11-04 22:18:24', 30);

INSERT INTO gift_certificates_tag(tag_id,gift_certificate_id)
VALUES (2, 2),
       (3, 2),
       (3, 3),
       (1, 1);