package com.crud.srpringbootcrud.DTO;

import com.crud.srpringbootcrud.model.Teacher;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LopHocDTO {
    private int idLop;
    private String tenLop;
    private int idTeacher;
}
