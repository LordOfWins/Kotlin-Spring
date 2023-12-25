package org.springkotlin.springkotlin.dto

import jakarta.validation.constraints.NotBlank

data class CourseDTO(
    val id: Int?,
    @get:NotBlank(message = "Name cannot be blank")
    val name: String,
    @get:NotBlank(message = "category cannot be blank")
    val category: String
)