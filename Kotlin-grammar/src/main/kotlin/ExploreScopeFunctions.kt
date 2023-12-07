package org.springkotlin

fun main() {
    //    exploreApply()
//    exploreAlso()
//    exploreLet()
//    exploreWith()
//    exploreRun()
    fun displaySubstringPosition(input: String, sub: String) {
        input.indexOf(sub).takeIf { it >= 0 }?.let {
            println("The substring $sub is found in $input.")
            println("Its start position is $it.")
        }
    }

    displaySubstringPosition("010000011", "11")
    displaySubstringPosition("010000011", "12")
}

fun exploreRun() {
    var numbers: MutableList<Int>? = null
    val result = numbers.run {
        numbers = mutableListOf(1, 2, 3)
        numbers?.sum()
    }

    println("result: $result")

    val length = run {
        val name = "Seungjae"
        println(name.uppercase())
        name.length
    }

    println("length: $length")
}

fun exploreWith() {
    val numbers = mutableListOf(1, 2, 3, 4, 5)
    val result = with(numbers) {
        /*println("Size is ${numbers.size}")
        val list = numbers.plus(6)
        list.sum()*/
        println("Size is ${size}")
        val list = plus(6)
        list.sum()
    }
    println("result : $result")
}

fun exploreLet() {
    val numbers = mutableListOf(1, 2, 3, 4, 5)
    val result = numbers.map { it * 2 }.filter { it > 5 }.let {
        println(it)
        it.sum()
    }
    println("result : $result")

    var name: String? = null
    name = "Seungjae"
    val result1 = name.let {
        println(it)
        it.uppercase()
    }
    println("result1 : $result1")
}

fun exploreApply() {
    val course = Course(
        1,
        "Design Thinking in Kotlin",
        "Dilip"
    ).apply {
        courseCategory = CourseCategory.DESIGN
    }.also {
        println("course apply also in : $it")
    }

    println("course Apply out: $course")
}

fun exploreAlso() {
    val course = Course(
        1,
        "Design Thinking in Kotlin",
        "Dilip"
    ).also {
        it.courseCategory = CourseCategory.DESIGN
        println("course also in : $it")
    }

    println("course also out: $course")
}