package org.springkotlin

fun calculate(x: Int, y: Int, op: (x: Int, y: Int) -> Int): Int {
    return op(x, y)
}

fun main() {
    val addLambda = { x: Int -> x + x }
    val result = addLambda(3)
    println(result)

    val multiplyLambda = { x: Int, y: Int ->
        println("x is $x and y is $y")
        x * y
    }
    val multiplyResult = multiplyLambda(3, 4)
    println(multiplyResult)

    val multiply = calculate(3, 4) { a, b -> a * b }
    println("multiply: $multiply")

    val addition = calculate(2, 3) { a, b -> a + b }
    println("addition: $addition")


}