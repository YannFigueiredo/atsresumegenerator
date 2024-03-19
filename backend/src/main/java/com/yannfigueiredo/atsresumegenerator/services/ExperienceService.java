package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.models.Experience;
import com.yannfigueiredo.atsresumegenerator.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {
    @Autowired
    private ExperienceRepository experienceRepository;

    public List<Experience> findAllByResumeId(Long resumeId) {
        try {
            List<Experience> experienceList = this.experienceRepository.findByResumeId(resumeId);

            return experienceList;
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível buscar a lista de experiências! Erro: " + e.getMessage());
        }
    }

    public Experience findById(Long id) {
        Optional<Experience> experience = this.experienceRepository.findById(id);

        return experience.orElseThrow(() -> new RuntimeException("Experiência não encontrada!"));
    }

    @Transactional
    public Experience create(Experience experience) {
        return this.experienceRepository.save(experience);
    }

    @Transactional
    public Experience update(Long id, Experience newExperience) {
        try {
            Experience experience = this.experienceRepository.getReferenceById(id);

            experience.setEnterpriseName(
                    newExperience.getEnterpriseName() != null ?
                            newExperience.getEnterpriseName() :
                            experience.getEnterpriseName()
            );
            experience.setJobTitle(
                    newExperience.getJobTitle() != null ?
                            newExperience.getJobTitle() :
                            experience.getJobTitle()
            );
            experience.setExpBegin(
                    newExperience.getExpBegin() != null ?
                            newExperience.getExpBegin() :
                            experience.getExpBegin()
            );
            experience.setExpEnd(
                    newExperience.getExpEnd() != null ?
                            newExperience.getExpEnd() :
                            experience.getExpEnd()

            );

           return this.experienceRepository.save(experience);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível atualizar a experiência! Erro: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            this.experienceRepository.deleteById(id);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível apagar a experiência Erro: !" + e.getMessage());
        }
    }
}
