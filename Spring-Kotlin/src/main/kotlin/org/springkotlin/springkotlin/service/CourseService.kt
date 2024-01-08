package org.springkotlin.springkotlin.service

import mu.KLogging
import org.springframework.stereotype.Service
import org.springkotlin.springkotlin.dto.CourseDTO
import org.springkotlin.springkotlin.entity.Course
import org.springkotlin.springkotlin.exception.CourseNotFoundException
import org.springkotlin.springkotlin.repository.CourseRepository

@Service
class CourseService(val courseRepository: CourseRepository, val instructorService: InstructorService) {

    companion object : KLogging()

    fun addCourse(courseDTO: CourseDTO): CourseDTO {

        val instructor = instructorService.findByInstructorId(courseDTO.instructorId!!)
        if (!instructor.isPresent) {
            throw InstructorNotValidException("Instructor not found with id: ${courseDTO.instructorId}")
        }
        val course = courseDTO.let {
            Course(id = null, name = it.name, category = it.category, instructor.get())
        }
        courseRepository.save(course)

//        logger.info("Course saved: $course")
        return course.let {
            CourseDTO(id = it.id, name = it.name, category = it.category, it.instructor!!.id)
        }
    }


    fun getCourses(name: String?): List<CourseDTO> {
        val course = name?.let {
            courseRepository.findCoursesByName(name)
        } ?: courseRepository.findAll()

        return course.map { CourseDTO(id = it.id, name = it.name, category = it.category) }
    }

    fun updateCourse(id: Int, courseDTO: CourseDTO): CourseDTO {
        val course = courseRepository.findById(id)
        return if (course.isPresent) {
            course.get().let {
                it.name = courseDTO.name
                it.category = courseDTO.category
                courseRepository.save(it)
                CourseDTO(id = it.id, name = it.name, category = it.category)
            }
        } else {
            throw CourseNotFoundException("Course not found with id: $id")
        }
    }

    fun deleteCourse(id: Int) {
        val course = courseRepository.findById(id)
        return if (course.isPresent) {
            course.get().let {
                courseRepository.deleteById(id)
            }
        } else {
            throw CourseNotFoundException("Course not found with id: $id")
        }
    }

    fun updateName(id: Int, name: String): CourseDTO {
        val course = courseRepository.findById(id)
        return if (course.isPresent) {
            course.get().run {
                this.name = name
                courseRepository.save(this)
                CourseDTO(id = this.id, name = this.name, category = this.category)
            }
        } else {
            throw CourseNotFoundException("Course not found with id: $id")
        }
    }
}