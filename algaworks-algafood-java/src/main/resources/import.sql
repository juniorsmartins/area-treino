INSERT INTO cozinhas (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinhas (id, nome) VALUES (2, 'Indiana');
INSERT INTO cozinhas (id, nome) VALUES (3, 'Estadunidense');

INSERT INTO restaurantes (nome, taxa_frete, cozinha_id) VALUES ('Indian Food', 2, 2);
INSERT INTO restaurantes (nome, taxa_frete, cozinha_id) VALUES ('McDonald', 5, 3);
INSERT INTO restaurantes (nome, taxa_frete, cozinha_id) VALUES ('Burguer King', 3, 3);

INSERT INTO estados (id, nome) VALUES (1, 'Rio Grande do Sul');
INSERT INTO estados (id, nome) VALUES (2, 'Santa Catarina');
INSERT INTO estados (id, nome) VALUES (3, 'Paraná');
INSERT INTO estados (id, nome) VALUES (4, 'Mato Grosso');
INSERT INTO estados (id, nome) VALUES (5, 'Minas Gerais');

INSERT INTO cidades (id, nome, estado_id) VALUES (1, 'Dom Pedrito', 1);
INSERT INTO cidades (id, nome, estado_id) VALUES (2, 'Porto Alegre', 1);
INSERT INTO cidades (id, nome, estado_id) VALUES (3, 'Florianópolis', 2);
INSERT INTO cidades (id, nome, estado_id) VALUES (4, 'Toledo', 3);
INSERT INTO cidades (id, nome, estado_id) VALUES (5, 'Cuiabá', 4);
INSERT INTO cidades (id, nome, estado_id) VALUES (6, 'Iturama', 5);

INSERT INTO formas_pagamento (id, descricao) VALUES (1, 'Cartão de Crédito');
INSERT INTO formas_pagamento (id, descricao) VALUES (2, 'Cartão de Débito');
INSERT INTO formas_pagamento (id, descricao) VALUES (3, 'Dinheiro');

INSERT INTO permissoes (id, nome, descricao) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissoes (id, nome, descricao) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

