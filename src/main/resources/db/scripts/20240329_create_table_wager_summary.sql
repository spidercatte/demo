DROP TABLE IF EXISTS wager_summary;
CREATE TABLE wager_summary (
    account_id BINARY(16) PRIMARY KEY,
    total_wager_amount DECIMAL(19, 4),
    wager_date DATE
);
