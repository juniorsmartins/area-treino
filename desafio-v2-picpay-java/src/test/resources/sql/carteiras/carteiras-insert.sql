INSERT INTO usuarios(version, id, nome, documento, email, senha, tipo) VALUES
    (0, 15, 'Ken Schwaber', '82113009072', 'ken@email.com', '123456', 'LOJISTA'),
    (0, 16, 'Sam Newman', '16455992059', 'sam@email.com', '123456', 'COMUM');

INSERT INTO carteiras(version, id, saldo, usuario_id) VALUES
    (0, 20, 10, 16);

