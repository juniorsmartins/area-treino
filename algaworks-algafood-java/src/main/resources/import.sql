INSERT INTO cozinhas (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinhas (id, nome) VALUES (2, 'Indiana');
INSERT INTO cozinhas (id, nome) VALUES (3, 'Estadunidense');

INSERT INTO restaurantes (id, nome, taxa_frete, cozinha_id) VALUES (1, 'Indian Food', 2, 2);
INSERT INTO restaurantes (id, nome, taxa_frete, cozinha_id) VALUES (2, 'McDonald', 5, 3);
INSERT INTO restaurantes (id, nome, taxa_frete, cozinha_id) VALUES (3, 'Burguer King', 3, 3);

INSERT INTO estados (id, nome) VALUES (1, 'Minas Gerais');
INSERT INTO estados (id, nome) VALUES (2, 'São Paulo');
INSERT INTO estados (id, nome) VALUES (3, 'Ceará');
INSERT INTO estados (id, nome) VALUES (4, 'Mato Grosso');
INSERT INTO estados (id, nome) VALUES (5, 'Rio Grande do Sul');

INSERT INTO cidades (id, nome, estado_id) VALUES (1, 'Uberlândia', 1);
INSERT INTO cidades (id, nome, estado_id) VALUES (2, 'Belo Horizonte', 1);
INSERT INTO cidades (id, nome, estado_id) VALUES (3, 'São Paulo', 2);
INSERT INTO cidades (id, nome, estado_id) VALUES (4, 'Campinas', 2);
INSERT INTO cidades (id, nome, estado_id) VALUES (5, 'Fortaleza', 3);

INSERT INTO formas_pagamento (id, descricao) VALUES (1, 'Cartão de Crédito');
INSERT INTO formas_pagamento (id, descricao) VALUES (2, 'Cartão de Débito');
INSERT INTO formas_pagamento (id, descricao) VALUES (3, 'Dinheiro');

INSERT INTO permissoes (id, nome, descricao) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissoes (id, nome, descricao) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT INTO restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 2);
INSERT INTO restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 3);
INSERT INTO restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 3);
INSERT INTO restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 2);
INSERT INTO restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 3);
