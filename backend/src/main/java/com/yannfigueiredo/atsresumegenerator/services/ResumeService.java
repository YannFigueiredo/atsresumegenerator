package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.models.Resume;
import com.yannfigueiredo.atsresumegenerator.repositories.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    public Resume findById(Long id) {
        Optional<Resume> result = this.resumeRepository.findById(id);

        return result.orElseThrow(() -> new RuntimeException("Currículo não encontrado!"));
    }

    @Transactional
    public Resume create(Resume resume) {
        return this.resumeRepository.save(resume);
    }

    @Transactional
    public Resume update(Long id, Resume resume) {
        try {
            Resume newResume = this.resumeRepository.getReferenceById(id);

            newResume.setEmail(resume.getEmail() != null ? resume.getEmail() : newResume.getEmail());
            newResume.setName(resume.getName() != null ? resume.getName() : newResume.getName());
            newResume.setLinkedin(resume.getLinkedin() != null ? resume.getLinkedin() : newResume.getLinkedin());
            newResume.setProfessionalSummary(
                    resume.getProfessionalSummary() != null ?
                            resume.getProfessionalSummary() : newResume.getProfessionalSummary()
            );
            newResume.setObjective(resume.getObjective() != null ? resume.getObjective() : newResume.getObjective());
            newResume.setTelephoneNumber(
                    resume.getTelephoneNumber() != null ?
                            resume.getTelephoneNumber() : newResume.getTelephoneNumber()
            );

            return this.resumeRepository.save(newResume);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível atualizar o currículo! Erro: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            this.resumeRepository.deleteById(id);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível deletar o currículo! Erro: " + e.getMessage());
        }
    }
}
