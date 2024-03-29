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
import org.springframework.format.annotation.NumberFormat;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "additional_education")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdditionalEducation implements Serializable {
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

    @Column(name = "workload", nullable = false, length = 20)
    @Size(min = 1, max = 20)
    @NotBlank
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Double workload;

    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = false, updatable = false)
    private Resume resume;
}
