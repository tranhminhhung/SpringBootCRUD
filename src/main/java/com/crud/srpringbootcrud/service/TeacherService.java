package com.crud.srpringbootcrud.service;

import com.crud.srpringbootcrud.DTO.TeacherDTO;
import com.crud.srpringbootcrud.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeacher();

    boolean addTeacher(Teacher teacher);
    boolean updateTeacher(TeacherDTO teacherDTO);
    boolean deleteTeacher(int idTeacher);

    Teacher getTeacherById(int idTeacher);
}
