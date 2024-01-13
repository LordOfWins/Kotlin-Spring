package org.springkotlin.springkotlin.util

import org.springkotlin.springkotlin.dto.CourseDTO
import org.springkotlin.springkotlin.entity.Course
import org.springkotlin.springkotlin.entity.Instructor

fun courseEntityList() = listOf(
    Course(
        null,
        "Build RestFul APis using SpringBoot and Kotlin", "Development"
    ),
    Course(
        null,
        "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development",
    ),
    Course(
        null,
        "Wiremock for Java Developers", "Development",
    )
)

fun courseDTO(
    id: Int? = null,
    name: String = "Build RestFul APis using Spring Boot and Kotlin",
    category: String = "Development",
//    instructorId: Int? = 1
) = CourseDTO(
    id,
    name,
    category,
//    instructorId
)

fun instructorEntityList() = listOf(
    Instructor(
        null, "James"
    ),
    Instructor(
        null, "Tim"
    ),
    Instructor(
        null, "John"
    )

)

fun courseEntityList(instructor: Instructor? = null) = listOf(
    Course(
        null,
        "Build RestFul APis using SpringBoot and Kotlin", "Development",
        instructor
    ),
    Course(
        null,
        "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development", instructor
    ),
    Course(
        null,
        "Wiremock for Java Developers", "Development",
        instructor
    )
)

fun instructorEntity(name: String = "Dilip Sundarraj") = Instructor(null, name)