package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.Factory;
import com.yannfigueiredo.atsresumegenerator.models.AdditionalEducation;
import com.yannfigueiredo.atsresumegenerator.repositories.AdditionalEducationRepository;
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
public class AdditionalEducationServiceTest {
    @Mock
    AdditionalEducationRepository additionalEducationRepository;

    @InjectMocks
    AdditionalEducationService additionalEducationService;

    Long existingId;
    Long existingResumeId;
    Long nonExistingId;
    Long nonExistingResumeId;
    AdditionalEducation additionalEducation;
    AdditionalEducation newAdditionalEducation;
    AdditionalEducation updatedAdditionalEducation;

    @BeforeEach
    public void setup() {
        existingId = 1L;
        existingResumeId = 1L;
        nonExistingId = 999L;
        nonExistingResumeId = 999L;
        additionalEducation = Factory.createAdditionalEducation("Java");
        newAdditionalEducation = Factory.createAdditionalEducation("Python");
        updatedAdditionalEducation = Factory.createAdditionalEducation("Banco de Dados MySQL");
    }

    @Test
    public void findAllByResumeIdShouldReturnAdditionalEducationListWhenIdExists() {
        Mockito.when(additionalEducationRepository.findByResumeId(existingResumeId)).thenReturn(List.of(additionalEducation, newAdditionalEducation));

        List<AdditionalEducation> result = additionalEducationService.findAllByResumeId(existingResumeId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(result.contains(additionalEducation));
        verify(additionalEducationRepository, times(1)).findByResumeId(existingResumeId);
    }

    @Test
    public void findAllByResumeIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(additionalEducationRepository.findByResumeId(nonExistingResumeId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> additionalEducationService.findAllByResumeId(nonExistingResumeId)
        );
        verify(additionalEducationRepository, times(1)).findByResumeId(nonExistingResumeId);
    }

    @Test
    public void findByIdShouldReturnAdditionalEducationWhenIdExists() {
        Mockito.when(additionalEducationRepository.findById(existingId)).thenReturn(Optional.of(additionalEducation));

        AdditionalEducation result = additionalEducationService.findById(existingId);

        Assertions.assertEquals(result, additionalEducation);
        verify(additionalEducationRepository, times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(additionalEducationRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                RuntimeException.class,
                () -> additionalEducationService.findById(nonExistingId)
        );
        verify(additionalEducationRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void createShouldReturnAdditionalEducation() {
        Mockito.when(additionalEducationRepository.save(additionalEducation)).thenReturn(additionalEducation);

        AdditionalEducation result = additionalEducationService.create(additionalEducation);

        Assertions.assertNotNull(result);
        verify(additionalEducationRepository, times(1)).save(additionalEducation);
    }

    @Test
    public void updateShouldReturnAdditionalEducationWhenIdExists() {
        Mockito.when(additionalEducationRepository.getReferenceById(existingId)).thenReturn(additionalEducation);
        Mockito.when(additionalEducationRepository.save(updatedAdditionalEducation)).thenReturn(updatedAdditionalEducation);

        AdditionalEducation result = additionalEducationService.update(existingId, updatedAdditionalEducation);

        Assertions.assertEquals(result, updatedAdditionalEducation);
        verify(additionalEducationRepository, times(1)).getReferenceById(existingId);
        verify(additionalEducationRepository, times(1)).save(updatedAdditionalEducation);
    }

    @Test
    public void updateShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(additionalEducationRepository.getReferenceById(nonExistingId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> additionalEducationService.update(nonExistingId, updatedAdditionalEducation)
        );
        verify(additionalEducationRepository, times(1)).getReferenceById(nonExistingId);
        verify(additionalEducationRepository, never()).save(updatedAdditionalEducation);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Mockito.doNothing().when(additionalEducationRepository).deleteById(existingId);

        Assertions.assertDoesNotThrow(() -> additionalEducationService.delete(existingId));
        verify(additionalEducationRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.doThrow(RuntimeException.class).when(additionalEducationRepository).deleteById(nonExistingId);

        Assertions.assertThrows(RuntimeException.class, () -> additionalEducationService.delete(nonExistingId));
        verify(additionalEducationRepository, times(1)).deleteById(nonExistingId);
    }
}
