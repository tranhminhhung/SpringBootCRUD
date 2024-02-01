package com.crud.srpringbootcrud.service.impl;

import com.crud.srpringbootcrud.DTO.LopHocDTO;
import com.crud.srpringbootcrud.exception.ExistException;
import com.crud.srpringbootcrud.exception.NotFoundException;
import com.crud.srpringbootcrud.model.LopHoc;
import com.crud.srpringbootcrud.repository.LopHocRepository;
import com.crud.srpringbootcrud.repository.TeacherRepository;
import com.crud.srpringbootcrud.service.LopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LopHocServiceImpl implements LopHocService {
    @Autowired private LopHocRepository lopHocRepository;
    @Autowired private TeacherRepository teacherRepository;
    @Override
    public List<LopHoc> getAllLopHoc() {
        return (List<LopHoc>)lopHocRepository.findAll();
    }

    @Override
    public boolean deleteLopHoc(int idLop) {
        if(lopHocRepository.findById(idLop).isEmpty()){
            throw new NotFoundException("Class not found");
        }
        lopHocRepository.deleteById(idLop);
        return false;
    }

    @Override
    public boolean addLopHoc(LopHoc lopHoc) {
        if(lopHocRepository.existsByIdLop(lopHoc.getIdLop()))
            throw new ExistException("Lop Hoc is existed!!");
        else{
            lopHocRepository.save(lopHoc);
        }
        return true;
    }

    @Override
    public LopHoc getLopHocById(int idLop) {
        Optional<LopHoc> result = lopHocRepository.findById(idLop);
        if(result.isPresent()){
            return  result.get();
        }
        throw new NotFoundException("Could not find any class with Id = " + idLop);
    }

    @Override
    public boolean updateLopHoc(LopHocDTO lopHocDTO) {
        LopHoc lopHoc = new LopHoc();
        lopHoc.setTenLop(lopHocDTO.getTenLop());
        lopHocRepository.findById(lopHocDTO.getIdLop()).map(lopHoc1 -> {
            lopHoc1.setTenLop(lopHoc.getTenLop());

            lopHoc1.setTeacher(teacherRepository.findById(lopHocDTO.getIdTeacher()).get());
            lopHocRepository.save(lopHoc1);
            return true;
        }).orElseGet(() -> {
            lopHoc.setIdLop(lopHocDTO.getIdLop());
            lopHoc.setTeacher(teacherRepository.findById(lopHoc.getTeacher().getIdTeacher()).get());
            lopHocRepository.save(lopHoc);
            return true;
        });
        return true;

    }

}
