package com.crud.srpringbootcrud.repository;

import com.crud.srpringbootcrud.model.LopHoc;
import org.springframework.data.repository.CrudRepository;

public interface LopHocRepository extends CrudRepository<LopHoc, Integer> {
    Boolean existsByIdLop(Integer idLop);
}
