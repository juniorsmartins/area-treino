insert into produtos(id, data_criacao, nome, descricao, preco) values(1, date_sub(sysdate(), interval 7 day), 'Kindle', 'Ovo de chocolate com brinde dentro.', 499);
insert into produtos(id, data_criacao, nome, descricao, preco) values(3, date_sub(sysdate(), interval 3 day), 'Criando Microsserviços', 'Livro de Sam Newman sobre arquitetura de software', 225.8);
insert into produtos(id, data_criacao, nome, descricao, preco) values(4, date_sub(sysdate(), interval 1 day), 'Spring Data Jpa', 'Curso de especialista', 552.85);

insert into clientes(id, nome) values(3, 'Eric Evans');
insert into clientes(id, nome) values(4, 'Jeff Sutherland');
insert into clientes(id, nome) values(5, 'Ron Jeffries');
insert into clientes(id, nome) values(6, 'Ward Cunningham');

insert into pedidos(id, cliente_id, data_criacao, data_conclusao, total, status) values(1, 6, NOW(), NOW(), 998.0, 'AGUARDANDO');
insert into itens_pedido(pedido_id, produto_id, preco_produto, quantidade) values(1, 1, 499, 2);

insert into pedidos(id, cliente_id, data_criacao, data_conclusao, total, status) values(2, 6, NOW(), NOW(), 499.0, 'AGUARDANDO');
insert into itens_pedido(pedido_id, produto_id, preco_produto, quantidade) values(2, 1, 499, 1);

insert into pagamento (pedido_id, status, numero_cartao, tipo_pagamento) values(2, 'PROCESSANDO', '123', 'Cartao')

insert into categorias(id, nome) values(1, 'Eletrônicos');

