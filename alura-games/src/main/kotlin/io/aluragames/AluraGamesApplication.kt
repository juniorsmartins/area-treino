package io.aluragames

import com.google.gson.Gson
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.lang.NullPointerException

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner

@SpringBootApplication
class AluraGamesApplication

fun main(args: Array<String>) {
	runApplication<AluraGamesApplication>(*args)

	val leitura = Scanner(System.`in`)
	println("Digite o código de jogo para buscar: ")
	val busca = leitura.nextLine()
	leitura.close()

	val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"

	val client: HttpClient = HttpClient.newHttpClient()
	val request = HttpRequest.newBuilder()
		.uri(URI.create(endereco))
		.build()
	val response = client
		.send(request, BodyHandlers.ofString())

//	if (response.statusCode() != 200) {
//		println("Erro ao buscar o jogo: ${response.statusCode()}")
//		return
//	}

	val json = response.body()

//	val meuJogo = Jogo("Batman: Arkham Asylum Game of the Year Edition",
//		"https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1681938587")
//	println(meuJogo)
//
//	val novoJogo = Jogo(capa = "https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1681938587",
//		titulo = "Batman: Arkham Asylum Game of the Year Edition")
//	println(novoJogo)

	val gson = Gson()

	val resultado = runCatching {
		val infoJogo = gson.fromJson(json, InfoJogo::class.java)
		val jogo = Jogo(infoJogo.info.title, infoJogo.info.thumb)
		println(jogo)
	}

	resultado.onFailure {
		println("Jogo inexistente. Tente outro id.")
	}
}

