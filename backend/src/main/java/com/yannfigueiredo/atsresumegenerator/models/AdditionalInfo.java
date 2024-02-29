package com.yannfigueiredo.atsresumegenerator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "additional_info")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdditionalInfo {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "info", nullable = false, length = 500)
    @Size(min = 1, max = 500)
    @NotBlank
    private String info;

    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = false, updatable = false)
    private Resume resume;
}
