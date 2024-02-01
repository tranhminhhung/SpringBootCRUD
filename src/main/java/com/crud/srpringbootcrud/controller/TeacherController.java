package com.crud.srpringbootcrud.controller;

import com.crud.srpringbootcrud.DTO.TeacherDTO;
import com.crud.srpringbootcrud.exception.NotFoundException;
import com.crud.srpringbootcrud.model.Teacher;
import com.crud.srpringbootcrud.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TeacherController {
    @Autowired private TeacherServiceImpl teacherService;

    @GetMapping("/teacher")
    public String getAllTeacher(Model model){
        model.addAttribute("teacher", teacherService.getAllTeacher());
        return "teacher";
    }

    @GetMapping("/teacher/new")
    public String newTeacher(Model model){
        model.addAttribute("teacher", new TeacherDTO());
        return "teacher_new";
    }

    @PostMapping("/teacher/saveTeacher")
    public String saveTeacher(Teacher teacher, RedirectAttributes redirectAttributes){
        if(teacherService.addTeacher(teacher)){
            redirectAttributes.addFlashAttribute("message", "The teacher has been saved successfully !!!");
            return "redirect:/teacher";
        }
        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/teacher_new";
    }

    @GetMapping("/teacher/delete/{idTeacher}")
    public String deleteTeacher(@PathVariable int idTeacher, RedirectAttributes redirectAttributes) {
        teacherService.deleteTeacher((idTeacher));
        redirectAttributes.addFlashAttribute("message", "Delete Success");
        return "redirect:/teacher";
    }

    @GetMapping("/teacher/edit/{idTeacher}")
    public String showEditForm(@PathVariable("idTeacher") Integer idTeacher, RedirectAttributes redirectAttributes, Model model) {
        try{

            Teacher teacher = teacherService.getTeacherById(idTeacher);
            model.addAttribute("teacher", teacher);
            model.addAttribute("pageTitle", "Edit Teacher (ID: " + idTeacher + ")");
            return "teacher_edit";
        }catch(NotFoundException e){
            redirectAttributes.addFlashAttribute("message", "The user has been saved successfully !!!");
            return "redirect:/teacher";
        }
    }

    @PostMapping("/teacher/saveEditTeacher")
    public String saveEditTeacher(TeacherDTO teacher, RedirectAttributes redirectAttributes){
        if(teacherService.updateTeacher(teacher)){
            redirectAttributes.addFlashAttribute("message", "The teacher has been edited successfully !!!");
            return "redirect:/teacher";
        }
        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/teacher_edit";
    }

}
