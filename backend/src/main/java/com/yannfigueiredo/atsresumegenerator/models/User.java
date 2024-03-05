package com.yannfigueiredo.atsresumegenerator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, length = 20, nullable = false)
    @NotBlank
    @Size(min = 2, max = 20)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 8, max = 30)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToOne
    @JoinColumn(name = "resume_id", nullable = false, updatable = false)
    private Resume resume;
}
