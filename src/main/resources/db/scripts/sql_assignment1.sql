-- Create Player table
CREATE TABLE Player (
    ID INT PRIMARY KEY,
    First_Name VARCHAR(50),
    Last_Name VARCHAR(50),
    Email VARCHAR(100),
    status VARCHAR(20)
    );

-- Insert records into Player table
INSERT INTO Player (ID, First_Name, Last_Name, Email, status) VALUES
(127800, 'Jamal', 'Jokic', 'jjokic@gmail.com', 'ACTIVE'),
(127801, 'Kobe', 'James', 'kobej@yahoo.com', 'ACTIVE'),
(127802, 'Luka', 'Oneal', 'lukkkkka@hotmail.com', 'ACTIVE'),
(127803, 'Michael', 'Lillard', 'dametime@gmail.com', 'CLOSED'),
(127804, 'Allen', 'Bryant', 'crossover@gmail.com', 'SUSPENDED'),
(127805, 'James', 'Kidd', 'j0909kidd@yahoo.ca', 'ACTIVE'),
(127806, 'Pascal', 'Tatum', 'pt8781@telus.net', 'TIMEOUT');

-- Create Deposit table
CREATE TABLE Deposit (
    ID INT PRIMARY KEY,
    transaction_date DATETIME,
    player_id INT,
    amount DECIMAL(10, 2),
    status VARCHAR(20)
);

-- Insert records into Deposit table
INSERT INTO Deposit (ID, transaction_date, player_id, amount, status) VALUES
  (1, '2024-01-10 18:45:12', 127801, 100.00, 'FAILED'),
  (2, '2024-01-11 12:00:22', 127801, 75.00, 'SUCCESS'),
  (3, '2024-01-12 13:23:07', 127800, 63.00, 'SUCCESS'),
  (4, '2024-01-12 07:08:18', 127800, 88.23, 'FAILED'),
  (5, '2024-01-12 03:24:09', 127800, 175.20, 'SUCCESS'),
  (6, '2024-01-16 00:18:23', 127801, 1075.00, 'SUCCESS'),
  (7, '2024-01-20 15:16:16', 127800, 1285.50, 'SUCCESS');

-- Create Withdrawal table
CREATE TABLE Withdrawal (
    ID INT PRIMARY KEY,
    transaction_date DATETIME,
    player_id INT,
    amount DECIMAL(10, 2),
    status VARCHAR(20)
);

-- Insert records into Withdrawal table
INSERT INTO Withdrawal (ID, transaction_date, player_id, amount, status) VALUES
(1, '2024-01-04 16:45:05', 127800, 50.00, 'SUCCESS'),
(2, '2024-01-04 17:10:07', 127800, 20005.00, 'SUCCESS'),
(3, '2024-01-11 23:59:08', 127800, 1000.00, 'SUCCESS'),
(4, '2024-01-11 00:00:02', 127800, 800.78, 'SUCCESS'),
(5, '2024-01-13 08:28:01', 127800, 150000.30, 'SUCCESS'),
(6, '2024-01-16 00:19:11', 127800, 20.00, 'PENDING'),
(7, '2024-01-31 19:16:41', 127800, 40.85, 'SUCCESS');

-- Create Bets table
CREATE TABLE Bets (
  ID INT PRIMARY KEY,
  transaction_date DATETIME,
  player_id INT,
  bet_amount DECIMAL(10, 2),
  win_amount DECIMAL(10, 2)
);

-- Insert records into Bets table
INSERT INTO Bets (ID, transaction_date, player_id, bet_amount, win_amount) VALUES
(1, '2024-01-12 16:45:05', 127800, 15.00, -15.00),
(2, '2024-01-12 17:10:07', 127800, 30.00, 16567.01),
(3, '2024-01-12 23:59:08', 127800, 1000.00, -1000.00),
(4, '2024-01-12 00:00:02', 127800, 750.00, 150.00),
(5, '2024-01-12 08:28:01', 127800, 100.00, 250.00),
(6, '2024-01-12 00:19:11', 127800, 22.00, -22.00),
(7, '2024-01-12 19:16:41', 127800, 45.00, -45.00);
