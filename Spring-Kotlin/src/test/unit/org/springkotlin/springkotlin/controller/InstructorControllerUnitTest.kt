package org.springkotlin.springkotlin.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springkotlin.springkotlin.dto.InstructorDTO
import org.springkotlin.springkotlin.service.InstructorService

@WebMvcTest(InstructorController::class)
@AutoConfigureWebTestClient
class InstructorControllerUnitTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    private lateinit var instructorService: InstructorService

    @Test
    fun addInstructor() {
        //given
        val instructorDTO = InstructorDTO(null, name = "test")
        every { instructorService.addInstructor(any()) } returns InstructorDTO(id = 1, name = "Dilip Sundarraj")
        val savedInstructorDTO =
            webTestClient.post().uri("/v1/instructors").bodyValue(instructorDTO).exchange()
                .expectStatus().isCreated.expectBody(
                    InstructorDTO::class.java
                ).returnResult().responseBody
        Assertions.assertTrue {
            savedInstructorDTO!!.id != null
        }
    }


    @Test
    fun validation() {
        //given
        val instructorDTO = InstructorDTO(null, "")

        every { instructorService.addInstructor(any()) } returns InstructorDTO(1, "Dilip Sundarraj")
        val response = webTestClient
            .post()
            .uri("/v1/instructors")
            .bodyValue(instructorDTO)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        //then
        assertEquals("instructorDTO.name must not be blank", response)
    }
}