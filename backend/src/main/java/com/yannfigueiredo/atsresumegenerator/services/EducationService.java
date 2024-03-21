package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.models.Education;
import com.yannfigueiredo.atsresumegenerator.repositories.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;

    public List<Education> findAllByResumeId(Long resumeId) {
        try {
            List<Education> educationList = this.educationRepository.findByResumeId(resumeId);

            return educationList;
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível buscar a lista de educação! Erro: " + e.getMessage());
        }
    }

    public Education findById(Long id) {
        Optional<Education> education = this.educationRepository.findById(id);

        return education.orElseThrow(() -> new RuntimeException("Educação não encontrada!"));
    }

    @Transactional
    public Education create(Education education) {
        return this.educationRepository.save(education);
    }

    @Transactional
    public Education update(Long id, Education newEducation) {
        try {
            Education education = this.educationRepository.getReferenceById(id);

            education.setCourse(
                    newEducation.getCourse() != null ?
                            newEducation.getCourse() :
                            education.getCourse()
            );
            education.setInstitution(
                    newEducation.getInstitution() != null ?
                            newEducation.getInstitution() :
                            education.getInstitution()
            );
            education.setConclusionYear(
                    newEducation.getConclusionYear() != null ?
                            newEducation.getConclusionYear() :
                            education.getConclusionYear()
            );

            return this.educationRepository.save(education);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível atualizar a educação! Erro: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            this.educationRepository.deleteById(id);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível apagar a educação Erro: !" + e.getMessage());
        }
    }
}
