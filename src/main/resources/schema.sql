CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    balance DOUBLE PRECISION CHECK ( balance >= 0 )
);

CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(256) NOT NULL,
    price DOUBLE PRECISION NOT NULL CHECK ( price >= 0 ),
    quantity INTEGER NOT NULL CHECK ( quantity >= 0 ),
    type  VARCHAR(32) NOT NULL,
    customer_id INTEGER REFERENCES users (id)
);