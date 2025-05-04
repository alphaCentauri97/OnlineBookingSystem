package com.example.OnlineBookingSystem.Online.Booking.System.Admin;

import com.example.OnlineBookingSystem.Online.Booking.System.Model.Role;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.Users;
import com.example.OnlineBookingSystem.Online.Booking.System.Repository.UserRepository;
import com.example.OnlineBookingSystem.Online.Booking.System.Service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    void testCreateUsers(){
        Users users = new Users();
        users.setId(1L);
        users.setEmail("test@gmail.com");
        users.setFirstname("Alpha");
        users.setLastname("Centauri");
        users.setRole(Role.valueOf("ADMIN"));

        when(userRepository.save(users)).thenReturn(users);

        Users savedUsers = adminService.createUser(users);

        assertNotNull(savedUsers);
        assertEquals("Alpha",savedUsers.getFirstname());
        verify(userRepository, times(1)).save(users);
    }
}
