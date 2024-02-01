package com.crud.srpringbootcrud.controller;

import com.crud.srpringbootcrud.DTO.LopHocDTO;
import com.crud.srpringbootcrud.DTO.StudentDTO;
import com.crud.srpringbootcrud.model.LopHoc;
import com.crud.srpringbootcrud.model.Student;
import com.crud.srpringbootcrud.service.impl.LopHocServiceImpl;
import com.crud.srpringbootcrud.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {
    @Autowired private StudentServiceImpl studentService;
    @Autowired private LopHocServiceImpl lopHocService;

    @GetMapping("/student")
    public String getAllStudent(Model model){
        model.addAttribute("student", studentService.getAllStudent());
        return "student";
    }

    @GetMapping("/student/new")
    public String showFormNewStudent(Model model){
        model.addAttribute("student", new StudentDTO());
        model.addAttribute("lop_hoc", lopHocService.getAllLopHoc());
        return "student_new";
    }

    @PostMapping("/student/saveStudent")
    public String saveStudent(Student student, RedirectAttributes redirectAttributes){
        if(studentService.addStudent(student)){
            redirectAttributes.addFlashAttribute("message", "Add new student successfully!!!");
            return "redirect:/student";
        }
        redirectAttributes.addFlashAttribute("message", "Save Failure!!!");
        return "student_new";
    }

    @GetMapping("/student/delete/{idStudent}")
    public String deleteStudent(@PathVariable int idStudent, RedirectAttributes redirectAttributes){
        studentService.deleteStudent(idStudent);
        redirectAttributes.addFlashAttribute("message", "Delete Success");
        return "redirect:/student";
    }

    @GetMapping("/student/edit/{idStudent}")
    public String showEditForm(@PathVariable("idStudent") int idStudent, Model model){
        Student student = studentService.getStudentById(idStudent);
        model.addAttribute("student", student);
        model.addAttribute("lop_hoc", lopHocService.getAllLopHoc());
        model.addAttribute("pageTitle", "Edit Student (ID: " + idStudent + ")");
        return "student_edit";
    }

    @PostMapping("/student/saveEditStudent")
    public String saveEditStudent(StudentDTO studentDTO, RedirectAttributes redirectAttributes){
        if(studentService.updateStudent(studentDTO)){
            redirectAttributes.addFlashAttribute("message", "The student has been edited successfully !!!");
            return "redirect:/student";
        }
        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/student_edit";
    }
}
