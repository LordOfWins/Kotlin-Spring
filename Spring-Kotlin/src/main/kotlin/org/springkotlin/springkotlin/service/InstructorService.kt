package org.springkotlin.springkotlin.service

import org.springframework.stereotype.Service
import org.springkotlin.springkotlin.dto.InstructorDTO
import org.springkotlin.springkotlin.entity.Instructor
import org.springkotlin.springkotlin.repository.InstructorRepository
import java.util.*

@Service
class InstructorService(val instructorRepository: InstructorRepository) {
    fun addInstructor(instructorDTO: InstructorDTO): Any {
        val instructorEntity = instructorDTO.let {
            Instructor(it.id, it.name)
        }

        instructorRepository.save(instructorEntity)
        return instructorEntity.let {
            InstructorDTO(it.id, it.name)
        }
    }

    fun findByInstructorId(instructorId: Int): Optional<Instructor> {
        return instructorRepository.findById(instructorId)
    }
}