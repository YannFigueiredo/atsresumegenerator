package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.models.User;
import com.yannfigueiredo.atsresumegenerator.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.yannfigueiredo.atsresumegenerator.Factory.createUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Long existingId;
    private Long nonExistingId;
    User user;
    User updatedUser;

    @BeforeEach
    public void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 9999L;
        user = createUser("yannfigueiredo");
        updatedUser = createUser("alexfigueiredo");
    }

    @Test
    public void findByIdShouldReturnUserWhenIdExists() {
        Mockito.when(userRepository.findById(existingId)).thenReturn(Optional.of(user));

        User newUser = userService.findById(existingId);

        Assertions.assertEquals(user, newUser);
        verify(userRepository, times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldReturnInvalidUserWhenIdDoesNotExists() {
        Mockito.when(userRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> {
           userService.findById(nonExistingId);
        });
        verify(userRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void createShouldReturnUser() {
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User newUser = userService.create(user);

        Assertions.assertNotNull(newUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void updateShouldReturnUserWhenIdExists() {
        Mockito.when(userRepository.getReferenceById(existingId)).thenReturn(user);
        Mockito.when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.update(existingId, updatedUser);

        Assertions.assertEquals(result, updatedUser);
        verify(userRepository, times(1)).getReferenceById(existingId);
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    public void updateShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.when(userRepository.getReferenceById(nonExistingId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> userService.update(nonExistingId, updatedUser)
        );
        verify(userRepository, times(1)).getReferenceById(nonExistingId);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Mockito.doNothing().when(userRepository).deleteById(existingId);

        Assertions.assertDoesNotThrow(() -> userService.delete(existingId));
        verify(userRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowExceptionWhenIdDoesNotExists() {
        Mockito.doThrow(RuntimeException.class).when(userRepository).deleteById(nonExistingId);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> userService.delete(nonExistingId)
        );
        verify(userRepository, times(1)).deleteById(nonExistingId);
    }
}
