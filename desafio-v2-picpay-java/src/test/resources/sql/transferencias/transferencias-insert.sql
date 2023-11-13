INSERT INTO usuarios(version, id, nome, documento, email, senha, tipo) VALUES
    (0, 15, 'Ken Schwaber', '82113009072', 'ken@email.com', '123456', 'LOJISTA'),
    (0, 16, 'Sam Newman', '16455992059', 'sam@email.com', '123456', 'COMUM');

INSERT INTO carteiras(version, id, saldo, usuario_id) VALUES
    (0, 20, 10, 15),
    (0, 21, 50, 16);

INSERT INTO transferencias(version, id, pagador_id, beneficiario_id, valor, data_time_transacao) VALUES
    (0, 5, 21, 20, 1, TIMESTAMP '2023-11-05T11:34:56Z'),
    (0, 6, 21, 20, 2, TIMESTAMP '2023-11-10T12:21:12Z');

