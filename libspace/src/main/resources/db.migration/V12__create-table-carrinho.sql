-- Create table carrinho
CREATE TABLE carrinho (
    id BIGSERIAL PRIMARY KEY,
    id_user BIGINT NOT NULL,
    data_criacao DATE DEFAULT CURRENT_DATE,
    status BOOLEAN DEFAULT TRUE NOT NULL,
    CONSTRAINT fk_user_in_carrinho FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE
);