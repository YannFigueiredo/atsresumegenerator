package com.yannfigueiredo.atsresumegenerator.repositories;

import com.yannfigueiredo.atsresumegenerator.models.AdditionalEducation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalEducationRepository extends JpaRepository<AdditionalEducation, Long> {
}
