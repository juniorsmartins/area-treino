insert into produtos(id, data_criacao, nome, descricao, preco, ativo) values(1, date_sub(sysdate(), interval 7 day), 'Kindle', 'Ovo de chocolate com brinde dentro.', 499, 'SIM');
insert into produtos(id, data_criacao, nome, descricao, preco, ativo) values(3, date_sub(sysdate(), interval 3 day), 'Criando Microsserviços', 'Livro de Sam Newman sobre arquitetura de software', 225.8, 'SIM');
insert into produtos(id, data_criacao, nome, descricao, preco, ativo) values(4, date_sub(sysdate(), interval 1 day), 'Spring Data Jpa', 'Curso de especialista', 552.15, 'NAO');

insert into clientes(id, nome, cpf) values(3, 'Eric Evans - Blue Book', '99988877799');
insert into clientes(id, nome, cpf) values(4, 'Jeff Sutherland - Scrum', '77788877799');
insert into clientes(id, nome, cpf) values(5, 'Ron Jeffries - Ágil', '66688877799');
insert into clientes(id, nome, cpf) values(6, 'Ward Cunningham - Ágil', '88888877799');
insert into clientes(id, nome, cpf) values(7, 'Kent TDD Beck', '122112211221');

insert into cliente_detalhe(cliente_id, sexo, data_nascimento) values (3, 'MASCULINO', date_sub(sysdate(), interval 36 year));
insert into cliente_detalhe(cliente_id, sexo, data_nascimento) values (4, 'MASCULINO', date_sub(sysdate(), interval 70 year));
insert into cliente_detalhe(cliente_id, sexo, data_nascimento) values (5, 'MASCULINO', date_sub(sysdate(), interval 65 year));
insert into cliente_detalhe(cliente_id, sexo, data_nascimento) values (6, 'MASCULINO', date_sub(sysdate(), interval 50 year));
insert into cliente_detalhe(cliente_id, sexo, data_nascimento) values (7, 'MASCULINO', date_sub(sysdate(), interval 42 year));

insert into pedidos(id, cliente_id, data_criacao, data_conclusao, total, status) values(1, 6, date_sub(sysdate(), interval 5 day), NOW(), 998.0, 'AGUARDANDO');
insert into itens_pedido(pedido_id, produto_id, preco_produto, quantidade) values(1, 1, 499, 2);

insert into pedidos(id, cliente_id, data_criacao, data_conclusao, total, status) values(2, 6, date_sub(sysdate(), interval 3 day), NOW(), 499.0, 'AGUARDANDO');
insert into itens_pedido(pedido_id, produto_id, preco_produto, quantidade) values(2, 1, 499, 1);

insert into pedidos(id, cliente_id, data_criacao, data_conclusao, total, status) values(3, 4, date_sub(sysdate(), interval 2 day), NOW(), 499.0, 'AGUARDANDO');
insert into itens_pedido(pedido_id, produto_id, preco_produto, quantidade) values(3, 1, 500, 1);

insert into pedidos(id, cliente_id, data_criacao, data_conclusao, total, status) values(4, 4, date_sub(sysdate(), interval 2 day), NOW(), 499.0, 'AGUARDANDO');
insert into itens_pedido(pedido_id, produto_id, preco_produto, quantidade) values(4, 3, 500, 1);

insert into pagamentos(pedido_id, status, numero_cartao, tipo_pagamento) values(1, 'PROCESSANDO', '99999999', 'cartao');
insert into pagamentos(pedido_id, status, codigo_barras, tipo_pagamento) values(2, 'PROCESSANDO', '12345678', 'boleto');

insert into categorias(id, nome) values(1, 'Eletrodomésticos');

