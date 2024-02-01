package com.crud.srpringbootcrud.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    private int idStudent;
    @Column
    private String nameStudent;
    @Column
    private int ageStudent;
    @ManyToOne
    @JoinColumn(name = "idLop")
    private LopHoc lopHoc;
}
