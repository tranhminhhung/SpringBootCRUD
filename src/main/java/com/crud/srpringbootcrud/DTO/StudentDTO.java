package com.crud.srpringbootcrud.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private int idStudent;
    private String nameStudent;
    private int ageStudent;
    private int idLop;
}
