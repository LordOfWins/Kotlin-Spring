package org.springkotlin.springkotlin.controller

import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springkotlin.springkotlin.service.GreetingService

//@RestController
@RequestMapping("/v1/greetings")
class GreetingController(val greetingService: GreetingService) {

    companion object : KLogging()

    @GetMapping("/{name}")
    fun getGreeting(@PathVariable("name") name: String): String {
//        return "Hello $name"
        logger().info("Hello $name")
        return greetingService.greeting(name)
    }
}