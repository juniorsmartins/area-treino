package io.aluragames

class Jogo(val titulo:String, val capa:String) {

    val descricao = ""

    override fun toString(): String {
        return "\nJogo (titulo = '$titulo', \ncapa = '$capa', \ndescricao = '$descricao')"
    }
}

