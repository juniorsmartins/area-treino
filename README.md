# Área de Treino


## API-Rest.Estacionamento.Java

O objetivo será desenvolver um sistema de gerenciamento para estacionamento.O sistema será uma API Rest com autenticação por Json Web Token (JWT). E possuirá uma documentação dos recursos disponíveis pela API.

1. Requisitos e Configurações:
> - [ ] README;
> - [x] API configurada com Timezone do país de origem;
> - [x] API configurada com Locale do país de origem;
> - [x] API com sistema de auditoria;
> - [x] Configuração de acesso a base de dados para ambiente de desenvolvimento;
> - [x] O usuário terá email, o qual será usado como username (valor único);
> - [x] O usuário terá senha de seis caracteres;
> - [x] O usuário deverá ter perfil de administrador ou cliente;
> - [x] O usuário será criado sem autenticação;
> - [x] O usuário será localizado pelo identificador gerado;
> - [x] O administrador, quando autenticado, poderá recuperar qualquer usuário pelo id;
> - [x] O cliente, quando autenticado, poderá recuperar somente os próprios dados;
> - [x] O usuário poderá alterar a senha;
> - [x] O administrador poderá listar todos os usuários quando estiver autenticado;
> - [x] Documentar todos os recursos;
> - [x] Realizar testes de integração do tipo ponto-a-ponto (end-to-end) dos recursos;
> - [x] Implementar sistema de segurança e autenticação com Json Web Token;
> - [x] Documentar recursos de autenticação;
> - [x] Realizar testes sobre o sistema de autenticação;
> - [ ] O cadastro do cliente só será possível depois de realizado o cadastro como usuário. Um usuário poderá ter apenas um único cadastro como cliente e um cliente poderá estar vinculado a um único usuário;
> - [ ] O cadastro do cliente contém nome completo e CPF (valor único);
> - [ ] O cadastro do cliente deve estar vinculado a um usuário;
> - [ ] Todas as ações relacionadas com vagas requerem autenticação e são restritas ao administrador;
> - [ ] Cada vaga contém código único que não deve ser o id;
> - [ ] Cada vaga contém status livre ou ocupada;
> - [ ] A vaga será localizada pelo código;
> - [ ] Ao estacionar o veículo, as seguintes informações serão armazenadas: placa, marca, modelo, cor, data de entrada e CPF;
> - [ ] O processo de registro no estacionamento gerará número único de recibo;
> - [ ] Ao sair do estacionamento, as seguintes informações serão armazenadas: data de saída, valor do período estacionado e valor do desconto;
> - [ ] Uma vaga de estacionamento é ocupada por muitos veículos, mas não ao mesmo tempo;
> - [ ] O desconto será concedido sempre após o cliente completar 10 estacionamentos (entrada e saída);
> - [ ] A porcentagem de desconto será de 30% sobre o valor a pagar;
> - [ ] O estacionamento será vinculado a uma vaga e ao cliente;
> - [ ] Ao retirar o veículo, o cliente informará o número do recibo gerado na data de entrada;
> - [ ] Apenas um administrador fará operação de check-in e check-out;
> - [ ] Administrador e cliente podem localizar dados de entrada pelo número do recibo;
> - [ ] O administrador poderá listar os estacionamentos pelo CPF do cliente;
> - [ ] O custo do estacionamento: primeiros quinze minutos (5), primeiros 60 minutos (9,25), a partir de 60 minutos deve incluir 1,75 para cada faixa de 15 minutos;
> - [ ] O cliente poderá gerar relatório em PDF com sua lista de estacionamentos.


## Desafio-picpay-java (versão 1)

https://github.com/PicPay/picpay-desafio-backend 
https://www.youtube.com/watch?v=QXunBiLq2SM 

## Desafio-v2-picpay-java

> - [X] Padrão internacional "ISO 8601" de Data e Hora;
> - [ ] 


