package com.crud.srpringbootcrud.service;

import com.crud.srpringbootcrud.DTO.StudentDTO;
import com.crud.srpringbootcrud.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();
    boolean addStudent(Student student);
    boolean deleteStudent(int idStudent);
    boolean updateStudent(StudentDTO studentDTO);
    Student getStudentById(int idStudent);
}
