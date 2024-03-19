package com.yannfigueiredo.atsresumegenerator.repositories;

import com.yannfigueiredo.atsresumegenerator.models.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByResumeId(Long resumeId);
}
