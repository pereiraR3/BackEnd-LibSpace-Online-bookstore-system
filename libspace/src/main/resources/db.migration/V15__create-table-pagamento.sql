-- Create table pagamento
CREATE TABLE pagamento (
    id BIGSERIAL PRIMARY KEY,
    id_pedido BIGINT NOT NULL,
    valor MONEY NOT NULL,
    metodo_pagamento VARCHAR(100) NOT NULL,
    data_pagamento DATE DEFAULT CURRENT_DATE,
    CONSTRAINT fk_pedido_in_pagamento FOREIGN KEY (id_pedido) REFERENCES pedido (id) ON DELETE CASCADE
);