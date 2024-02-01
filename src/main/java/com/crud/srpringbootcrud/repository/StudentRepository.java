package com.crud.srpringbootcrud.repository;

import com.crud.srpringbootcrud.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Boolean existsByIdStudent(Integer idStudent);
}
