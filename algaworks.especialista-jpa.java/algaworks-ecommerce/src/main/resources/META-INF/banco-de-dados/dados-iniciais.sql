insert into produtos(id, nome, descricao, preco) values(1, 'Kindle', 'Ovo de chocolate com brinde dentro.', 499);
insert into produtos(id, nome, descricao, preco) values(3, 'Criando Microsserviços', 'Livro de Sam Newman sobre arquitetura de software', 225.8);
insert into produtos(id, nome, descricao, preco) values(4, 'Spring Data Jpa', 'Curso de especialista', 552.85);

insert into clientes(id, nome) values(3, 'Eric Evans');
insert into clientes(id, nome) values(4, 'Jeff Sutherland');
insert into clientes(id, nome) values(5, 'Ron Jeffries');
insert into clientes(id, nome) values(6, 'Ward Cunningham');

insert into pedidos(id, cliente_id, data_pedido, data_conclusao, nota_fiscal_id, total, status) values(1, 6, NOW(), NOW(), 1, 10, 'AGUARDANDO');

insert into itens_pedido(id, pedido_id, produto_id, preco_produto, quantidade) values(1, 1, 1, 28.82, 2);

insert into categorias(id, nome) values(1, 'Eletrônicos');

