package org.springkotlin.springkotlin.controller

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springkotlin.springkotlin.dto.InstructorDTO
import org.springkotlin.springkotlin.repository.InstructorRepository
import org.springkotlin.springkotlin.util.instructorEntityList

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class InstructorControllerIntgTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var instructorRepository: InstructorRepository

    @BeforeEach
    fun setUp() {
        instructorRepository.deleteAll()
        val instructors = instructorEntityList()
        instructorRepository.saveAll(instructors)
    }

    @Test
    fun createInstructor() {
        val instructorDTO = InstructorDTO(null, name = "test")

        val savedInstructorDTO =
            webTestClient.post().uri("/v1/instructors").bodyValue(instructorDTO).exchange()
                .expectStatus().isCreated.expectBody(
                    InstructorDTO::class.java
                ).returnResult().responseBody
        Assertions.assertTrue {
            savedInstructorDTO!!.id != null
        }
    }
}