package io.aluragames.modelo

import com.google.gson.annotations.SerializedName

data class Jogo(val titulo:String, val capa:String) {

    var descricao:String? = ""

    override fun toString(): String {
        return "\n\nJogo (titulo = '$titulo', \ncapa = '$capa', \ndescricao = '$descricao')"
    }
}

