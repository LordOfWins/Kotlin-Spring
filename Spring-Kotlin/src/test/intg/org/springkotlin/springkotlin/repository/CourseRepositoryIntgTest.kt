package org.springkotlin.springkotlin.repository

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springkotlin.springkotlin.util.PostgreSQLContainerInitializer
import org.springkotlin.springkotlin.util.courseEntityList
import org.springkotlin.springkotlin.util.instructorEntity
import java.util.stream.Stream

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryIntgTest : PostgreSQLContainerInitializer() {

    @Autowired
    lateinit var courseRepository: CourseRepository

    @Autowired
    lateinit var instructorRepository: InstructorRepository

    @BeforeEach
    fun setUp() {

        courseRepository.deleteAll()
        val instructor = instructorEntity()
        instructorRepository.save(instructor)
        val courses = courseEntityList(instructor)
        courseRepository.saveAll(courses)
    }

    @Test
    fun findByNameContaining() {
        val courses = courseRepository.findByNameContaining("SpringBoot")
        println("courses: $courses")
        Assertions.assertEquals(
            2, courses.size
        )
    }

    @Test
    fun findCourseByName() {
        val courses = courseRepository.findByNameContaining("SpringBoot")
        println("courses: $courses")
        Assertions.assertEquals(
            2, courses.size
        )
    }

    @ParameterizedTest
    @MethodSource("courseAndSize")
    fun findCourseByName2(name: String, expectedSize: Int) {
        val courses = courseRepository.findCoursesByName(name)
        println("courses : $courses")
        Assertions.assertEquals(expectedSize, courses.size)
    }

    companion object {
        @JvmStatic
        fun courseAndSize(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("SpringBoot", 2),
                Arguments.arguments("Wiremock", 1)
            )

        }
    }

}