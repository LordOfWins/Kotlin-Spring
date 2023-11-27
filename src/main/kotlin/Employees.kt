package org.springkotlin

data class Employee(
    val id: Int,
    val name: String,
)

fun main() {
    val employee = Employee(1, "Dilip")
    println(employee)

    val employee2 = Employee(1, "Dilip")
    println(employee2 == employee)

    val employee3 = employee.copy()
    println(employee3)
}