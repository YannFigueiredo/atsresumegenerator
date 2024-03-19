package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.Factory;
import com.yannfigueiredo.atsresumegenerator.models.Experience;
import com.yannfigueiredo.atsresumegenerator.repositories.ExperienceRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ExperienceServiceTest {
    @Mock
    ExperienceRepository experienceRepository;

    @InjectMocks
    ExperienceService experienceService;

    Long existingId;
    Long existingResumeId;
    Long nonExistingId;
    Long nonExistingResumeId;
    Experience experience;
    Experience newExperience;
    Experience updatedExperience;

    @BeforeEach
    public void setup() {
        existingId = 1L;
        existingResumeId = 1L;
        nonExistingId = 999L;
        nonExistingResumeId = 999L;
        experience = Factory.createExperience("Desenvolvedor Java");
        newExperience = Factory.createExperience("Cientista de Dados");
        updatedExperience = Factory.createExperience("Desenvolvedor Front-End");
    }

    @Test
    public void findAllByResumeIdShouldReturnExperienceListWhenIdExists() {
        Mockito.when(experienceRepository.findByResumeId(existingResumeId)).thenReturn(List.of(experience, newExperience));

        List<Experience> result = experienceService.findAllByResumeId(existingResumeId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(result.contains(experience));
        verify(experienceRepository, times(1)).findByResumeId(existingResumeId);
    }

    @Test
    public void findAllByResumeIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(experienceRepository.findByResumeId(nonExistingResumeId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> experienceService.findAllByResumeId(nonExistingResumeId)
        );
        verify(experienceRepository, times(1)).findByResumeId(nonExistingResumeId);
    }

    @Test
    public void findByIdShouldReturnExperienceWhenIdExists() {
        Mockito.when(experienceRepository.findById(existingId)).thenReturn(Optional.of(experience));

        Experience result = experienceService.findById(existingId);

        Assertions.assertEquals(result, experience);
        verify(experienceRepository, times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(experienceRepository.findById(nonExistingId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
          RuntimeException.class,
          () -> experienceService.findById(nonExistingId)
        );
        verify(experienceRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void createShouldReturnExperience() {
        Mockito.when(experienceRepository.save(experience)).thenReturn(experience);

        Experience result = experienceService.create(experience);

        Assertions.assertNotNull(result);
        verify(experienceRepository, times(1)).save(experience);
    }

    @Test
    public void updateShouldReturnExperienceWhenIdExists() {
        Mockito.when(experienceRepository.getReferenceById(existingId)).thenReturn(experience);
        Mockito.when(experienceRepository.save(updatedExperience)).thenReturn(updatedExperience);

        Experience result = experienceService.update(existingId, updatedExperience);

        Assertions.assertEquals(result, updatedExperience);
        verify(experienceRepository, times(1)).getReferenceById(existingId);
        verify(experienceRepository, times(1)).save(updatedExperience);
    }

    @Test
    public void updateShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(experienceRepository.getReferenceById(nonExistingId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> experienceService.update(nonExistingId, updatedExperience)
        );
        verify(experienceRepository, times(1)).getReferenceById(nonExistingId);
        verify(experienceRepository, never()).save(updatedExperience);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Mockito.doNothing().when(experienceRepository).deleteById(existingId);

        Assertions.assertDoesNotThrow(() -> experienceService.delete(existingId));
        verify(experienceRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.doThrow(RuntimeException.class).when(experienceRepository).deleteById(nonExistingId);

        Assertions.assertThrows(RuntimeException.class, () -> experienceService.delete(nonExistingId));
        verify(experienceRepository, times(1)).deleteById(nonExistingId);
    }
}
