-- Migração para criar tabelas, corrigir tipos e adicionar índices

-- Tabelas
CREATE TABLE cart_item (
    quantity INTEGER,
    id BIGINT NOT NULL,
    product_id BIGINT,
    cart_id UUID, -- Alterado para UUID
    PRIMARY KEY (id)
);

CREATE TABLE event_publication (
    completion_date TIMESTAMP(6) WITH TIME ZONE,
    publication_date TIMESTAMP(6) WITH TIME ZONE,
    id UUID NOT NULL,
    event_type VARCHAR(255),
    listener_id VARCHAR(255),
    serialized_event VARCHAR(255), -- Alterado para TEXT
    PRIMARY KEY (id)
);

CREATE TABLE order_items (
    price NUMERIC(10, 2),  -- Alterado para NUMERIC(10, 2)
    quantity INTEGER,
    id UUID NOT NULL,
    order_id UUID,
    description VARCHAR(255),       -- Alterado para TEXT
    PRIMARY KEY (id)
);

CREATE TABLE orders (
    total_amount NUMERIC(10, 2), -- Alterado para NUMERIC(12, 2)
    order_date TIMESTAMP(6),
    id UUID NOT NULL,
    user_id UUID,
    contact_phone VARCHAR(255),
    delivery_address VARCHAR(255), -- Alterado para TEXT
    order_number VARCHAR(255),
    payment_method VARCHAR(255),
    status ENUM('CANCELLED','DELIVERED','PENDING','PROCESSING','SHIPPED'),
    PRIMARY KEY (id)
);

CREATE TABLE product (
    price NUMERIC(10, 2),  -- Alterado para NUMERIC(10, 2)
    id BIGINT NOT NULL,
    product_name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE shopping_carts (
    user_id UUID UNIQUE,
    id UUID NOT NULL,       -- Alterado para UUID
    PRIMARY KEY (id)
);

CREATE TABLE users (
    active BOOLEAN NOT NULL,
    id UUID NOT NULL,
    city VARCHAR(255),
    country VARCHAR(255),
    email_address VARCHAR(255),
    first_name VARCHAR(255),
    house_number VARCHAR(255),
    last_name VARCHAR(255),
    neighborhood VARCHAR(255),
    password_value VARCHAR(255),
    phone_number VARCHAR(255),
    state VARCHAR(255),
    street VARCHAR(255),
    zip_code VARCHAR(255),
    PRIMARY KEY (id)
);

-- Chaves Estrangeiras
ALTER TABLE cart_item
    ADD CONSTRAINT FK_cart_item_shopping_carts
    FOREIGN KEY (cart_id)
    REFERENCES shopping_carts(id);

ALTER TABLE cart_item
    ADD CONSTRAINT FK_cart_item_product
    FOREIGN KEY (product_id)
    REFERENCES product(id);

ALTER TABLE order_items
    ADD CONSTRAINT FK_order_items_orders
    FOREIGN KEY (order_id)
    REFERENCES orders(id);

ALTER TABLE orders
    ADD CONSTRAINT FK_orders_users
    FOREIGN KEY (user_id)
    REFERENCES users(id);

ALTER TABLE shopping_carts
    ADD CONSTRAINT FK_shopping_carts_users
    FOREIGN KEY (user_id)
    REFERENCES users(id);

CREATE SEQUENCE cart_item_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE product_seq
    START WITH 1
    INCREMENT BY 1;

-- Índices
CREATE INDEX idx_cart_item_cart_id ON cart_item (cart_id);
CREATE INDEX idx_cart_item_product_id ON cart_item (product_id);
CREATE INDEX idx_order_items_order_id ON order_items (order_id);
CREATE INDEX idx_orders_user_id ON orders (user_id);

-- Índices adicionais (opcionais)
-- CREATE INDEX idx_orders_status ON orders (status);
-- CREATE INDEX idx_users_email_address ON users (email_address);