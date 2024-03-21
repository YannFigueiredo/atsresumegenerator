package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.models.AdditionalEducation;
import com.yannfigueiredo.atsresumegenerator.repositories.AdditionalEducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalEducationService {
    @Autowired
    private AdditionalEducationRepository additionalEducationRepository;

    public List<AdditionalEducation> findAllByResumeId(Long resumeId) {
        try {
            List<AdditionalEducation> additionalEducationList = this.additionalEducationRepository.findByResumeId(resumeId);

            return additionalEducationList;
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível buscar a lista de educação adicional! Erro: " + e.getMessage());
        }
    }

    public AdditionalEducation findById(Long id) {
        Optional<AdditionalEducation> additionalAdditionalEducation = this.additionalEducationRepository.findById(id);

        return additionalAdditionalEducation.orElseThrow(() -> new RuntimeException("Educação adicional não encontrada!"));
    }

    @Transactional
    public AdditionalEducation create(AdditionalEducation additionalAdditionalEducation) {
        return this.additionalEducationRepository.save(additionalAdditionalEducation);
    }

    @Transactional
    public AdditionalEducation update(Long id, AdditionalEducation newAdditionalEducation) {
        try {
            AdditionalEducation additionalEducation = this.additionalEducationRepository.getReferenceById(id);

            additionalEducation.setCourse(
                    newAdditionalEducation.getCourse() != null ?
                            newAdditionalEducation.getCourse() :
                            additionalEducation.getCourse()
            );
            additionalEducation.setInstitution(
                    newAdditionalEducation.getInstitution() != null ?
                            newAdditionalEducation.getInstitution() :
                            additionalEducation.getInstitution()
            );
            additionalEducation.setWorkload(
                    newAdditionalEducation.getWorkload() != null ?
                            newAdditionalEducation.getWorkload() :
                            additionalEducation.getWorkload()
            );

            return this.additionalEducationRepository.save(additionalEducation);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível atualizar a educação adicional! Erro: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            this.additionalEducationRepository.deleteById(id);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível apagar a educação adicional Erro: !" + e.getMessage());
        }
    }
}
