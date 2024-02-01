package com.crud.srpringbootcrud.service.impl;

import com.crud.srpringbootcrud.DTO.TeacherDTO;
import com.crud.srpringbootcrud.exception.ExistException;
import com.crud.srpringbootcrud.exception.NotFoundException;
import com.crud.srpringbootcrud.model.Teacher;
import com.crud.srpringbootcrud.repository.TeacherRepository;
import com.crud.srpringbootcrud.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeacher(){
        return (List<Teacher>) teacherRepository.findAll();
    }

    public boolean addTeacher(Teacher teacher){
        if(teacherRepository.existsByIdTeacher(teacher.getIdTeacher())){
            throw new ExistException("Teacher is existed");
        }
        else{
            Teacher teacher1 = teacherRepository.save(teacher);
        }
        return true;
    }
    @Override
    public Teacher getTeacherById(int idTeacher) {
        Optional<Teacher> result = teacherRepository.findById(idTeacher);
        if(result.isPresent()){
            return  result.get();
        }
        throw new NotFoundException("Could not find any teacher with Id = " + idTeacher);
    }
    @Override
    public boolean updateTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setNameTeacher(teacherDTO.getNameTeacher());
        teacherRepository.findById(teacherDTO.getIdTeacher()).map(teacher1 -> {
            teacher1.setNameTeacher(teacher.getNameTeacher());
            teacherRepository.save(teacher1);
            return true;
        }).orElseGet(() -> {
            teacher.setIdTeacher(teacherDTO.getIdTeacher());
            teacherRepository.save(teacher);
            return true;
        });
        return true;
    }

    @Override
    public boolean deleteTeacher(int idTeacher) {
        if (teacherRepository.findById(idTeacher).isEmpty()){
            throw new NotFoundException("Teacher not found");
        }
        teacherRepository.deleteById(idTeacher);
        return false;
    }

}
