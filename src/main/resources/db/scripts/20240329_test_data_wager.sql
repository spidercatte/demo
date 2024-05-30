INSERT INTO wager (account_id, wager_amount, wager_time) VALUES
(UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), 100.50, NOW()),
(UNHEX(REPLACE('6ba7b810-9dad-11d1-80b4-00c04fd430c8', '-', '')), 200.75, NOW()),
(UNHEX(REPLACE('7c7e61ff-988c-4d92-8c20-2bb8a2ac134b', '-', '')), 300.25, NOW());

INSERT INTO wager (account_id, wager_amount, wager_time) VALUES
(UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), 100.50, DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(UNHEX(REPLACE('6ba7b810-9dad-11d1-80b4-00c04fd430c8', '-', '')), 200.75, DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(UNHEX(REPLACE('7c7e61ff-988c-4d92-8c20-2bb8a2ac134b', '-', '')), 300.25, DATE_SUB(NOW(), INTERVAL 1 HOUR));

INSERT INTO wager (account_id, wager_amount, wager_time) VALUES
(UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), 100.50, DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(UNHEX(REPLACE('6ba7b810-9dad-11d1-80b4-00c04fd430c8', '-', '')), 200.75, DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(UNHEX(REPLACE('7c7e61ff-988c-4d92-8c20-2bb8a2ac134b', '-', '')), 300.25, DATE_SUB(NOW(), INTERVAL 2 HOUR));


