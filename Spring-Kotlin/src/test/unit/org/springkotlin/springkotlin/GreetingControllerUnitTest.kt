package org.springkotlin.springkotlin

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springkotlin.springkotlin.controller.GreetingController
import org.springkotlin.springkotlin.service.GreetingService

@WebMvcTest(controllers = [GreetingController::class])
@AutoConfigureWebTestClient
class GreetingControllerUnitTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var greetingService: GreetingService

    @Test
    fun getGreeting() {

        val name = "test"
        every { greetingService.greeting(name) } returns "Hello $name, Hello from the default message source"
        val result =
            webTestClient.get().uri("/v1/greetings/{name}", name).exchange().expectStatus().is2xxSuccessful.expectBody(
                String::class.java
            ).returnResult()

        Assertions.assertEquals("Hello $name, Hello from the default message source", result.responseBody)
    }
}