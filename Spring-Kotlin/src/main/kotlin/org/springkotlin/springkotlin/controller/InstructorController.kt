package org.springkotlin.springkotlin.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springkotlin.springkotlin.dto.InstructorDTO
import org.springkotlin.springkotlin.service.InstructorService

@RestController
@RequestMapping("/v1/instructors")
@Validated
class InstructorController(val instructorService: InstructorService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addInstructor(@Valid @RequestBody instructorDTO: InstructorDTO) = instructorService.addInstructor(instructorDTO)
}