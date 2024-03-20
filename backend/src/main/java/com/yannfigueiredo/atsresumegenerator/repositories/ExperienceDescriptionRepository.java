package com.yannfigueiredo.atsresumegenerator.repositories;

import com.yannfigueiredo.atsresumegenerator.models.ExperienceDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceDescriptionRepository extends JpaRepository<ExperienceDescription, Long> {
    List<ExperienceDescription> findByExperienceId(Long descriptionId);
}
