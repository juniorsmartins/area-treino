package io.aluragames

import io.aluragames.modelo.Gamer

class TestGamer {

    fun testGamer() {
        val gamer1 = Gamer("Martin Fowler", "fowler@email.com")
        println(gamer1)

        val gamer2 = Gamer("Olga Weiss",
            "weiss@email.com",
            "19/12/2000",
            "weiss")
        println(gamer2)
    }
}

