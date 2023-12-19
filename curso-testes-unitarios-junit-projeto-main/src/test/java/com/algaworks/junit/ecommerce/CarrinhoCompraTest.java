package com.algaworks.junit.ecommerce;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.List;

@DisplayName("Carrinho de Compras")
class CarrinhoCompraTest {

    private Produto produto2;

    private ItemCarrinhoCompra item2;

    private List<ItemCarrinhoCompra> listaOriginal;

    private CarrinhoCompra carrinhoCompra;

    @BeforeEach
    void criarCenarioGlobal() {
        var cliente  = new Cliente(1L, "Kent Beck");
        var produto1 = new Produto(1L, "Notebook", "Dell XPS-9320", BigDecimal.valueOf(12000));
        produto2 = new Produto(2L, "Mouse Optico", "Cor preta e sem fio", BigDecimal.valueOf(100));
        var item1 = new ItemCarrinhoCompra(produto1, 1);
        item2 = new ItemCarrinhoCompra(produto2, 1);
        listaOriginal = List.of(item1, item2);
        carrinhoCompra = new CarrinhoCompra(cliente, listaOriginal);
    }

    @Nested
    @DisplayName("Métodos Get")
    class MetodosGet {

        @Test
        @DisplayName("getItens() - retornar cópia da lista de itens.")
        void dadoGetItens_QuandoConsultarItens_EntaoRetornarCopiaDeListaDeItens() {
            var listaCopiada = carrinhoCompra.getItens();
            Assertions.assertEquals(listaOriginal.get(0).getQuantidade(), listaCopiada.get(0).getQuantidade());
            listaCopiada.get(0).adicionarQuantidade(1);
            Assertions.assertNotEquals(listaOriginal.get(0).getQuantidade(), listaCopiada.get(0).getQuantidade());
        }

        @Test
        @DisplayName("getTotalValor() - lista de dois itens com uma quantidade cada.")
        void dadoGetTotalValor_QuandoConsultarDoisItensComUmaQuantidadeCada_EntaoRetornar12100() {
            var valorTotal = carrinhoCompra.getValorTotal();
            Assertions.assertEquals(BigDecimal.valueOf(12100), valorTotal);
        }

        @Test
        @DisplayName("getTotalValor() - lista de dois itens com uma e três quantidades.")
        void dadoGetValorTotal_QuandoConsultarDoisItensComUmaAndTresQuantidades_EntaoRetornarBigDecimalCom12300() {
            item2.adicionarQuantidade(2); // Acrescentou dois mouses - agora são três por 100 reais cada
            var valorTotal = carrinhoCompra.getValorTotal();
            Assertions.assertEquals(BigDecimal.valueOf(12300), valorTotal);
        }

        @Test
        @DisplayName("getQuantidadeTotalDeProdutos() - lista de dois itens com uma quantidade cada.")
        void dadoGetQuantidadeTotalDeProdutos_QuandoSomarDoisItensComUmaQuantidadeCada_EntaoRetornarDois() {
            var quantidadeTotal = carrinhoCompra.getQuantidadeTotalDeProdutos();
            Assertions.assertEquals(2, quantidadeTotal);
        }

        @Test
        @DisplayName("getQuantidadeTotalDeProdutos() - lista de dois itens com uma e três quantidades.")
        void dadoGetQuantidadeTotalDeProdutos_QuandoSomarDoisItensComUmaAndTresQuantidades_EntaoRetornarQuatro() {
            item2.adicionarQuantidade(2);
            var quantidadeTotal = carrinhoCompra.getQuantidadeTotalDeProdutos();
            Assertions.assertEquals(4, quantidadeTotal);
        }
    }

    @Nested
    @DisplayName("Método Adicionar")
    class MetodoAdicionar {

        @Test
        @DisplayName("adicionarProduto - item novo")
        void dadoProdutoValidoInédito_QuandoAdicionarProduto_EntaoRetornarListaComProdutoNovoAdicionado() {
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
            var produtoExistente = new Produto(2L, "Mouse Optico", "Cor preta e sem fio", BigDecimal.valueOf(100));
            carrinhoCompra.adicionarProduto(produtoExistente, 2);

            var listaCopiada = carrinhoCompra.getItens();

            Assertions.assertEquals(2, listaCopiada.size());
            Assertions.assertEquals(listaOriginal.get(0).getQuantidade(), listaCopiada.get(0).getQuantidade());
            Assertions.assertEquals(3, listaCopiada.get(1).getQuantidade());
        }

        @Test
        @DisplayName("adicionarProduto - produto nulo")
        void dadoParametroProdutoNulo_QuandoAdicionarProduto_EntaoLancarNullPointerException() {
            Executable acao = () -> carrinhoCompra.adicionarProduto(null, 1);
            Assertions.assertThrows(NullPointerException.class, acao);
        }

        @Test
        @DisplayName("adicionarProduto - quantidade zerada inválida")
        void dadoParametroQuantidadeZeradaInvalida_QuandoAdicionarProduto_EntaoLancarException() {
            var produto = new Produto(4L, "WebCam", "Cor cinza", BigDecimal.valueOf(1400));
            Executable acao = () -> carrinhoCompra.adicionarProduto(produto, 0);
            Assertions.assertThrows(RuntimeException.class, acao);
        }

        @Test
        @DisplayName("adicionarProduto - quantidade negativa inválida")
        void dadoParametroQuantidadeNegativaInvalida_QuandoAdicionarProduto_EntaoLancarException() {
            var produto = new Produto(4L, "WebCam", "Cor cinza", BigDecimal.valueOf(1400));
            Executable acao = () -> carrinhoCompra.adicionarProduto(produto, -1);
            Assertions.assertThrows(RuntimeException.class, acao);
        }
    }

    @Nested
    @DisplayName("Método Remover")
    class MetodoRemover {

        @Test
        @DisplayName("removerProduto - subtrair produto de lista original.")
        void dadoParametroProdutoValido_QuandoChamarRemoverEmListaComDoisProdutos_EntaoRetornarListaComUmProduto() {
            carrinhoCompra.removerProduto(produto2);
            var listaCopiada = carrinhoCompra.getItens();
            Assertions.assertEquals(1, listaCopiada.size());
        }

        @Test
        @DisplayName("removerProduto - subtrair produto da lista original, mesmo com muita quantidade.")
        void dadoParametroProdutoValido_QuandoChamarRemoverEmListaComDoisProdutos_EntaoRetornarListaComUmProdutoMesmoComMuitaQuantidadeDaqueleProduto() {
            item2.adicionarQuantidade(10);
            carrinhoCompra.removerProduto(produto2);
            var listaCopiada = carrinhoCompra.getItens();
            Assertions.assertEquals(1, listaCopiada.size());
        }

        @Test
        @DisplayName("removerProduto - lançar exceção por produto nulo.")
        void dadoParametroProdutoNulo_QuandoChamarRemover_EntaoLancarNullPointerException() {
            Executable acao = () -> carrinhoCompra.removerProduto(null);
            Assertions.assertThrows(NullPointerException.class, acao);
        }

        @Test
        @DisplayName("removerProduto - lançar exceção por produto inexistente na lista.")
        void dadoParametroProdutoInexistenteNaLista_QuandoChamarRemoverEmListaComDoisProdutos_EntaoLancarRuntimeException() {
            var produtoInexistente = new Produto(6L, "Teclado", "Modelo Gamer 1234", BigDecimal.valueOf(350));
            Executable acao = () -> carrinhoCompra.removerProduto(produtoInexistente);
            Assertions.assertThrows(RuntimeException.class, acao);
        }
    }

    @Nested
    @DisplayName("Método AumentarQuantidadeProduto")
    class AumentarQuantidadeProduto {

        @Test
        @DisplayName("aumentarQuantidadeProduto - aumentar quantidade de produto na lista.")
        void dadoProdutoValido_QuandoAumentarQuantidadeDeProdutoExistente_EntaoRetornarListaComDoisItensMaisAcrescimoDeUmaQuantidade() {
            carrinhoCompra.aumentarQuantidadeProduto(produto2);
            var listaCopiada = carrinhoCompra.getItens();
            Assertions.assertEquals(2, listaCopiada.get(1).getQuantidade());
        }

        @Test
        @DisplayName("aumentarQuantidadeProduto - exception por produto nulo.")
        void dadoProdutoNulo_QuandoAumentarQuantidadeDeProduto_EntaoLancarNullPointerException() {
            Executable acao = () -> carrinhoCompra.aumentarQuantidadeProduto(null);
            Assertions.assertThrows(NullPointerException.class, acao);
        }

        @Test
        @DisplayName("aumentarQuantidadeProduto - exception por produto inexistente.")
        void dadoProdutoInexistente_QuandoAumentarQuantidadeDeProduto_EntaoLancarRuntimeException() {
            var produtoInexistente = new Produto(7L, "No-Break", "Modelo 24 horas", BigDecimal.valueOf(4350));
            Executable acao = () -> carrinhoCompra.aumentarQuantidadeProduto(produtoInexistente);
            Assertions.assertThrows(RuntimeException.class, acao);
        }
    }

    @Nested
    @DisplayName("Método DiminuirQuantidadeProduto")
    class DiminuirQuantidadeProduto {

        @Test
        @DisplayName("diminuirQuantidadeProduto - diminuir item da lista.")
        void dadoProdutoValido_QuandoDiminuirQuantidadeDoProdutoComUmItem_EntaoRetornarListaComUmItem() {
            carrinhoCompra.diminuirQuantidadeProduto(produto2);
            var listaCopiada = carrinhoCompra.getItens();
            Assertions.assertEquals(1, listaCopiada.size());
        }

        @Test
        @DisplayName("diminuirQuantidadeProduto - diminuir quantidade do item da lista.")
        void dadoProdutoValido_QuandoDiminuirQuantidadeDoProdutoComDoisItens_EntaoRetornarListaComDoisItensMasQuantidadeUm() {
            item2.adicionarQuantidade(1);
            carrinhoCompra.diminuirQuantidadeProduto(produto2);
            var listaCopiada = carrinhoCompra.getItens();
            Assertions.assertEquals(2, listaCopiada.size());
            Assertions.assertEquals(1, listaCopiada.get(0).getQuantidade());
            Assertions.assertEquals(1, listaCopiada.get(1).getQuantidade());
        }

        @Test
        @DisplayName("diminuirQuantidadeProduto - lançar exceção por produto nulo.")
        void dadoProdutoNulo_QuandoDininuirQuantidadeDoProduto_EntaoLancarNullPointerException() {
            Executable acao = () -> carrinhoCompra.diminuirQuantidadeProduto(null);
            Assertions.assertThrows(NullPointerException.class, acao);
        }

        @Test
        @DisplayName("diminuirQuantidadeProduto - lançar exceção por produto inexistente.")
        void dadoProdutoInexistente_QuandoDiminuirQuantidadeDoProduto_EntaoLancarRuntimeException() {
            var produtoInexistente = new Produto(8L, "Filtro", "Modelo com três fusis", BigDecimal.valueOf(450));
            Executable acao = () -> carrinhoCompra.diminuirQuantidadeProduto(produtoInexistente);
            Assertions.assertThrows(RuntimeException.class, acao);
        }
    }
}

