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
    type VARCHAR(32) NOT NULL,
    customer_id INTEGER REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users (id),
    customer_id INTEGER REFERENCES users (id),
    quantity INTEGER NOT NULL CHECK ( quantity > 0 ),
    product_id INTEGER REFERENCES products (id),
    city VARCHAR(64) NOT NULL,
    street VARCHAR(64) NOT NULL,
    status VARCHAR(32) NOT NULL
);
