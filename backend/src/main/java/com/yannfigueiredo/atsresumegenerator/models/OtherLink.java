package com.yannfigueiredo.atsresumegenerator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "other_link")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtherLink {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link", nullable = false, length = 200)
    @NotBlank
    @URL
    private String link;

    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = false, updatable = false)
    private Resume resume;
}
