package com.algaworks.junit.ecommerce;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.List;

@DisplayName("Carrinho de Compras")
class CarrinhoCompraTest {

    private Cliente cliente;

    private ItemCarrinhoCompra item1;

    private ItemCarrinhoCompra item2;

    @BeforeEach
    void criarCenarioGlobal() {
        cliente  = new Cliente(1L, "Kent Beck");
        var produto1 = new Produto(1L, "Notebook", "Dell XPS-9320", BigDecimal.valueOf(12000));
        var produto2 = new Produto(2L, "Mouse Optico", "Cor preta e sem fio", BigDecimal.valueOf(100));
        item1 = new ItemCarrinhoCompra(produto1, 1);
        item2 = new ItemCarrinhoCompra(produto2, 1);
    }

    @Nested
    @DisplayName("Métodos Get")
    class MetodosGet {

        @Test
        @DisplayName("getItens() - retornar cópia da lista de itens.")
        void dadoGetItens_QuandoConsultarItens_EntaoRetornarCopiaDeListaDeItens() {
            var listaOriginal = List.of(item1, item2);
            var carrinhoCompra = new CarrinhoCompra(cliente, listaOriginal);
            var listaCopiada = carrinhoCompra.getItens();

            Assertions.assertEquals(listaOriginal.get(0).getQuantidade(), listaCopiada.get(0).getQuantidade());
            listaCopiada.get(0).adicionarQuantidade(1);
            Assertions.assertNotEquals(listaOriginal.get(0).getQuantidade(), listaCopiada.get(0).getQuantidade());
        }

        @Test
        @DisplayName("getTotalValor() - lista de dois itens com uma quantidade cada.")
        void dadoGetTotalValor_QuandoConsultarDoisItensComUmaQuantidadeCada_EntaoRetornar12100() {
            var carrinhoCompra = new CarrinhoCompra(cliente, List.of(item1, item2));
            var valorTotal = carrinhoCompra.getValorTotal();
            Assertions.assertEquals(BigDecimal.valueOf(12100), valorTotal);
        }

        @Test
        @DisplayName("getTotalValor() - lista de dois itens com uma e três quantidades.")
        void dadoGetValorTotal_QuandoConsultarDoisItensComUmaAndTresQuantidades_EntaoRetornarBigDecimalCom12300() {
            item2.adicionarQuantidade(2); // Acrescentou dois mouses - agora são três por 100 reais cada
            var carrinhoCompra = new CarrinhoCompra(cliente, List.of(item1, item2));
            var valorTotal = carrinhoCompra.getValorTotal();
            Assertions.assertEquals(BigDecimal.valueOf(12300), valorTotal);
        }

        @Test
        @DisplayName("getQuantidadeTotalDeProdutos() - lista de dois itens com uma quantidade cada.")
        void dadoGetQuantidadeTotalDeProdutos_QuandoSomarDoisItensComUmaQuantidadeCada_EntaoRetornarDois() {
            var carrinhoCompra = new CarrinhoCompra(cliente, List.of(item1, item2));
            var quantidadeTotal = carrinhoCompra.getQuantidadeTotalDeProdutos();
            Assertions.assertEquals(2, quantidadeTotal);
        }

        @Test
        @DisplayName("getQuantidadeTotalDeProdutos() - lista de dois itens com uma e três quantidades.")
        void dadoGetQuantidadeTotalDeProdutos_QuandoSomarDoisItensComUmaAndTresQuantidades_EntaoRetornarQuatro() {
            item2.adicionarQuantidade(2);
            var carrinhoCompra = new CarrinhoCompra(cliente, List.of(item1, item2));
            var quantidadeTotal = carrinhoCompra.getQuantidadeTotalDeProdutos();
            Assertions.assertEquals(4, quantidadeTotal);
        }
    }

    @Nested
    @DisplayName("Métodos de Coleção")
    class MetodosDeColecao {

        @Test
        @DisplayName("adicionarProduto - item novo")
        void dadoProdutoValidoInédito_QuandoAdicionarProduto_EntaoRetornarListaComProdutoNovoAdicionado() {
            var listaOriginal = List.of(item1, item2);
            var carrinhoCompra = new CarrinhoCompra(cliente, listaOriginal);
            var produtoNovo = new Produto(3L, "Tela UltraWideScreen", "Dell 52 polegadas", BigDecimal.valueOf(14000));
            carrinhoCompra.adicionarProduto(produtoNovo, 1);

            var listaCopiada = carrinhoCompra.getItens();

            Assertions.assertEquals(3, listaCopiada.size());
            Assertions.assertEquals(listaOriginal.get(0).getQuantidade(), listaCopiada.get(0).getQuantidade());
            Assertions.assertEquals(listaOriginal.get(1).getQuantidade(), listaCopiada.get(1).getQuantidade());
        }

        @Test
        @DisplayName("adicionarProduto - item existente")
        void dadoProdutoValidoExistente_QuandoAdicionarProduto_EntaoRetornarListaComQuantidadeDeProdutoIncrementada() {
            var produto1 = new Produto(1L, "Notebook", "Dell XPS-9320", BigDecimal.valueOf(12000));
            var produto2 = new Produto(2L, "Mouse Optico", "Cor preta e sem fio", BigDecimal.valueOf(100));
            var item1 = new ItemCarrinhoCompra(produto1, 1);
            var item2 = new ItemCarrinhoCompra(produto2, 1);
            var listaOriginal = List.of(item1, item2);
            var carrinhoCompra = new CarrinhoCompra(cliente, listaOriginal);
            var produtoExistente = new Produto(2L, "Mouse Optico", "Cor preta e sem fio", BigDecimal.valueOf(100));
            carrinhoCompra.adicionarProduto(produtoExistente, 1);

            var listaCopiada = carrinhoCompra.getItens();

            Assertions.assertEquals(2, listaCopiada.size());
            Assertions.assertEquals(listaOriginal.get(0).getQuantidade(), listaCopiada.get(0).getQuantidade());
            Assertions.assertNotEquals(listaOriginal.get(1).getQuantidade(), listaCopiada.get(1).getQuantidade());
        }

        @Test
        @DisplayName("adicionarProduto - produto nulo")
        void dadoParametroProdutoNulo_QuandoAdicionarProduto_EntaoLancarNullPointerException() {
            var listaOriginal = List.of(item1, item2);
            var carrinhoCompra = new CarrinhoCompra(cliente, listaOriginal);

            Executable acao = () -> carrinhoCompra.adicionarProduto(null, 1);
            Assertions.assertThrows(NullPointerException.class, acao);
        }

        @Test
        @DisplayName("adicionarProduto - quantidade zerada inválida")
        void dadoParametroQuantidadeZeradaInvalida_QuandoAdicionarProduto_EntaoLancarException() {
            var listaOriginal = List.of(item1, item2);
            var carrinhoCompra = new CarrinhoCompra(cliente, listaOriginal);
            var produto = new Produto(4L, "WebCam", "Cor cinza", BigDecimal.valueOf(1400));

            Executable acao = () -> carrinhoCompra.adicionarProduto(produto, 0);
            Assertions.assertThrows(RuntimeException.class, acao);
        }

        @Test
        @DisplayName("adicionarProduto - quantidade negativa inválida")
        void dadoParametroQuantidadeNegativaInvalida_QuandoAdicionarProduto_EntaoLancarException() {
            var listaOriginal = List.of(item1, item2);
            var carrinhoCompra = new CarrinhoCompra(cliente, listaOriginal);
            var produto = new Produto(4L, "WebCam", "Cor cinza", BigDecimal.valueOf(1400));

            Executable acao = () -> carrinhoCompra.adicionarProduto(produto, -1);
            Assertions.assertThrows(RuntimeException.class, acao);
        }
    }
}

