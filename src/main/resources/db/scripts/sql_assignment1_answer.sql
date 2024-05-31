WITH CTE_SETTLEMENT_PROPORTION AS
    (
        SELECT
            Stake.BET_ID,
            Stake.ACCOUNT_ID,
            Stake.TRANSACTION_TYPE,
            Stake.AMOUNT,
            Stake.FUND_TYPE,
            Settlement.AMOUNT SettlementAmount,
            Stake.Amount / Settlement.AMOUNT Ratio,

            Settlement.Amount / SUM(Stake.Amount) OVER(PARTITION BY Stake.BET_ID, Stake.ACCOUNT_ID) Multiplier

    FROM
   (
    SELECT BET_ID, ACCOUNT_ID, TRANSACTION_TYPE, AMOUNT, FUND_TYPE FROM StakeSettlement WHERE TRANSACTION_TYPE = 'STAKE'
    ) Stake
    LEFT JOIN
   (
    SELECT BET_ID, AMOUNT FROM StakeSettlement WHERE TRANSACTION_TYPE = 'SETTLEMENT'
    ) Settlement
ON Stake.BET_ID = Settlement.BET_ID

)

SELECT
    BET_ID,
    ACCOUNT_ID,
    SUM(CASE WHEN FUND_TYPE = 'CASH' THEN (SettlementAmount * Ratio * Multiplier) ELSE 0 END) CASH_SETTLEMENT,
    SUM(CASE WHEN FUND_TYPE = 'BONUS' THEN (SettlementAmount * Ratio * Multiplier) ELSE 0 END) BONUS_SETTLEMENT
FROM CTE_SETTLEMENT_PROPORTION
GROUP BY BET_ID, ACCOUNT_ID