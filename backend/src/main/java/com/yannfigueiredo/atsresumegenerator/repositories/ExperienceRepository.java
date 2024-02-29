package com.yannfigueiredo.atsresumegenerator.repositories;

import com.yannfigueiredo.atsresumegenerator.models.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
