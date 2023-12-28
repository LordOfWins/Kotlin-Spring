package org.springkotlin.springkotlin.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springkotlin.springkotlin.dto.CourseDTO
import org.springkotlin.springkotlin.service.CourseService


@RestController
@RequestMapping("/v1/courses")
@Validated
class CourseController(val courseService: CourseService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCourse(@RequestBody @Valid courseDTO: CourseDTO): CourseDTO {
        return courseService.addCourse(courseDTO)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCourses(@RequestParam(value = "name", required = false) name: String?) = courseService.getCourses(name)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCourse(@PathVariable id: Int, @Valid @RequestBody courseDTO: CourseDTO): CourseDTO {
        return courseService.updateCourse(id, courseDTO)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun patchName(@PathVariable id: Int, @RequestBody courseDTO: CourseDTO): CourseDTO {
        return courseService.updateName(id, courseDTO.name)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable id: Int) {
        courseService.deleteCourse(id)
    }
}