package org.springkotlin

fun printName(name: String) {
    println("Name is $name")
}

fun addition(x: Int, y: Int): Int {
    return x + y
}

fun additionApproach(x: Int, y: Int) = x + y

fun main() {
    val unit = printName("seungjae")
    println(unit)
    val result = addition(2, 3)
    println("Result is $result")
    val result2 = additionApproach(2, 3)
    println("Result is $result2")
}