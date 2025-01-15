
-- Tabla para usuarios
CREATE TABLE users (
    id VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    email VARCHAR(60) UNIQUE NOT NULL,
    password VARCHAR(60) NOT NULL,
    role VARCHAR(10) DEFAULT 'USER' NOT NULL
);

-- Tabla para las actividades
CREATE TABLE activities (
    id VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_date_time TIMESTAMP NOT NULL,
    end_date_time TIMESTAMP NOT NULL
);


-- Tabla para las actividades de cada usuario
CREATE TABLE participation (
    userId VARCHAR(60) NOT NULL,
    activityId VARCHAR(60) NOT NULL,

);

