-- Datos para la tabla 'activities'
INSERT INTO ACTIVITIES (title, description, start_date_time, end_date_time) VALUES
('Clase de Yoga', 'Sesión de yoga para mejorar la flexibilidad y la relajación.', CURRENT_TIMESTAMP + INTERVAL '11' HOUR, CURRENT_TIMESTAMP + INTERVAL '12' HOUR),
('Entrenamiento de Fuerza', 'Entrenamiento con pesas para aumentar la fuerza y la masa muscular.', CURRENT_TIMESTAMP + INTERVAL '11' HOUR, CURRENT_TIMESTAMP + INTERVAL '12' HOUR),
('Clase de Spinning', 'Clase de spinning para mejorar la resistencia cardiovascular.', CURRENT_TIMESTAMP + INTERVAL '1' DAY + INTERVAL '8' HOUR, CURRENT_TIMESTAMP + INTERVAL '1' DAY + INTERVAL '9' HOUR),
('Pilates', 'Sesión de pilates para fortalecer el core y mejorar la postura.', CURRENT_TIMESTAMP + INTERVAL '1' DAY + INTERVAL '17' HOUR, CURRENT_TIMESTAMP + INTERVAL '1' DAY + INTERVAL '18' HOUR),
('Zumba', 'Clase de Zumba para quemar calorías y divertirse al ritmo de la música.', CURRENT_TIMESTAMP + INTERVAL '2' DAY + INTERVAL '19' HOUR, CURRENT_TIMESTAMP + INTERVAL '2' DAY + INTERVAL '20' HOUR);


-- Datos para la tabla 'users'
INSERT INTO USERS (id, name, email, password, role) VALUES
(UUID(), 'agalmad', 'agalmad@gmail.com', '$2a$10$QfVnMjApZVS8zVmI0ptbcOF69xg.PRC3uj9CJkmr/i5h5mlapwE1G', 'ADMIN');

