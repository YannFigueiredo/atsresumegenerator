package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.Factory;
import com.yannfigueiredo.atsresumegenerator.models.AdditionalInfo;
import com.yannfigueiredo.atsresumegenerator.repositories.AdditionalInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AdditionalInfoServiceTest {
    @Mock
    AdditionalInfoRepository additionalInfoRepository;

    @InjectMocks
    AdditionalInfoService additionalInfoService;

    Long existingId;
    Long existingResumeId;
    Long nonExistingId;
    Long nonExistingResumeId;
    AdditionalInfo additionalInfo;
    AdditionalInfo newAdditionalInfo;
    AdditionalInfo updatedAdditionalInfo;

    @BeforeEach
    public void setup() {
        existingId = 1L;
        existingResumeId = 1L;
        nonExistingId = 999L;
        nonExistingResumeId = 999L;
        additionalInfo = Factory.createAdditionalInfo("Possuo curso avançado de Espanhol");
        newAdditionalInfo = Factory.createAdditionalInfo("Tenho disponibilidade para mudança");
        updatedAdditionalInfo = Factory.createAdditionalInfo("Possuo curso avançado de Inglês");
    }

    @Test
    public void findAllByResumeIdShouldReturnAdditionalInfoListWhenIdExists() {
        Mockito.when(additionalInfoRepository.findByResumeId(existingResumeId)).thenReturn(List.of(additionalInfo, newAdditionalInfo));

        List<AdditionalInfo> result = additionalInfoService.findAllByResumeId(existingResumeId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(result.contains(additionalInfo));
        verify(additionalInfoRepository, times(1)).findByResumeId(existingResumeId);
    }

    @Test
    public void findAllByResumeIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(additionalInfoRepository.findByResumeId(nonExistingResumeId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> additionalInfoService.findAllByResumeId(nonExistingResumeId)
        );
        verify(additionalInfoRepository, times(1)).findByResumeId(nonExistingResumeId);
    }

    @Test
    public void findByIdShouldReturnAdditionalInfoWhenIdExists() {
        Mockito.when(additionalInfoRepository.findById(existingId)).thenReturn(Optional.of(additionalInfo));

        AdditionalInfo result = additionalInfoService.findById(existingId);

        Assertions.assertEquals(result, additionalInfo);
        verify(additionalInfoRepository, times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(additionalInfoRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                RuntimeException.class,
                () -> additionalInfoService.findById(nonExistingId)
        );
        verify(additionalInfoRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void createShouldReturnAdditionalInfo() {
        Mockito.when(additionalInfoRepository.save(additionalInfo)).thenReturn(additionalInfo);

        AdditionalInfo result = additionalInfoService.create(additionalInfo);

        Assertions.assertNotNull(result);
        verify(additionalInfoRepository, times(1)).save(additionalInfo);
    }

    @Test
    public void updateShouldReturnAdditionalInfoWhenIdExists() {
        Mockito.when(additionalInfoRepository.getReferenceById(existingId)).thenReturn(additionalInfo);
        Mockito.when(additionalInfoRepository.save(updatedAdditionalInfo)).thenReturn(updatedAdditionalInfo);

        AdditionalInfo result = additionalInfoService.update(existingId, updatedAdditionalInfo);

        Assertions.assertEquals(result, updatedAdditionalInfo);
        verify(additionalInfoRepository, times(1)).getReferenceById(existingId);
        verify(additionalInfoRepository, times(1)).save(updatedAdditionalInfo);
    }

    @Test
    public void updateShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(additionalInfoRepository.getReferenceById(nonExistingId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> additionalInfoService.update(nonExistingId, updatedAdditionalInfo)
        );
        verify(additionalInfoRepository, times(1)).getReferenceById(nonExistingId);
        verify(additionalInfoRepository, never()).save(updatedAdditionalInfo);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Mockito.doNothing().when(additionalInfoRepository).deleteById(existingId);

        Assertions.assertDoesNotThrow(() -> additionalInfoService.delete(existingId));
        verify(additionalInfoRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.doThrow(RuntimeException.class).when(additionalInfoRepository).deleteById(nonExistingId);

        Assertions.assertThrows(RuntimeException.class, () -> additionalInfoService.delete(nonExistingId));
        verify(additionalInfoRepository, times(1)).deleteById(nonExistingId);
    }
}
