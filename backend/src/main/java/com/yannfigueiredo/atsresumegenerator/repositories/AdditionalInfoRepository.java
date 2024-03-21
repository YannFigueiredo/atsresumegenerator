package com.yannfigueiredo.atsresumegenerator.repositories;

import com.yannfigueiredo.atsresumegenerator.models.AdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long> {
    List<AdditionalInfo> findByResumeId(Long resumeId);
}
