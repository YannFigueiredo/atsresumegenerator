package com.yannfigueiredo.atsresumegenerator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "education_description")
public class EducationDescription {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 300)
    @Size(min = 1, max = 300)
    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "education_id", nullable = false)
    private Education education;
}
