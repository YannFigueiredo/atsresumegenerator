package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.models.ExperienceDescription;
import com.yannfigueiredo.atsresumegenerator.repositories.ExperienceDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceDescriptionService {
    @Autowired
    ExperienceDescriptionRepository experienceDescriptionRepository;

    public List<ExperienceDescription> findAllByExperienceId(Long descriptionId) {
        try {
            List<ExperienceDescription> experienceDescriptionList =
                    this.experienceDescriptionRepository.findByExperienceId(descriptionId);

            return experienceDescriptionList;
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível buscar a lista de descrições de experiência! Erro: " + e.getMessage());
        }
    }

    public ExperienceDescription findById(Long id) {
        Optional<ExperienceDescription> experienceDescription = this.experienceDescriptionRepository.findById(id);

        return experienceDescription.orElseThrow(() -> new RuntimeException("Não foi possível encontrar a descrição de experiência!"));
    }

    @Transactional
    public ExperienceDescription create(ExperienceDescription experienceDescription) {
        return this.experienceDescriptionRepository.save(experienceDescription);
    }

    @Transactional
    public ExperienceDescription update(Long id, ExperienceDescription newExperienceDescription) {
        try {
            ExperienceDescription experienceDescription = this.experienceDescriptionRepository.getReferenceById(id);

            experienceDescription.setDescription(newExperienceDescription.getDescription() != null ?
                    newExperienceDescription.getDescription() :
                    experienceDescription.getDescription()
            );

            return this.experienceDescriptionRepository.save(experienceDescription);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível atualizar a descrição de experiênci! Erro: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            this.experienceDescriptionRepository.deleteById(id);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível deletar a descrição de experiência! Erro: " + e.getMessage());
        }
    }
}
