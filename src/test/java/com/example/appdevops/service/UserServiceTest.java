package com.example.appdevops.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.appdevops.dto.UserDto.GetUserNameResponse;
import com.example.appdevops.dto.UserDto.GetUserResponse;
import com.example.appdevops.entity.User;
import com.example.appdevops.exception.NotFoundException;
import com.example.appdevops.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @InjectMocks
    UserService userService;
    
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        
    }

    @Test
    public void test_success_getusername_whenHasUser_shouldSuccess() throws Exception {
        // Arrange
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockUserDto()));
        
        // Act
        GetUserNameResponse response = userService.getUsername(1);

        // Assert
        Assertions.assertEquals(response.username(), "username");

        // Verify: ยืนยันว่าเรียกด้วยพารามิเตอร์ที่คาดไว้
        Mockito.verify(userRepository).findById(1L);
    }

    @Test
    public void test_success_getUserInfo_andUserIsActive() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockUserDto()));
        GetUserResponse response = userService.getUserInfo(1);
        Assertions.assertNotEquals(response, null);
        Assertions.assertEquals(response.status(), "Active");

        // verify
        Mockito.verify(userRepository).findById(1L);
    }

    @Test
    public void test_failed_getusername_whenUserIsNull_shouldThrowNotFound() throws Exception {
        // Arrange
        Mockito.when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        NotFoundException ex = assertThrows(NotFoundException.class, 
            () -> userService.getUsername(99));

        // Assert
        Assertions.assertEquals("User not found", ex.getMessage());
        Assertions.assertEquals("NOT_FOUND", ex.getErrorCode());

        // Verify
        Mockito.verify(userRepository).findById(99L);
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    private User mockUserDto() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setFullName("Test Tester");
        user.setEmail("test@gmail.com");
        user.setMobileNo("08xxxxxxxx");
        user.setActive(true);

        return user;
    }
}
