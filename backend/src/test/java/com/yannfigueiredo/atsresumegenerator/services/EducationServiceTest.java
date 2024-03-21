package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.Factory;
import com.yannfigueiredo.atsresumegenerator.models.Education;
import com.yannfigueiredo.atsresumegenerator.repositories.EducationRepository;
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
public class EducationServiceTest {
    @Mock
    EducationRepository educationRepository;

    @InjectMocks
    EducationService educationService;

    Long existingId;
    Long existingResumeId;
    Long nonExistingId;
    Long nonExistingResumeId;
    Education education;
    Education newEducation;
    Education updatedEducation;

    @BeforeEach
    public void setup() {
        existingId = 1L;
        existingResumeId = 1L;
        nonExistingId = 999L;
        nonExistingResumeId = 999L;
        education = Factory.createEducation("Ciência da Computação");
        newEducation = Factory.createEducation("Engenharia da Computação");
        updatedEducation = Factory.createEducation("Engenharia de Software");
    }

    @Test
    public void findAllByResumeIdShouldReturnEducationListWhenIdExists() {
        Mockito.when(educationRepository.findByResumeId(existingResumeId)).thenReturn(List.of(education, newEducation));

        List<Education> result = educationService.findAllByResumeId(existingResumeId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(result.contains(education));
        verify(educationRepository, times(1)).findByResumeId(existingResumeId);
    }

    @Test
    public void findAllByResumeIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(educationRepository.findByResumeId(nonExistingResumeId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> educationService.findAllByResumeId(nonExistingResumeId)
        );
        verify(educationRepository, times(1)).findByResumeId(nonExistingResumeId);
    }

    @Test
    public void findByIdShouldReturnEducationWhenIdExists() {
        Mockito.when(educationRepository.findById(existingId)).thenReturn(Optional.of(education));

        Education result = educationService.findById(existingId);

        Assertions.assertEquals(result, education);
        verify(educationRepository, times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(educationRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                RuntimeException.class,
                () -> educationService.findById(nonExistingId)
        );
        verify(educationRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void createShouldReturnEducation() {
        Mockito.when(educationRepository.save(education)).thenReturn(education);

        Education result = educationService.create(education);

        Assertions.assertNotNull(result);
        verify(educationRepository, times(1)).save(education);
    }

    @Test
    public void updateShouldReturnEducationWhenIdExists() {
        Mockito.when(educationRepository.getReferenceById(existingId)).thenReturn(education);
        Mockito.when(educationRepository.save(updatedEducation)).thenReturn(updatedEducation);

        Education result = educationService.update(existingId, updatedEducation);

        Assertions.assertEquals(result, updatedEducation);
        verify(educationRepository, times(1)).getReferenceById(existingId);
        verify(educationRepository, times(1)).save(updatedEducation);
    }

    @Test
    public void updateShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(educationRepository.getReferenceById(nonExistingId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> educationService.update(nonExistingId, updatedEducation)
        );
        verify(educationRepository, times(1)).getReferenceById(nonExistingId);
        verify(educationRepository, never()).save(updatedEducation);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Mockito.doNothing().when(educationRepository).deleteById(existingId);

        Assertions.assertDoesNotThrow(() -> educationService.delete(existingId));
        verify(educationRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.doThrow(RuntimeException.class).when(educationRepository).deleteById(nonExistingId);

        Assertions.assertThrows(RuntimeException.class, () -> educationService.delete(nonExistingId));
        verify(educationRepository, times(1)).deleteById(nonExistingId);
    }
}
