package io.aluragames

import com.google.gson.Gson
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

@SpringBootApplication
class AluraGamesApplication

fun main(args: Array<String>) {
	runApplication<AluraGamesApplication>(*args)

	val client: HttpClient = HttpClient.newHttpClient()
	val request = HttpRequest.newBuilder()
		.uri(URI.create("https://www.cheapshark.com/api/1.0/games?id=146"))
		.build()
	val response = client
		.send(request, BodyHandlers.ofString())

	val json = response.body()
	println(json)

	val meuJogo = Jogo("Batman: Arkham Asylum Game of the Year Edition",
		"https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1681938587")
	println(meuJogo)

	val novoJogo = Jogo(capa = "https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1681938587",
		titulo = "Batman: Arkham Asylum Game of the Year Edition")
	println(novoJogo)

	val gson = Gson()
	val infoJogo = gson.fromJson(json, InfoJogo::class.java)
	println("\n" + infoJogo)

	val jogo = Jogo(infoJogo.info.title, infoJogo.info.thumb)
	println(jogo)
}

