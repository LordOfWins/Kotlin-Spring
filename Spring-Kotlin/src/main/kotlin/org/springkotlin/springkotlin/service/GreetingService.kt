package org.springkotlin.springkotlin.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GreetingService {

    @Value("\${message}")
    lateinit var message: String
    fun greeting(name: String) = "Hello $name, $message"
}