package com.crud.srpringbootcrud.service.impl;

import com.crud.srpringbootcrud.DTO.LopHocDTO;
import com.crud.srpringbootcrud.DTO.StudentDTO;
import com.crud.srpringbootcrud.exception.ExistException;
import com.crud.srpringbootcrud.exception.NotFoundException;
import com.crud.srpringbootcrud.model.LopHoc;
import com.crud.srpringbootcrud.model.Student;
import com.crud.srpringbootcrud.repository.LopHocRepository;
import com.crud.srpringbootcrud.repository.StudentRepository;
import com.crud.srpringbootcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired private StudentRepository studentRepository;
    @Autowired private LopHocRepository lopHocRepository;
    @Override
    public List<Student> getAllStudent() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public boolean addStudent(Student student) {
        if(studentRepository.existsByIdStudent(student.getIdStudent()))
            throw new ExistException("Student is existed!!");
        else{
            studentRepository.save(student);
        }
        return true;
    }

    @Override
    public boolean deleteStudent(int idStudent) {
        if(studentRepository.findById(idStudent).isEmpty()){
            throw new NotFoundException("Student not found");
        }
        studentRepository.deleteById(idStudent);
        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setNameStudent(studentDTO.getNameStudent());
        student.setAgeStudent(studentDTO.getAgeStudent());
        LopHoc lopHoc = lopHocRepository.findById(studentDTO.getIdLop()).get();
        studentRepository.findById(studentDTO.getIdStudent()).map(student1 -> {
            student1.setNameStudent(student.getNameStudent());
            student1.setAgeStudent(student.getAgeStudent());
            student1.setLopHoc(lopHoc);
            studentRepository.save(student1);
            return true;
        }).orElseGet(() -> {
            student.setIdStudent(studentDTO.getIdStudent());
            student.setNameStudent(studentDTO.getNameStudent());
            student.setLopHoc(lopHocRepository.findById(student.getLopHoc().getIdLop()).get());
            studentRepository.save(student);
            return true;
        });
        return true;

    }

    @Override
    public Student getStudentById(int idStudent) {
        Optional<Student> result = studentRepository.findById(idStudent);
        if(result.isPresent()){
            return  result.get();
        }
        throw new NotFoundException("Could not find any student with Id = " + idStudent);
    }
}
