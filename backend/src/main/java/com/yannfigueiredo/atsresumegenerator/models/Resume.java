package com.yannfigueiredo.atsresumegenerator.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resume")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Resume {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "name", nullable = false, length = 100)
    @Size(min = 2, max = 100)
    @NotBlank
    private String name;

    @Column(name = "telephone_number", nullable = false, length = 11)
    private Integer telephoneNumber;

    @Column(name = "email", unique = true, nullable = false, length = 200)
    @Email
    private String email;

    @Column(name = "linkedin", unique = true, nullable = false, length = 200)
    @URL
    private String linkedin;

    @OneToMany(mappedBy = "resume")
    private List<OtherLink> othersLinks = new ArrayList<OtherLink>();

    @Column(name = "objective", nullable = false, length = 150)
    private String objective;

    @Column(name = "professional_summary", nullable = false, length = 460)
    private String professionalSummary;

    @OneToMany(mappedBy = "resume")
    private List<Education> education = new ArrayList<Education>();

    @OneToMany(mappedBy = "resume")
    private List<Experience> experience = new ArrayList<Experience>();

    @OneToMany(mappedBy = "resume")
    private List<AdditionalEducation> additionalEducation = new ArrayList<AdditionalEducation>();

    @OneToMany(mappedBy = "resume")
    private List<AdditionalInfo> additionalInfo = new ArrayList<AdditionalInfo>();
}
