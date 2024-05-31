SELECT
    p.player_name,
    COALESCE(SUM(d.amount), 0) AS total_deposits,
    COALESCE(SUM(w.amount), 0) AS total_withdrawals,
    COALESCE(SUM(b.bet_amount), 0) AS total_bets,
    COALESCE(SUM(b.win_amount), 0) AS total_wins
FROM
    Player p
        LEFT JOIN
    Deposit d ON p.player_id = d.player_id
        LEFT JOIN
    Withdrawal w ON p.player_id = w.player_id
        LEFT JOIN
    Bets b ON p.player_id = b.player_id
WHERE
    p.player_id = 127800
  AND DATE(d.transaction_date) = '2024-01-12'
  AND DATE(w.transaction_date) = '2024-01-12'
  AND DATE(b.transaction_date) = '2024-01-12'
GROUP BY
    p.player_name;
