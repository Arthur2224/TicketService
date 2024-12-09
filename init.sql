-- init.sql
CREATE TABLE clients (
     id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL, 
    created_at TIMESTAMP, 
    updated_at TIMESTAMP, 
    email VARCHAR(255),
    status VARCHAR(255)
);
CREATE TABLE tickets (
    id BIGINT PRIMARY KEY,             
    client_id BIGINT NOT NULL,                         
    creation_datetime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    concert_hall VARCHAR(10) NOT NULL,                 
    event_code INT NOT NULL,                           
    is_promo BOOLEAN DEFAULT FALSE,                    
    stadium_sector VARCHAR(255),                       
    max_backpack_weight NUMERIC,                        
    price DECIMAL(10, 2) NOT NULL,                     
    CONSTRAINT fk_client FOREIGN KEY (client_id)
    REFERENCES clients(id)

);

INSERT INTO clients (name, created_at, updated_at, email, status)
VALUES 
('Liam Müller', '2024-11-20 09:00:00', '2024-11-20 09:30:00', 'liam.muller@example.com','INACTIVE'),
('Sofia Rossi', '2024-11-19 13:00:00', '2024-11-19 13:45:00', 'sofia.rossi@example.com','INACTIVE'),
('Lucas Novak', '2024-11-18 11:15:00', '2024-11-18 11:45:00', 'lucas.novak@example.com','INACTIVE'),
('Anna Schmidt', '2024-11-17 16:20:00', '2024-11-17 16:50:00', 'anna.schmidt@example.com','INACTIVE'),
('Marta Kowalski', '2024-11-16 10:05:00', '2024-11-16 10:35:00', 'marta.kowalski@example.com','INACTIVE'),
('Noah Dubois', '2024-11-15 15:00:00', '2024-11-15 15:30:00', 'noah.dubois@example.com','INACTIVE'),
('Eva Horváth', '2024-11-14 14:00:00', '2024-11-14 14:20:00', 'eva.horvath@example.com','INACTIVE'),
('Oliver Hansen', '2024-11-13 12:00:00', '2024-11-13 12:45:00', 'oliver.hansen@example.com','INACTIVE'),
('Chloe Lefevre', '2024-11-12 09:30:00', '2024-11-12 10:00:00', 'chloe.lefevre@example.com','INACTIVE'),
('Alexander Petrov', '2024-11-11 11:00:00', '2024-11-11 11:20:00', 'alexander.petrov@example.com','INACTIVE'),
('Isabelle Schneider', '2024-11-10 17:15:00', '2024-11-10 17:45:00', 'isabelle.schneider@example.com','INACTIVE'),
('Luca Moretti', '2024-11-09 13:00:00', '2024-11-09 13:30:00', 'luca.moretti@example.com','INACTIVE'),
('Elena Ivanova', '2024-11-08 08:50:00', '2024-11-08 09:20:00', 'elena.ivanova@example.com','INACTIVE'),
('Benjamin Johansson', '2024-11-07 15:30:00', '2024-11-07 16:00:00', 'benjamin.johansson@example.com','INACTIVE'),
('Amelie García', '2024-11-06 10:15:00', '2024-11-06 10:45:00', 'amelie.garcia@example.com','INACTIVE');

INSERT INTO tickets (id,client_id, concert_hall, event_code, is_promo, stadium_sector, max_backpack_weight, price)
VALUES
(12,1, 'Hall A', 123, TRUE, 'A', 5.5, 45.50),
(13,2, 'Hall B', 456, FALSE, 'B', 6.0, 30.00),
(14,3, 'Hall A', 789, TRUE, 'A', 7.2, 60.00),
(15,4, 'Hall B', 101, FALSE, 'B', 8.5, 25.00),
(16,5, 'Hall C', 202, TRUE, 'C', 10.0, 70.00),
(17,6, 'Hall A', 303, FALSE, 'A', 4.8, 35.00),
(18,7, 'Hall B', 404, TRUE, 'B', 5.0, 55.00),
(19,8, 'Hall C', 505, FALSE, 'C', 6.5, 20.00),
(20,9, 'Hall A', 606, TRUE, 'A', 7.0, 75.00),
(21,10, 'Hall B', 707, FALSE, 'B', 9.0, 50.00);
