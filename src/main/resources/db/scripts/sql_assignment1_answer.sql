WITH Stake AS (
    SELECT BET_ID, ACCOUNT_ID, TRANSACTION_TYPE, AMOUNT, FUND_TYPE
    FROM StakeSettlement
    WHERE TRANSACTION_TYPE = 'STAKE'
),
     Settlement AS (
         SELECT BET_ID, AMOUNT
         FROM StakeSettlement
         WHERE TRANSACTION_TYPE = 'SETTLEMENT'
     ),
     TotalStakes AS (
         SELECT BET_ID, ACCOUNT_ID, SUM(AMOUNT) AS TotalAmount
         FROM Stake
         GROUP BY BET_ID, ACCOUNT_ID
     ),
     CTE_SETTLEMENT_PROPORTION AS (
         SELECT
             Stake.BET_ID,
             Stake.ACCOUNT_ID,
             Stake.TRANSACTION_TYPE,
             Stake.AMOUNT,
             Stake.FUND_TYPE,
             Settlement.AMOUNT AS SettlementAmount,
             Stake.Amount / Settlement.AMOUNT AS Ratio,
             Settlement.Amount / TotalStakes.TotalAmount AS Multiplier
         FROM
             Stake
                 LEFT JOIN
             Settlement ON Stake.BET_ID = Settlement.BET_ID
                 LEFT JOIN
             TotalStakes ON Stake.BET_ID = TotalStakes.BET_ID AND Stake.ACCOUNT_ID = TotalStakes.ACCOUNT_ID
     )
SELECT
    BET_ID,
    ACCOUNT_ID,
    SUM(CASE WHEN FUND_TYPE = 'CASH' THEN (SettlementAmount * Ratio * Multiplier) ELSE 0 END) AS CASH_SETTLEMENT,
    SUM(CASE WHEN FUND_TYPE = 'BONUS' THEN (SettlementAmount * Ratio * Multiplier) ELSE 0 END) AS BONUS_SETTLEMENT
FROM CTE_SETTLEMENT_PROPORTION
GROUP BY BET_ID, ACCOUNT_ID;
