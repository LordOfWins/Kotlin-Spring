package org.springkotlin.springkotlin.repository

import org.springframework.data.repository.CrudRepository
import org.springkotlin.springkotlin.entity.Instructor

interface InstructorRepository : CrudRepository<Instructor, Int>