package com.yannfigueiredo.atsresumegenerator.repositories;

import com.yannfigueiredo.atsresumegenerator.models.OtherLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OtherLinkRepository extends JpaRepository<OtherLink, Long> {
    List<OtherLink> findByResumeId(Long resumeId);
}
