DROP TABLE IF EXISTS wager;
CREATE TABLE wager (
    account_id BINARY(16),
    wager_amount DECIMAL(19, 4),
    wager_time TIMESTAMP
);
