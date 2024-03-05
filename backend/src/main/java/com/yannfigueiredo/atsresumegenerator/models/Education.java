package com.yannfigueiredo.atsresumegenerator.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "education")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Education implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course", nullable = false, length = 300)
    @Size(min = 1, max = 300)
    @NotBlank
    private String course;

    @Column(name = "institution", nullable = false, length = 300)
    @Size(min = 1, max = 300)
    @NotBlank
    private String institution;

    @Column(name = "conclusion_year", nullable = false, length = 4)
    @Size(min = 4, max = 4)
    @NotBlank
    private String conclusionYear;

    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = false, updatable = false)
    private Resume resume;
}
