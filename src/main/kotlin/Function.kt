package org.springkotlin

import java.time.LocalDate

fun printName(name: String) {
    println("Name is $name")
}

fun addition(x: Int, y: Int): Int {
    return x + y
}

fun additionApproach(x: Int, y: Int) = x + y

fun defaultParameters(name: String, email: String = "", dob: LocalDate = LocalDate.now()) {

    println("Name is $name and the email is $email and the dob is $dob")
}


fun main() {
    val unit = printName("seungjae")
    println(unit)
    val result = addition(2, 3)
    println("Result is $result")
    val result2 = additionApproach(2, 3)
    println("Result is $result2")

    defaultParameters("Dilip", "abc@gmail.com", LocalDate.parse("2000-01-01"))
    defaultParameters("Dilip")
    defaultParameters("Dilip", dob = LocalDate.parse("2000-01-01"))
    defaultParameters(dob = LocalDate.parse("2000-01-01"), name = "Dilip", email = "abc@gmail.com")
}