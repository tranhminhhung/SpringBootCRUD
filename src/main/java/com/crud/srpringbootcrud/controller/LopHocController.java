package com.crud.srpringbootcrud.controller;

import com.crud.srpringbootcrud.DTO.LopHocDTO;
import com.crud.srpringbootcrud.exception.NotFoundException;
import com.crud.srpringbootcrud.model.LopHoc;
import com.crud.srpringbootcrud.model.Teacher;
import com.crud.srpringbootcrud.service.impl.LopHocServiceImpl;
import com.crud.srpringbootcrud.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LopHocController {
    @Autowired
    private LopHocServiceImpl lopHocService;
    @Autowired private TeacherServiceImpl teacherService;

    @GetMapping("/lop_hoc")
    public String getAllClass(Model model){
        model.addAttribute("lop_hoc", lopHocService.getAllLopHoc());
        return "lop_hoc";
    }

    @GetMapping("/lop_hoc/delete/{idLop}")
    public String deleteLopHoc(@PathVariable int idLop, RedirectAttributes redirectAttributes){
        lopHocService.deleteLopHoc(idLop);
        redirectAttributes.addFlashAttribute("message", "Delete Success");
        return "redirect:/lop_hoc";
    }

    @GetMapping("/lop_hoc/new")
    public String newLopHoc(Model model){
        model.addAttribute("lop_hoc", new LopHocDTO());
        model.addAttribute("teacher", teacherService.getAllTeacher());
        return "lop_hoc_new";
    }

    @PostMapping("/lop_hoc/saveLopHoc")
    public String saveLopHoc(LopHoc lopHoc, RedirectAttributes redirectAttributes){
        if(lopHocService.addLopHoc(lopHoc)){
            redirectAttributes.addFlashAttribute("message", "Add new class successfully!!!");
            return "redirect:/lop_hoc";
        }
        redirectAttributes.addFlashAttribute("message", "Save Failure!!!");
        return "lop_hoc_new";
    }

    @GetMapping("/lop_hoc/edit/{idLop}")
    public String showEditForm(@PathVariable("idLop") int idLop, Model model){
        LopHoc lopHoc = lopHocService.getLopHocById(idLop);
        model.addAttribute("lop_hoc", lopHoc);
        model.addAttribute("teacher", teacherService.getAllTeacher());
        model.addAttribute("pageTitle", "Edit Class (ID: " + idLop + ")");
        return "lop_hoc_edit";
    }


    @PostMapping("/lop_hoc/saveEditClass")
    public String saveEditClass(LopHocDTO lopHocDTO, RedirectAttributes redirectAttributes){
        if(lopHocService.updateLopHoc(lopHocDTO)){
            redirectAttributes.addFlashAttribute("message", "The class has been edited successfully !!!");
            return "redirect:/lop_hoc";
        }
        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/lop_hoc_edit";
    }
}
