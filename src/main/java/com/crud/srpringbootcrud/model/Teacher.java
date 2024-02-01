package com.crud.srpringbootcrud.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "teachers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    private int idTeacher;
    @Column
    private String nameTeacher;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<LopHoc> lopHocs;



}
