CREATE TABLE IF NOT EXISTS ingredients
(
    ingredient_id                    SERIAL PRIMARY KEY,
    name                             VARCHAR(255)  NOT NULL,
    price                            NUMERIC(5, 1)  NOT NULL,
    available_quantity               SMALLINT  NULL,
    type                             varchar (155) NOT NULL,
    created_at                       TIMESTAMP DEFAULT NOW()
);