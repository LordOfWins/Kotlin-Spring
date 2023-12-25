package org.springkotlin.springkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springkotlin.springkotlin.entity.Course

interface CourseRepository : JpaRepository<Course, Int> {
    fun findByNameContaining(courseName: String): List<Course>

    @Query(value = "SELECT * FROM COURSES WHERE name like %?1%", nativeQuery = true)
    fun findCoursesByName(courseName: String): List<Course>
}