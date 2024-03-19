package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.models.OtherLink;
import com.yannfigueiredo.atsresumegenerator.repositories.OtherLinkRepository;
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

import static com.yannfigueiredo.atsresumegenerator.Factory.createOtherLink;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class OtherLinkServiceTest {
    @Mock
    OtherLinkRepository otherLinkRepository;

    @InjectMocks
    OtherLinkService otherLinkService;

    Long existingId;
    Long existingResumeId;
    Long nonExistingId;
    Long nonExistingResumeId;
    OtherLink otherLink;
    OtherLink newOtherLink;
    OtherLink updatedOtherLink;

    @BeforeEach
    public void setup() {
        existingId = 1L;
        existingResumeId = 1L;
        nonExistingId = 999L;
        nonExistingResumeId = 999L;
        otherLink = createOtherLink("https://google.com");
        updatedOtherLink = createOtherLink("https://facebook.com");
        newOtherLink = createOtherLink("https://bing.com");
    }

    @Test
    public void findAllByResumeIdShouldReturnOtherLinkListWhenIdExists() {
        Mockito.when(otherLinkRepository.findByResumeId(existingResumeId)).thenReturn(List.of(otherLink, newOtherLink));

        List<OtherLink> result = otherLinkService.findAllByResumeId(existingResumeId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(otherLink));
        verify(otherLinkRepository, times(1)).findByResumeId(existingResumeId);
    }

    @Test
    public void findAllByResumeIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(otherLinkRepository.findByResumeId(nonExistingResumeId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> otherLinkService.findAllByResumeId(nonExistingResumeId)
        );
        verify(otherLinkRepository, times(1)).findByResumeId(nonExistingResumeId);
    }

    @Test
    public void findByIdShouldReturnOtherLinkWhenIdExists() {
        Mockito.when(otherLinkRepository.findById(existingId)).thenReturn(Optional.of(otherLink));

        OtherLink result = otherLinkService.findById(existingId);

        Assertions.assertEquals(result, otherLink);
        verify(otherLinkRepository, times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(otherLinkRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                RuntimeException.class,
                () -> otherLinkService.findById(nonExistingId)
        );
        verify(otherLinkRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void createShouldReturnOtherLink() {
        Mockito.when(otherLinkRepository.save(otherLink)).thenReturn(otherLink);

        OtherLink result = otherLinkService.create(otherLink);

        Assertions.assertNotNull(result);
        verify(otherLinkRepository, times(1)).save(otherLink);
    }

    @Test
    public void updateShouldReturnOtherLinkWhenIdExists() {
        Mockito.when(otherLinkRepository.getReferenceById(existingId)).thenReturn(otherLink);
        Mockito.when(otherLinkRepository.save(updatedOtherLink)).thenReturn(updatedOtherLink);

        OtherLink result = otherLinkService.update(existingId, updatedOtherLink);

        Assertions.assertEquals(updatedOtherLink, result);
        verify(otherLinkRepository, times(1)).getReferenceById(existingId);
        verify(otherLinkRepository, times(1)).save(updatedOtherLink);
    }

    @Test
    public void updateShouldThrowExceptionWhenIdDoesNoExists() {
        Mockito.when(otherLinkRepository.getReferenceById(nonExistingId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> otherLinkService.update(nonExistingId, updatedOtherLink)
        );
        verify(otherLinkRepository, times(1)).getReferenceById(nonExistingId);
        verify(otherLinkRepository, never()).save(any(OtherLink.class));
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Mockito.doNothing().when(otherLinkRepository).deleteById(existingId);

        Assertions.assertDoesNotThrow(() -> otherLinkService.delete(existingId));
        verify(otherLinkRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowRuntimeWhenIdDoesNotExists() {
        Mockito.doThrow(RuntimeException.class).when(otherLinkRepository).deleteById(nonExistingId);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> otherLinkService.delete(nonExistingId)
        );
        verify(otherLinkRepository, times(1)).deleteById(nonExistingId);
    }
}
