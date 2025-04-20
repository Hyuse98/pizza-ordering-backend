-- Inserir 10 usuários
INSERT INTO users (id, active, city, country, email_address, first_name, house_number, last_name, neighborhood, password_value, phone_number, state, street, zip_code) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', TRUE, 'São Paulo', 'Brasil', 'usuario1@email.com', 'João', '10', 'Silva', 'Centro', 'senha123', '1199999999', 'SP', 'Rua A', '01001000'),
('b1f0b6a1-2b1c-4d0e-9e1f-3a4b5c6d7e8f', TRUE, 'Rio de Janeiro', 'Brasil', 'usuario2@email.com', 'Maria', '20', 'Santos', 'Copacabana', 'senha456', '2198888888', 'RJ', 'Avenida B', '22000000'),
('c2d3e4f5-67a8-4b9c-8d0e-1a2b3c4d5e6f', TRUE, 'Belo Horizonte', 'Brasil', 'usuario3@email.com', 'Pedro', '30', 'Ferreira', 'Savassi', 'senha789', '3197777777', 'MG', 'Rua C', '30000000'),
('d3a4b5c6-7e9f-5a6b-6c7d-8e9f0a1b2c3d', TRUE, 'Porto Alegre', 'Brasil', 'usuario4@email.com', 'Ana', '40', 'Oliveira', 'Moinhos de Vento', 'senha101', '5196666666', 'RS', 'Avenida D', '90000000'),
('e4b5c6d7-8f0a-6b7c-8d9e-2b3c4d5e6f1a', TRUE, 'Salvador', 'Brasil', 'usuario5@email.com', 'Carlos', '50', 'Pereira', 'Barra', 'senha202', '7195555555', 'BA', 'Rua E', '40000000'),
('f5c6d7e8-0a1b-7c8d-9e0f-3c4d5e6f1a2b', TRUE, 'Brasília', 'Brasil', 'usuario6@email.com', 'Mariana', '60', 'Costa', 'Asa Sul', 'senha303', '6194444444', 'DF', 'Avenida F', '70000000'),
('a6d7e8f9-1b2c-8d9e-0f3d-4e5f6a7b8c9d', TRUE, 'Curitiba', 'Brasil', 'usuario7@email.com', 'Fernando', '70', 'Rocha', 'Batel', 'senha404', '4193333333', 'PR', 'Rua G', '80000000'),
('b7e8f9a0-2c3d-9e0f-1a4e-5f6a7b8c9d0e', TRUE, 'Recife', 'Brasil', 'usuario8@email.com', 'Camila', '80', 'Melo', 'Boa Viagem', 'senha505', '8192222222', 'PE', 'Avenida H', '50000000'),
('c8f9a0b1-3d4e-0f1a-2b5f-6a7b8c9d0e1f', TRUE, 'Fortaleza', 'Brasil', 'usuario9@email.com', 'Lucas', '90', 'Gomes', 'Aldeota', 'senha606', '8591111111', 'CE', 'Rua I', '60000000'),
('d9a0b1c2-4e5f-0a2b-3c6a-7b8c9d0e1f2a', TRUE, 'Manaus', 'Brasil', 'usuario10@email.com', 'Julia', '100', 'Carvalho', 'Adrianópolis', 'senha707', '9290000000', 'AM', 'Avenida J', '69000000');

-- Inserir 10 produtos
INSERT INTO product (id, product_name, price) VALUES
(1, 'Notebook', 2500.00),
(2, 'Smartphone', 1200.50),
(3, 'Tablet', 800.00),
(4, 'Fones de Ouvido', 150.99),
(5, 'Mouse', 45.00),
(6, 'Teclado', 75.50),
(7, 'Monitor', 900.00),
(8, 'Impressora', 300.00),
(9, 'Câmera', 1800.00),
(10, 'Caixa de Som', 200.00);

-- Inserir Carrinhos de Compra para os usuários
INSERT INTO shopping_carts (id, user_id) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
('b1f0b6a1-2b1c-4d0e-9e1f-3a4b5c6d7e8f', 'b1f0b6a1-2b1c-4d0e-9e1f-3a4b5c6d7e8f'),
('c2d3e4f5-67a8-4b9c-8d0e-1a2b3c4d5e6f', 'c2d3e4f5-67a8-4b9c-8d0e-1a2b3c4d5e6f'),
('d3a4b5c6-7e9f-5a6b-6c7d-8e9f0a1b2c3d', 'd3a4b5c6-7e9f-5a6b-6c7d-8e9f0a1b2c3d'),
('e4b5c6d7-8f0a-6b7c-8d9e-2b3c4d5e6f1a', 'e4b5c6d7-8f0a-6b7c-8d9e-2b3c4d5e6f1a'),
('f5c6d7e8-0a1b-7c8d-9e0f-3c4d5e6f1a2b', 'f5c6d7e8-0a1b-7c8d-9e0f-3c4d5e6f1a2b'),
('a6d7e8f9-1b2c-8d9e-0f3d-4e5f6a7b8c9d', 'a6d7e8f9-1b2c-8d9e-0f3d-4e5f6a7b8c9d'),
('b7e8f9a0-2c3d-9e0f-1a4e-5f6a7b8c9d0e', 'b7e8f9a0-2c3d-9e0f-1a4e-5f6a7b8c9d0e'),
('c8f9a0b1-3d4e-0f1a-2b5f-6a7b8c9d0e1f', 'c8f9a0b1-3d4e-0f1a-2b5f-6a7b8c9d0e1f'),
('d9a0b1c2-4e5f-0a2b-3c6a-7b8c9d0e1f2a', 'd9a0b1c2-4e5f-0a2b-3c6a-7b8c9d0e1f2a');

-- Inserir alguns itens no carrinho (exemplos)
INSERT INTO cart_item (id, cart_id, product_id, quantity) VALUES
(1, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 1, 2),  -- João adiciona 2 Notebooks
(2, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 4, 1),  -- João adiciona 1 Fone de Ouvido
(3, 'b1f0b6a1-2b1c-4d0e-9e1f-3a4b5c6d7e8f', 2, 1),  -- Maria adiciona 1 Smartphone
(4, 'c2d3e4f5-67a8-4b9c-8d0e-1a2b3c4d5e6f', 7, 1),  -- Pedro adiciona 1 Monitor
(5, 'd3a4b5c6-7e9f-5a6b-6c7d-8e9f0a1b2c3d', 5, 2),  -- Ana adiciona 2 Mouses
(6, 'e4b5c6d7-8f0a-6b7c-8d9e-2b3c4d5e6f1a', 3, 1),  -- Carlos adiciona 1 Tablet
(7, 'f5c6d7e8-0a1b-7c8d-9e0f-3c4d5e6f1a2b', 9, 1),  -- Mariana adiciona 1 Câmera
(8, 'a6d7e8f9-1b2c-8d9e-0f3d-4e5f6a7b8c9d', 6, 1),  -- Fernando adiciona 1 Teclado
(9, 'b7e8f9a0-2c3d-9e0f-1a4e-5f6a7b8c9d0e', 8, 1),  -- Camila adiciona 1 Impressora
(10, 'c8f9a0b1-3d4e-0f1a-2b5f-6a7b8c9d0e1f', 10, 2); -- Lucas adiciona 2 Caixas de Som