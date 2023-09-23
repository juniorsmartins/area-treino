package io.aluragames

import io.aluragames.modelo.Gamer
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
	val gamer = Gamer.criarGamer(leitura)
	println("\nCadastro concluído com sucesso! Dados do Gamer: ")
	println(gamer)

	do {
		println("\nDigite o código de jogo para buscar: ")
		val idJogo = leitura.nextLine()

		val buscaApi = ConsumoApi()
		val infoJogo = buscaApi.buscaJogo(idJogo)

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
			gamer.jogosBuscados.add(jogo)
		}

		TestGamer().testGamer()

		println("\nDeseja buscar o novo jogo? S/N")
		val resposta = leitura.nextLine()

	} while (resposta.equals("s", true))

	leitura.close()

	println("\nJogos Buscados: \n")
	println(gamer.jogosBuscados)
	println("\nBusca finalizada com sucesso!")
}

