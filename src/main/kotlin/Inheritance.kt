package org.springkotlin

open class User(
    val name: String
) {
    open val isLogin: Boolean = false
    open fun login() {
        println("Inside Login")
    }
}

class Student(name: String) : User(name) {
    override var isLogin: Boolean = true

    companion object {
        const val noOfEnrolledCourses = 10
        fun country() = "India"
    }

    override fun login() {
        println("Inside user login")
        super.login()
    }
}

class Instructor(name: String) : User(name)

fun main() {
    val student = Student("seungjae")
    println(student.name)
    student.login()
    student.isLogin = true
    println(student.isLogin)

    val country = Student.country()
    println(country)
    println(Student.noOfEnrolledCourses)

    val instructor = Instructor("Seungjae")
    println(instructor.name)
    instructor.login()
}