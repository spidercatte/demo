-- Create SETTLEMENT table

CREATE TABLE StakeSettlement (
                            ID INT PRIMARY KEY,
                            BET_ID INT,
                            ACCOUNT_ID INT,
                            TRANSACTION_TYPE VARCHAR(50),
                            AMOUNT DECIMAL(10, 2),
                            FUND_TYPE VARCHAR(50)
);

-- Insert data into SETTLEMENT table

INSERT INTO StakeSettlement (ID, BET_ID, ACCOUNT_ID, TRANSACTION_TYPE, AMOUNT, FUND_TYPE)
VALUES
    (1, 1, 1, 'STAKE', 5, 'CASH'),
    (2, 1, 1, 'STAKE', 5, 'BONUS'),
    (3, 1, 1, 'SETTLEMENT', 20, NULL),
    (4, 2, 1, 'STAKE', 6, 'CASH'),
    (5, 2, 1, 'STAKE', 4, 'BONUS'),
    (6, 2, 1, 'SETTLEMENT', 10, NULL),
    (7, 3, 2, 'STAKE', 10, 'CASH'),
    (8, 3, 2, 'SETTLEMENT', 10, NULL),
    (9, 4, 2, 'STAKE', 5, 'BONUS'),
    (10, 4, 2, 'SETTLEMENT', 10, NULL)
