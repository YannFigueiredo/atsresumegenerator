package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.models.AdditionalInfo;
import com.yannfigueiredo.atsresumegenerator.repositories.AdditionalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalInfoService {
    @Autowired
    private AdditionalInfoRepository additionalInfoRepository;

    public List<AdditionalInfo> findAllByResumeId(Long resumeId) {
        try {
            List<AdditionalInfo> additionalInfoList = this.additionalInfoRepository.findByResumeId(resumeId);

            return additionalInfoList;
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível buscar a lista de informações adicionais! Erro: " + e.getMessage());
        }
    }

    public AdditionalInfo findById(Long id) {
        Optional<AdditionalInfo> additionalInfo = this.additionalInfoRepository.findById(id);

        return additionalInfo.orElseThrow(() -> new RuntimeException("Informação adicional não encontrada!"));
    }

    @Transactional
    public AdditionalInfo create(AdditionalInfo additionalInfo) {
        return this.additionalInfoRepository.save(additionalInfo);
    }

    @Transactional
    public AdditionalInfo update(Long id, AdditionalInfo newAdditionalInfo) {
        try {
            AdditionalInfo additionalInfo = this.additionalInfoRepository.getReferenceById(id);

            additionalInfo.setInfo(
                    newAdditionalInfo.getInfo() != null ?
                            newAdditionalInfo.getInfo() :
                            additionalInfo.getInfo()
            );

            return this.additionalInfoRepository.save(additionalInfo);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível atualizar a informação adicional! Erro: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            this.additionalInfoRepository.deleteById(id);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível apagar a informação adicional Erro: !" + e.getMessage());
        }
    }
}
