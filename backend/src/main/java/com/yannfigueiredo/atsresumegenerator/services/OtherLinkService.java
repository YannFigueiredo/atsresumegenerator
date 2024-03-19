package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.models.OtherLink;
import com.yannfigueiredo.atsresumegenerator.repositories.OtherLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OtherLinkService {
    @Autowired
    private OtherLinkRepository otherLinkRepository;

    public List<OtherLink> findAllByResumeId(Long ResumeId) {
        try {
            List<OtherLink> otherLinkList = this.otherLinkRepository.findByResumeId(ResumeId);

            return otherLinkList;
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível buscar a lista de 'outros links'! Erro: " + e.getMessage());
        }
    }

    public OtherLink findById(Long id) {
        Optional<OtherLink> otherLink = this.otherLinkRepository.findById(id);

        return otherLink.orElseThrow(() -> new RuntimeException("Outro link não encontrado!"));
    }

    @Transactional
    public OtherLink create(OtherLink otherLink) {
        return this.otherLinkRepository.save(otherLink);
    }

    @Transactional
    public OtherLink update(Long id, OtherLink otherLink) {
        try {
            OtherLink newOtherLink = this.otherLinkRepository.getReferenceById(id);

            newOtherLink.setLink(otherLink.getLink() != null ? otherLink.getLink() : newOtherLink.getLink());

            return this.otherLinkRepository.save(newOtherLink);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível atualizar um outro link! Erro: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            this.otherLinkRepository.deleteById(id);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível deletar o outro link! Erro: " + e.getMessage());
        }
    }
}
