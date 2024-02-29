package com.yannfigueiredo.atsresumegenerator.repositories;

import com.yannfigueiredo.atsresumegenerator.models.ExperienceDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceDescriptionRepository extends JpaRepository<ExperienceDescription, Long> {
}
