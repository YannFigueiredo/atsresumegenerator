package com.yannfigueiredo.atsresumegenerator.repositories;

import com.yannfigueiredo.atsresumegenerator.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
