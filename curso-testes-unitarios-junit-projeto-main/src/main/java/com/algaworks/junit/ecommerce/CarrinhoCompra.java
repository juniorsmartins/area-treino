package com.algaworks.junit.ecommerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class CarrinhoCompra {

	private final Cliente cliente;
	private final List<ItemCarrinhoCompra> itens;

	public CarrinhoCompra(Cliente cliente) {
		this(cliente, new ArrayList<>());
	}

	public CarrinhoCompra(Cliente cliente, List<ItemCarrinhoCompra> itens) {
		Objects.requireNonNull(cliente);
		Objects.requireNonNull(itens);
		this.cliente = cliente;
		this.itens = new ArrayList<>(itens); //Cria lista caso passem uma imutável
	}

	public List<ItemCarrinhoCompra> getItens() {
		//TODO deve retornar uma nova lista para que a antiga não seja alterada

		List<ItemCarrinhoCompra> listaCopiada = new ArrayList<>();

		this.itens.forEach(item ->
			listaCopiada.add(new ItemCarrinhoCompra(item.getProduto(), item.getQuantidade())));

		return listaCopiada;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void adicionarProduto(Produto produto, int quantidade) {
		//TODO parâmetros não podem ser nulos, deve retornar uma exception
		//TODO quantidade não pode ser menor que 1
		//TODO deve incrementar a quantidade caso o produto já exista
		if (produto == null) {
			throw new NullPointerException();
		}

		if (quantidade < 1) {
			throw new RuntimeException();
		}

		AtomicBoolean operacaoNaoRealizada = new AtomicBoolean(true);
		this.itens.forEach(item -> {
			if (item.getProduto().equals(produto)) {
				item.adicionarQuantidade(quantidade);
				operacaoNaoRealizada.set(false);
			}
		});

		if (operacaoNaoRealizada.get()) {
			var novoItem = new ItemCarrinhoCompra(produto, quantidade);
			this.itens.add(novoItem);
		}
	}

	public void removerProduto(Produto produto) {
		//TODO parâmetro não pode ser nulo, deve retornar uma exception
		//TODO caso o produto não exista, deve retornar uma exception
		//TODO deve remover o produto independente da quantidade
		if (produto == null) {
			throw new NullPointerException();
		}

		this.itens.stream()
				.filter(item -> item.getProduto().equals(produto))
				.findFirst()
				.map(itens::remove)
				.orElseThrow(RuntimeException::new);
	}

	public void aumentarQuantidadeProduto(Produto produto) {
		//TODO parâmetro não pode ser nulo, deve retornar uma exception
		//TODO caso o produto não exista, deve retornar uma exception
		//TODO deve aumentar em um quantidade do produto
		if (produto == null) {
			throw new NullPointerException();
		}

		this.itens.stream()
				.filter(item -> item.getProduto().equals(produto))
				.findFirst()
				.map(item -> {
					item.adicionarQuantidade(1);
					return item;
				})
				.orElseThrow(RuntimeException::new);
	}

    public void diminuirQuantidadeProduto(Produto produto) {
		//TODO parâmetro não pode ser nulo, deve retornar uma exception
		//TODO caso o produto não exista, deve retornar uma exception
		//TODO deve diminuir em um quantidade do produto, caso tenha apenas um produto, deve remover da lista
		if (produto == null) {
			throw new NullPointerException();
		}

		this.itens.stream()
				.filter(item -> item.getProduto().equals(produto))
				.findFirst()
				.map(item -> {
					if (item.getQuantidade() > 1) {
						item.subtrairQuantidade(1);
					} else {
						itens.remove(item);
					}
					return itens;
				})
				.orElseThrow(RuntimeException::new);
	}

    public BigDecimal getValorTotal() {
		//TODO implementar soma do valor total de todos itens

		return this.itens.stream()
				.map(item -> item.getProduto().getValor()
						.multiply(BigDecimal.valueOf(item.getQuantidade())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
    }

	public int getQuantidadeTotalDeProdutos() {
		//TODO retorna quantidade total de itens no carrinho
		//TODO Exemplo em um carrinho com 2 itens, com a quantidade 2 e 3 para cada item respectivamente, deve retornar 5

		return this.itens.stream()
				.map(ItemCarrinhoCompra::getQuantidade)
				.reduce(0, Integer::sum);
	}

	public void esvaziar() {
		//TODO deve remover todos os itens
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CarrinhoCompra that = (CarrinhoCompra) o;
		return Objects.equals(itens, that.itens) && Objects.equals(cliente, that.cliente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(itens, cliente);
	}
}