package com.yannfigueiredo.atsresumegenerator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "education")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Education {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enterprise", nullable = false, length = 100)
    @NotBlank
    @Size(min = 1, max = 100)
    private String enterpriseName;

    @Column(name = "exp_begin", nullable = false)
    @NotBlank
    private Instant expBegin;

    @Column(name = "exp_end")
    @NotNull
    private Instant expEnd;

    @OneToMany(mappedBy = "education")
    private List<EducationDescription> descriptions = new ArrayList<EducationDescription>();

    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = false, updatable = false)
    private Resume resume;
}
