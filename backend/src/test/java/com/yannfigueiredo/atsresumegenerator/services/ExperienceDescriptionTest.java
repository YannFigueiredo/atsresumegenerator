package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.Factory;
import com.yannfigueiredo.atsresumegenerator.models.ExperienceDescription;
import com.yannfigueiredo.atsresumegenerator.repositories.ExperienceDescriptionRepository;
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
public class ExperienceDescriptionTest {
    @Mock
    ExperienceDescriptionRepository experienceDescriptionRepository;

    @InjectMocks
    ExperienceDescriptionService experienceDescriptionService;

    Long existingId;
    Long existingExperienceId;
    Long nonExistingId;
    Long nonExistingExperienceId;
    ExperienceDescription experienceDescription;
    ExperienceDescription newExperienceDescription;
    ExperienceDescription updatedExperienceDescription;

    @BeforeEach
    public void setup() {
        existingId = 1L;
        existingExperienceId = 1L;
        nonExistingId = 999L;
        nonExistingExperienceId = 999L;
        experienceDescription = Factory.createExperienceDescription("Desenvolvia interfaces");
        newExperienceDescription = Factory.createExperienceDescription("Criava testes");
        updatedExperienceDescription = Factory.createExperienceDescription("Desenvolvia componentes");
    }

    @Test
    public void findAllByExperienceIdShouldReturnExperienceDescriptionListWhenIdExists() {
        Mockito.when(experienceDescriptionRepository.findByExperienceId(existingExperienceId)).
                thenReturn(List.of(experienceDescription, newExperienceDescription));

        List<ExperienceDescription> result = experienceDescriptionService.
                findAllByExperienceId(existingExperienceId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(result.contains(experienceDescription));
        verify(experienceDescriptionRepository, times(1)).
                findByExperienceId(existingExperienceId);
    }

    @Test
    public void findAllByExperienceIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(experienceDescriptionRepository.findByExperienceId(nonExistingId)).
                thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> experienceDescriptionService.findAllByExperienceId(nonExistingExperienceId)
        );
        verify(experienceDescriptionRepository, times(1)).
                findByExperienceId(nonExistingExperienceId);
    }

    @Test
    public void findByIdShouldReturnExperienceDescriptionWhenIdExists() {
        Mockito.when(experienceDescriptionRepository.findById(existingId)).
                thenReturn(Optional.of(experienceDescription));

        ExperienceDescription result = experienceDescriptionService.findById(existingId);

        Assertions.assertEquals(result, experienceDescription);
        verify(experienceDescriptionRepository, times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(experienceDescriptionRepository.findById(nonExistingExperienceId)).
                thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> experienceDescriptionService.findById(nonExistingId)
        );
        verify(experienceDescriptionRepository, times(1)).
                findById(nonExistingId);
    }

    @Test
    public void createShouldReturnExperience() {
        Mockito.when(experienceDescriptionRepository.save(experienceDescription)).
                thenReturn(experienceDescription);

        ExperienceDescription result = experienceDescriptionService.create(experienceDescription);

        Assertions.assertNotNull(result);
        verify(experienceDescriptionRepository, times(1)).save(experienceDescription);
    }

    @Test
    public void updateShouldReturnExperienceWhenIdExists() {
        Mockito.when(experienceDescriptionRepository.getReferenceById(existingId)).
                thenReturn(experienceDescription);
        Mockito.when(experienceDescriptionRepository.save(updatedExperienceDescription)).
                thenReturn(updatedExperienceDescription);

        ExperienceDescription result = experienceDescriptionService.update(existingId, updatedExperienceDescription);

        Assertions.assertEquals(result, updatedExperienceDescription);
        verify(experienceDescriptionRepository, times(1)).getReferenceById(existingId);
        verify(experienceDescriptionRepository, times(1)).save(updatedExperienceDescription);
    }

    @Test
    public void updateShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(experienceDescriptionRepository.getReferenceById(nonExistingId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> experienceDescriptionService.update(nonExistingId, updatedExperienceDescription)
        );
        verify(experienceDescriptionRepository, times(1)).getReferenceById(nonExistingId);
        verify(experienceDescriptionRepository, never()).save(any(ExperienceDescription.class));
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Mockito.doNothing().when(experienceDescriptionRepository).deleteById(existingId);

        Assertions.assertDoesNotThrow(() -> experienceDescriptionService.delete(existingId));
        verify(experienceDescriptionRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.doThrow(RuntimeException.class).when(experienceDescriptionRepository).deleteById(nonExistingId);

        Assertions.assertThrows(RuntimeException.class, () -> experienceDescriptionService.delete(nonExistingId));
        verify(experienceDescriptionRepository, times(1)).deleteById(nonExistingId);
    }
}
