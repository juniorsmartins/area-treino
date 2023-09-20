package io.aluragames.modelo

import kotlin.random.Random

data class Gamer(var nome:String, var email:String) {
    var dataNascimento:String? = null
    var usuario:String? = null
    var idInterno:String? = null

    constructor(nome: String, email: String, dataNascimento: String, usuario: String):
            this(nome, email) {
                this.dataNascimento = dataNascimento
                this.usuario = usuario
                this.criarIdInterno()
            }

    override fun toString(): String {
        return "\nGamer (nome = '$nome', " +
                "\nemail = '$email', " +
                "\ndataNascimento = $dataNascimento, " +
                "\nusuario = $usuario, " +
                "\nidInterno = $idInterno)"
    }

    fun criarIdInterno() {
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)

        idInterno = "$usuario#$tag"
    }
}

