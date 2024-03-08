package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.Factory;
import com.yannfigueiredo.atsresumegenerator.models.Resume;
import com.yannfigueiredo.atsresumegenerator.repositories.ResumeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ResumeServiceTest {
    @Mock
    ResumeRepository resumeRepository;

    @InjectMocks
    ResumeService resumeService;

    private Long existingId;
    private Long nonExistingId;
    private Resume resume;
    private Resume updatedResume;

    @BeforeEach
    public void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 999L;
        resume = Factory.createResume("Yann");
        updatedResume = Factory.createResume("Yann Figueiredo");
    }

    @Test
    public void findByIdShouldReturnResumeWhenIdExists() {
        Mockito.when(resumeRepository.findById(existingId)).thenReturn(Optional.of(resume));

        Resume result = resumeService.findById(existingId);

        Assertions.assertEquals(result, resume);
        verify(resumeRepository, times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(resumeRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                RuntimeException.class,
                () -> resumeService.findById(nonExistingId)
        );
        verify(resumeRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void createShouldReturnResume() {
        Mockito.when(resumeRepository.save(resume)).thenReturn(resume);

        Resume result = resumeService.create(resume);

        Assertions.assertNotNull(result);
        verify(resumeRepository, times(1)).save(resume);
    }

    @Test
    public void updateShouldReturnResumeWhenIdExists() {
        Mockito.when(resumeRepository.getReferenceById(existingId)).thenReturn(resume);
        Mockito.when(resumeRepository.save(updatedResume)).thenReturn(updatedResume);

        Resume result = resumeService.update(existingId, updatedResume);

        Assertions.assertEquals(result, updatedResume);
        verify(resumeRepository, times(1)).getReferenceById(existingId);
        verify(resumeRepository, times(1)).save(updatedResume);
    }

    @Test
    public void updateShouldReturnResumeWhenIdDoesNotExists() {
        Mockito.when(resumeRepository.getReferenceById(nonExistingId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> resumeService.update(nonExistingId, updatedResume)
        );
        verify(resumeRepository, times(1)).getReferenceById(nonExistingId);
        verify(resumeRepository, never()).save(any(Resume.class));
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Mockito.doNothing().when(resumeRepository).deleteById(existingId);

        Assertions.assertDoesNotThrow(() -> resumeService.delete(existingId));
        verify(resumeRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.doThrow(RuntimeException.class).when(resumeRepository).deleteById(nonExistingId);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> resumeService.delete(nonExistingId)
        );
        verify(resumeRepository, times(1)).deleteById(nonExistingId);
    }
}
