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
import org.springframework.web.util.UriComponentsBuilder
import org.springkotlin.springkotlin.dto.CourseDTO
import org.springkotlin.springkotlin.entity.Course
import org.springkotlin.springkotlin.repository.CourseRepository
import org.springkotlin.springkotlin.repository.InstructorRepository
import org.springkotlin.springkotlin.util.PostgreSQLContainerInitializer
import org.springkotlin.springkotlin.util.courseEntityList
import org.springkotlin.springkotlin.util.instructorEntity
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@Testcontainers
class CourseControllerIntgTest : PostgreSQLContainerInitializer() {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    @Autowired
    lateinit var instructorRepository: InstructorRepository

    /*    companion object {

            @Container
            val postgresDB = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:13-alpine")).apply {
                withDatabaseName("testdb")
                withUsername("postgres")
                withPassword("secret")
            }

            @JvmStatic
            @DynamicPropertySource
            fun properties(registry: DynamicPropertyRegistry) {
                registry.add("spring.datasource.url", postgresDB::getJdbcUrl)
                registry.add("spring.datasource.username", postgresDB::getUsername)
                registry.add("spring.datasource.password", postgresDB::getPassword)
            }
        }*/

    @BeforeEach
    fun setUp() {
        courseRepository.deleteAll()
        val instructor = instructorEntity()
        instructorRepository.save(instructor)
        val courses = courseEntityList(instructor)
        courseRepository.saveAll(courses)
    }

    @Test
    fun createCourse() {
        val instructor = instructorRepository.findAll().first()
        val courseDTO = CourseDTO(null, name = "test", category = "test", instructor.id)

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
    fun getCoursesByName() {
        val uri = UriComponentsBuilder.fromUriString("/v1/courses")
            .queryParam("name", "SpringBoot").toUriString()
        val courses = webTestClient.get().uri(uri).exchange().expectStatus().isOk.expectBodyList(
            CourseDTO::class.java
        ).returnResult().responseBody
        println("Courses = $courses")
        assertEquals(2, courses!!.size)
    }

    @Test
    fun updateCourse() {
        val instructor = instructorRepository.findAll().first()
        val courseDTO = Course(null, "Build sdflajdf", "Dev", instructor)
        courseRepository.save(courseDTO)
        val updatedCourseDTO = CourseDTO(null, "sdfsdf", "sdfsdf", courseDTO.instructor!!.id)

        val updatedCourse =
            webTestClient.put().uri("/v1/courses/{id}", courseDTO.id).bodyValue(updatedCourseDTO).exchange()
                .expectStatus().isOk.expectBody(
                    CourseDTO::class.java
                ).returnResult().responseBody
        assertEquals("sdfsdf", updatedCourse!!.name)
    }

    @Test
    fun deleteCourse() {
        val instructor = instructorRepository.findAll().first()
        val course = Course(null, "sdfsdf", "sdfsdf", instructor)
        courseRepository.save(course)
        val updatedCourseDTO =
            webTestClient.delete().uri("/v1/courses/{id}", course.id).exchange().expectStatus().isNoContent
        assertTrue(courseRepository.findById(course.id!!).isEmpty)
    }

}