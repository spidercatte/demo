WITH
    CTE_DEPOSIT AS (
        SELECT
            PLAYER_ID,
            CAST(TRANSACTION_DATE AS DATE) AS TRANS_DT,
            SUM(AMOUNT) AS TOTAL_DEPOSIT
        FROM
            DEPOSIT
        WHERE
            STATUS = 'SUCCESS' AND CAST(TRANSACTION_DATE AS DATE) = '2024-01-12'
        GROUP BY
            PLAYER_ID, CAST(TRANSACTION_DATE AS DATE)
    ),
    CTE_WITHDRAWAL AS (
        SELECT
            PLAYER_ID,
            CAST(TRANSACTION_DATE AS DATE) AS TRANS_DT,
            SUM(AMOUNT) AS TOTAL_WITHDRAWAL
        FROM
            WITHDRAWAL
        WHERE
            STATUS = 'SUCCESS' AND CAST(TRANSACTION_DATE AS DATE) = '2024-01-12'
        GROUP BY
            PLAYER_ID, CAST(TRANSACTION_DATE AS DATE)
    ),
    CTE_BET AS (
        SELECT
            PLAYER_ID,
            CAST(TRANSACTION_DATE AS DATE) AS TRANS_DT,
            SUM(BET_AMOUNT) AS TOTAL_BET,
            SUM(WIN_AMOUNT) AS TOTAL_WIN
        FROM
            BETS
        WHERE
            CAST(TRANSACTION_DATE AS DATE) = '2024-01-12'
        GROUP BY
            PLAYER_ID, CAST(TRANSACTION_DATE AS DATE)
    )

SELECT
    P.ID AS PLAYER_ID,
    P.First_Name,
    P.Last_Name,
    COALESCE(D.TOTAL_DEPOSIT, 0) AS TOTAL_DEPOSIT,
    COALESCE(W.TOTAL_WITHDRAWAL, 0) AS TOTAL_WITHDRAWAL,
    COALESCE(B.TOTAL_BET, 0) AS TOTAL_BET,
    COALESCE(B.TOTAL_WIN, 0) AS TOTAL_WIN
FROM
    PLAYER P
        LEFT JOIN
    CTE_DEPOSIT D ON P.ID = D.PLAYER_ID
        LEFT JOIN
    CTE_WITHDRAWAL W ON P.ID = W.PLAYER_ID
        LEFT JOIN
    CTE_BET B ON P.ID = B.PLAYER_ID
WHERE
    P.ID = 127800;

