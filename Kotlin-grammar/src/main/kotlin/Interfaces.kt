package org.springkotlin

interface CourseRepository {
    var isCoursePersisted: Boolean


    fun getById(id: Int): Course

    fun save(course: Course): Int {
        println("course : $course")
        return course.id
    }
}

fun interface Repository {

    fun getAll(): Any
}

class SqlCourseRepository : CourseRepository, Repository {
    override var isCoursePersisted: Boolean = true
    override fun getById(id: Int): Course {
        return Course(
            id = id,
            "Kafka For Developers using Spring Boot",
            "Dilip Sundarraj"
        )
    }

    override fun save(course: Course): Int {
        isCoursePersisted = true
        return super.save(course)
    }

    override fun getAll(): Any {
        TODO("Not yet implemented")
    }
}

class NoSqlCourseRepository : CourseRepository {
    override var isCoursePersisted: Boolean = false
    override fun getById(id: Int): Course {
        return Course(
            id = id,
            "Docker For Developers using Spring Boot",
            "Dilip Sundarraj"
        )
    }

    override fun save(course: Course): Int {
        println("course in noSqlCourseRepository : $course")
        isCoursePersisted = true
        return course.id
    }
}

interface A {
    fun doSomething() {
        println("Do Something in A")
    }
}

interface B {
    fun doSomething() {
        println("Do Something in B")
    }
}

class AB : A, B {
    override fun doSomething() {
        super<A>.doSomething()
        super<B>.doSomething()
        println("Do something in AB")
    }

}

fun main() {
    val sqlCourseRepository = SqlCourseRepository()
    println(sqlCourseRepository.isCoursePersisted)
    val course = sqlCourseRepository.getById(2)
    println(course)
    val courseId = sqlCourseRepository.save(course)
    println(courseId)

    val noSqlCourseRepository = NoSqlCourseRepository()
    val course2 = noSqlCourseRepository.getById(3)
    println(course2)
    val courseId2 = noSqlCourseRepository.save(course2)
    println(courseId2)

    val ab = AB()
    ab.doSomething()
}