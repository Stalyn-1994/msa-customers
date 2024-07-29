-- Insert data into person table
INSERT INTO person (identification, name, gender, age, address, cellphone)
VALUES ('ID1234567890', 'Alice Smith', 'Female', 30, '123 Elm Street, Springfield', '555-1234'),
       ('ID0987654321', 'Bob Johnson', 'Male', 45, '456 Oak Street, Springfield', '555-5678'),
       ('ID1122334455', 'Carol White', 'Female', 27, '789 Pine Street, Springfield', '555-9012'),
       ('ID2233445566', 'David Brown', 'Male', 36, '321 Maple Street, Springfield', '555-3456'),
       ('ID3344556677', 'Eve Black', 'Female', 40, '654 Birch Street, Springfield', '555-7890');
-- Insert data into customer table
INSERT INTO customer (password, client_id, state)
VALUES ('e4ec16243e6145c17e5d393a6c17b7f7b1cc4dc2ee40ede728af0bb4c34d1b9c', 'C123', TRUE),
       ('e4ec16243e6145c17e5d393a6c17b7f7b1cc4dc2ee40ede728af0bb4c34d1b9c', 'C456', FALSE),
       ('e4ec16243e6145c17e5d393a6c17b7f7b1cc4dc2ee40ede728af0bb4c34d1b9c', 'C789', TRUE),
       ('e4ec16243e6145c17e5d393a6c17b7f7b1cc4dc2ee40ede728af0bb4c34d1b9c', 'C321', TRUE),
       ('e4ec16243e6145c17e5d393a6c17b7f7b1cc4dc2ee40ede728af0bb4c34d1b9c', 'C654', FALSE);
