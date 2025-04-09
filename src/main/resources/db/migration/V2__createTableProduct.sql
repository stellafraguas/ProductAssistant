CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(250) NOT NULL,
    category_id BIGINT NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    active BOOLEAN NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    last_updated_by VARCHAR(50),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at TIMESTAMP,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id)
);
