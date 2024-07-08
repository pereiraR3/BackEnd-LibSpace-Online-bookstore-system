-- Create table pedido
CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY,
    id_user BIGINT NOT NULL,
    id_carrinho BIGINT NOT NULL,
    valor_total MONEY NOT NULL,
    status BOOLEAN DEFAULT FALSE,
    data_pedido DATE DEFAULT CURRENT_DATE,
    CONSTRAINT fk_user_in_pedido FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_carrinho_in_pedido FOREIGN KEY (id_carrinho) REFERENCES carrinho (id) ON DELETE CASCADE
);
