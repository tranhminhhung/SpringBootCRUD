package com.crud.srpringbootcrud.repository;

import com.crud.srpringbootcrud.model.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
    Boolean existsByIdTeacher(Integer idTeacher);
}
