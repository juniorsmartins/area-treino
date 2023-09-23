package io.aluragames.modelo

import kotlin.random.Random

data class Gamer(var nome:String, var email:String) {

    var dataNascimento:String? = null

    var usuario:String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank()) {
                this.criarIdInterno()
            }
        }

    var idInterno:String? = null
        private set

    constructor(nome: String, email: String, dataNascimento: String, usuario: String):
            this(nome, email) {
                this.dataNascimento = dataNascimento
                this.usuario = usuario
                this.criarIdInterno()
            }

    init {
        this.email = validarEmail()
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

    fun validarEmail(): String{
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
        if(regex.matches(email)){
            return email
        } else{
            throw IllegalArgumentException("Email inválido")
        }
    }
}

