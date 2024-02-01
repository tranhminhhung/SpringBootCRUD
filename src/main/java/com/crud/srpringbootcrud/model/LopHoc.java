package com.crud.srpringbootcrud.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "lophoc")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LopHoc {
    @Id
    private int idLop;
    @Column
    private String tenLop;
    @ManyToOne
    @JoinColumn(name = "idTeacher")
    private Teacher teacher;

    @OneToMany(mappedBy = "lopHoc", cascade = CascadeType.ALL)
    private List<Student> students;

}
