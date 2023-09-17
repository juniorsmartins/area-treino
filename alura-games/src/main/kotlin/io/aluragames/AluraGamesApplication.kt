package io.aluragames

import io.aluragames.modelo.Jogo
import io.aluragames.servicos.ConsumoApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class AluraGamesApplication

fun main(args: Array<String>) {
	runApplication<AluraGamesApplication>(*args)

	val leitura = Scanner(System.`in`)
	println("Digite o código de jogo para buscar: ")
	val idJogo = leitura.nextLine()

	val buscaApi = ConsumoApi()
	var infoJogo = buscaApi.buscaJogo(idJogo)

	var jogo: Jogo? = null

	val resultado = runCatching {
		jogo = Jogo(infoJogo.info.title, infoJogo.info.thumb)
	}

	resultado.onFailure {
		println("Jogo inexistente. Tente outro id.")
	}

	resultado.onSuccess {
		println("Deseja inserir descrição personalizada? S/N")
		val opcao = leitura.nextLine()
		if (opcao.equals("s", true)) {
			println("Insira a descrição personalizada: ")
			var descricaoPersonalizada = leitura.nextLine()
			jogo?.descricao = descricaoPersonalizada;
		} else {
			jogo?.descricao = jogo?.titulo
		}

		leitura.close()
		println(jogo)
	}

	resultado.onSuccess {
		println("Busca finalizada com sucesso!")
	}
}

