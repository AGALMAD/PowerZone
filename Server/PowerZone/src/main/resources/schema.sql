-- Tabla para usuarios
CREATE TABLE IF NOT EXISTS users(
    id VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(60) UNIQUE NOT NULL,
    password VARCHAR(60) NOT NULL,
    role VARCHAR(10) DEFAULT 'USER' NOT NULL
);

-- Tabla para las actividades
CREATE TABLE IF NOT EXISTS activities(
    id VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    title VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    start_date_time TIMESTAMP NOT NULL,
    end_date_time TIMESTAMP NOT NULL
);

-- Tabla para las actividades de cada usuario
CREATE TABLE IF NOT EXISTS participation (
    userId VARCHAR(60) NOT NULL,
    activityId VARCHAR(60) NOT NULL
);
