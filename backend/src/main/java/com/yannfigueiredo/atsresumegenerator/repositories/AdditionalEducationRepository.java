package com.yannfigueiredo.atsresumegenerator.repositories;

import com.yannfigueiredo.atsresumegenerator.models.AdditionalEducation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdditionalEducationRepository extends JpaRepository<AdditionalEducation, Long> {
    List<AdditionalEducation> findByResumeId(Long resumeId);
}
