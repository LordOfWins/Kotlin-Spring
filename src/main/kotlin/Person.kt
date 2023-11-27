package org.springkotlin

class Person(
    val name: String = "",
    val age: Int = 0
) {

    var email: String = ""

    var nameLength: Int = 0

    init {
        println("Inside Init Block")
        nameLength = name.length
    }


    constructor(
        _email: String,
        _name: String = "",
        _age: Int = 0
    ) : this(_name, _age) {
        email = _email
    }

    fun action() {
        println("Action")
    }
}

fun main() {
//    val person = Person("seungjae", 33)
//    person.action()
//    println("Name : ${person.name} and the age is : ${person.age}")

    val person2 = Person(_email = "abc@gmail.com", "Alex", 25)
    println("Name : ${person2.name} and the age is : ${person2.age}")
    println("Email is : ${person2.email}")
    println("Length Of the name : ${person2.nameLength}")

}