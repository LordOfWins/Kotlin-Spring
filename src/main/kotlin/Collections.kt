package org.springkotlin

fun main() {
    val names = listOf("Alex", "Ben", "Chloe")
    println("names : $names")

    val namesMutableList = mutableListOf("Alex", "Ben", "Chloe")
    namesMutableList.add("Adam")
    println("namesMutableList : $namesMutableList")

    val set = setOf("adam", "ben", "chloe")
    println("set: $set")

    val mutableSet = mutableSetOf("adam", "ben", "chloe")
    mutableSet.add("adam1")
    println("mutableSet :  $mutableSet")

    val mapOf = mapOf("adam" to 1, "ben" to 2, "chloe" to 3)
    println("mapOf : $mapOf")

    val mutableMapOf = mutableMapOf("adam" to 1, "ben" to 2, "chloe" to 3)
    println("mutableMapOf : $mutableMapOf")
    mutableMapOf["adam1"] = 4
    println("mutableMapOf : $mutableMapOf")
}