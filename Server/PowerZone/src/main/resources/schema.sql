
-- Tabla para usuarios
CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) DEFAULT 'USER' NOT NULL
);

-- Tabla para las actividades
CREATE TABLE activities (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_date_time TIMESTAMP NOT NULL,
    end_date_time TIMESTAMP NOT NULL
);
