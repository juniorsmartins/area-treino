INSERT INTO usuarios (id, username, password, role) VALUES
    (100, 'bob@email.com', '$2a$12$M6TkuV3HQur7Av7KsljxcOro3dui0A75Vl4R3m/3ZFMdB94PRLbJO', 'ROLE_ADMIN'),
    (101, 'fowler@email.com', '$2a$12$M6TkuV3HQur7Av7KsljxcOro3dui0A75Vl4R3m/3ZFMdB94PRLbJO', 'ROLE_CLIENTE'),
    (102, 'beck@email.com', '$2a$12$M6TkuV3HQur7Av7KsljxcOro3dui0A75Vl4R3m/3ZFMdB94PRLbJO', 'ROLE_CLIENTE');

INSERT INTO clientes(id, nome, cpf, usuario_id) VALUES
    (10, 'Bianca Silva', '77917017079', 101),
    (20, 'Roberto Gomes', '78149242007', 102);

