package com.crud.srpringbootcrud.service;

import com.crud.srpringbootcrud.DTO.LopHocDTO;
import com.crud.srpringbootcrud.model.LopHoc;

import java.util.List;

public interface LopHocService {
    List<LopHoc> getAllLopHoc();
    boolean deleteLopHoc(int idLop);
    boolean addLopHoc(LopHoc lopHoc);
    LopHoc getLopHocById(int idLop);
    boolean updateLopHoc(LopHocDTO LopHoc);
}
