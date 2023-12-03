package org.springkotlin

fun main() {
    val course = Course(
        1,
        "Reactive Programming in Modern Java using Project Reactor",
        "DILIP"
    )
    checkType(course)
    checkType("SEUNGJAE")

    castNumber(1.0)
    castNumber(1)

    val number = 1
    val numberDouble = number.toDouble()
    println(numberDouble)
}

fun castNumber(any: Any) {
    when (any) {
        any as? Double -> {
            println(any)
        }

        any as Int -> {
            println(any)
        }
    }
}

fun checkType(type: Any) {
    when {
        type is Course -> {
            println(type.copy())
        }

        type is String -> {
            println(type.lowercase())
        }
    }
}