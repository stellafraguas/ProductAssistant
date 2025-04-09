CREATE TABLE IF NOT EXISTS category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    parent_id BIGINT,
    CONSTRAINT fk_category_parent FOREIGN KEY (parent_id) REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
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
