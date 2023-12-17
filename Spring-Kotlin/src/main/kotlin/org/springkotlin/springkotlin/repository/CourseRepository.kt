package org.springkotlin.springkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springkotlin.springkotlin.entity.Course

interface CourseRepository : JpaRepository<Course, Int>