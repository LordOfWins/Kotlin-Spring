package org.springkotlin.springkotlin.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springkotlin.springkotlin.dto.CourseDTO
import org.springkotlin.springkotlin.entity.Course
import org.springkotlin.springkotlin.repository.CourseRepository
import org.springkotlin.springkotlin.util.courseEntityList

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerIntgTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp() {
        courseRepository.deleteAll()
        val courses = courseEntityList()
        courseRepository.saveAll(courses)
    }

    @Test
    fun createCourse() {
        val courseDTO = CourseDTO(null, name = "test", category = "test")

        val savedCourseDTO =
            webTestClient.post().uri("/v1/courses").bodyValue(courseDTO).exchange().expectStatus().isCreated.expectBody(
                CourseDTO::class.java
            ).returnResult().responseBody
        assertTrue {
            savedCourseDTO!!.id != null
        }
    }

    @Test
    fun getCourses() {
        val courses = webTestClient.get().uri("/v1/courses").exchange().expectStatus().is2xxSuccessful.expectBodyList(
            CourseDTO::class.java
        ).returnResult().responseBody
        assertEquals(3, courses!!.size)
    }

    @Test
    fun updateCourse() {
        val courseDTO = Course(null, "Build sdflajdf", "Dev")
        courseRepository.save(courseDTO)
        val updatedCourseDTO = CourseDTO(null, "sdfsdf", "sdfsdf")

        val updatedCourse =
            webTestClient.put().uri("/v1/courses/{id}", courseDTO.id).bodyValue(updatedCourseDTO).exchange()
                .expectStatus().isOk.expectBody(
                    CourseDTO::class.java
                ).returnResult().responseBody
        assertEquals("sdfsdf", updatedCourse!!.name)
    }

    @Test
    fun deleteCourse() {
        val course = Course(null, "sdfsdf", "sdfsdf")
        courseRepository.save(course)
        val updatedCourseDTO =
            webTestClient.delete().uri("/v1/courses/{id}", course.id).exchange().expectStatus().isNoContent
        assertTrue(courseRepository.findById(course.id!!).isEmpty)
    }

}