package org.springkotlin

const val courseName = "Kotlin Programming"

fun topLevelFunction(): Int {
    return (0..100).random()
}

fun main() {
    val num = topLevelFunction()
    println("num :  $num")
}