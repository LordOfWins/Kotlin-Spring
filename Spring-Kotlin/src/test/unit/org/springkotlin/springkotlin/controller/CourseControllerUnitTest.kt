package org.springkotlin.springkotlin.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springkotlin.springkotlin.dto.CourseDTO
import org.springkotlin.springkotlin.service.CourseService
import org.springkotlin.springkotlin.util.courseDTO

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerUnitTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    private lateinit var courseService: CourseService

    @Test
    fun addCourse() {
        //given
        val courseDTO = CourseDTO(null, name = "test", category = "test")
        every { courseService.addCourse(any()) } returns courseDTO(id = 1)
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
        every { courseService.getCourses(any()) }.returnsMany(
            listOf(
                courseDTO(id = 1),
                courseDTO(id = 2),
                courseDTO(id = 3)
            )
        )
        val courses = webTestClient.get().uri("/v1/courses").exchange().expectStatus().is2xxSuccessful.expectBodyList(
            CourseDTO::class.java
        ).returnResult().responseBody
        assertEquals(3, courses!!.size)
    }

    @Test
    fun validation() {
        val courseDTO = CourseDTO(null, name = "", category = "")
        every { courseService.addCourse(any()) } returns courseDTO(id = 1)
        val response =
            webTestClient.post().uri("/v1/courses").bodyValue(courseDTO).exchange()
                .expectStatus().isBadRequest.expectBody(String::class.java).returnResult().responseBody
        assertEquals("Name cannot be blank, category cannot be blank", response)
    }

    @Test
    fun addCourseRuntimeException() {
        val courseDTO = CourseDTO(null, name = "test", category = "test")
        val errorMessage = "Unexpected error occurred while processing request"
        every { courseService.addCourse(any()) } throws RuntimeException(errorMessage)
        val response =
            webTestClient.post().uri("/v1/courses").bodyValue(courseDTO).exchange()
                .expectStatus().is5xxServerError.expectBody(String::class.java).returnResult().responseBody
        assertEquals(errorMessage, response)
    }

    @Test
    fun updateCourse() {
        every { courseService.updateCourse(any(), any()) } returns courseDTO(
            id = 100,
            name = "sdfsdf",
            category = "sdfsdf"
        )
        val updatedCourseDTO = CourseDTO(null, "sdfsdf", "sdfsdf")

        val updatedCourse =
            webTestClient.put().uri("/v1/courses/{id}", 100).bodyValue(updatedCourseDTO).exchange()
                .expectStatus().isOk.expectBody(
                    CourseDTO::class.java
                ).returnResult().responseBody
        assertEquals("sdfsdf", updatedCourse!!.name)
    }

    @Test
    fun deleteCourse() {
        every { courseService.deleteCourse(any()) } just runs
        webTestClient.delete().uri("/v1/courses/{id}", 100).exchange().expectStatus().isNoContent
    }

}