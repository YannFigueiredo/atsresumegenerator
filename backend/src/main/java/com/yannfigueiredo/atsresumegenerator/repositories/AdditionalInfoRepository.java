package com.yannfigueiredo.atsresumegenerator.repositories;

import com.yannfigueiredo.atsresumegenerator.models.AdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long> {
}
