package org.springkotlin

object Authenticate {
    fun authenticate(userName: String, password: String) {
        println("User Authenticated for userName : $userName")
    }
}

fun main() {
    Authenticate.authenticate("Seungjae", "abc")
}