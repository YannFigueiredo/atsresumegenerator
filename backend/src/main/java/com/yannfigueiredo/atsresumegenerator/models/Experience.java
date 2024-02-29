package com.yannfigueiredo.atsresumegenerator.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "experience")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Experience {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_title", nullable = false, length = 150)
    @Size(min = 2, max = 150)
    @NotBlank
    private String jobTitle;

    @Column(name = "enterprise", nullable = false, length = 100)
    @NotBlank
    @Size(min = 1, max = 100)
    private String enterpriseName;

    @Column(name = "exp_begin", nullable = false, length = 500)
    @NotBlank
    private Instant expBegin;

    @Column(name = "exp_end", length = 500)
    @NotNull
    private Instant expEnd;

    @OneToMany(mappedBy = "experience")
    private List<ExperienceDescription> descriptions = new ArrayList<ExperienceDescription>();

    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = false, updatable = false)
    private Resume resume;
}
