package com.algaworks.junit.ecommerce;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Carrinho de Compras")
class CarrinhoCompraTest {

    @Nested
    @DisplayName("Métodos Get")
    class gets {

        private Cliente cliente;

        private ItemCarrinhoCompra item1;

        private ItemCarrinhoCompra item2;

        @BeforeEach
        void criarCenario() {
            cliente  = new Cliente(1L, "Kent Beck");
            var produto1 = new Produto(1L, "Notebook", "Dell XPS-9320", BigDecimal.valueOf(12000));
            var produto2 = new Produto(2L, "Mouse Optico", "Cor preta e sem fio", BigDecimal.valueOf(100));
            item1 = new ItemCarrinhoCompra(produto1, 1);
            item2 = new ItemCarrinhoCompra(produto2, 1);
        }

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
}

