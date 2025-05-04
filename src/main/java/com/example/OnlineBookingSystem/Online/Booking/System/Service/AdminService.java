package com.example.OnlineBookingSystem.Online.Booking.System.Service;

import com.example.OnlineBookingSystem.Online.Booking.System.Exception.UserAlreadyExistsException;
import com.example.OnlineBookingSystem.Online.Booking.System.Exception.UserNotFoundException;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.ServiceProvider;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.Users;
import com.example.OnlineBookingSystem.Online.Booking.System.Repository.UserRepository;
import com.example.OnlineBookingSystem.Online.Booking.System.Repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository adminRepository;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ServiceProviderRepository providerRepository;

    public Users createUser(Users users) {
        Optional<Users> existingUser = adminRepository.findByEmail(users.getEmail());

        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("Username is already exist.");
        }
            return adminRepository.save(users);
    }

    public List<Users> getData() {
        return adminRepository.findAll();
    }

    public Users updateUser(Long id, Users updatedUser) {
        Users existingUser = adminRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User is not found by id: "+id));
        existingUser.setFirstname(updatedUser.getFirstname());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setAppointments(updatedUser.getAppointments());
        existingUser.setVerified(updatedUser.isVerified());

        return adminRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        Users existingUser = adminRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User is not found by id: "+id));
        adminRepository.delete(existingUser);
    }



    public ServiceProvider createProvider(String email, ServiceProvider serviceProvider) {

        Optional<Users> existingUser = adminRepository.findByEmail(email);

        if(existingUser.isEmpty()){
            throw new UserNotFoundException("User is not available");
        }
        Users users = existingUser.get();


        serviceProvider.setUser(users);
        return providerRepository.save(serviceProvider);
    }

    public List<ServiceProvider> getAllProviders() {

        return providerRepository.findAll();
    }

    public String verify(Users user) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(authentication.isAuthenticated()){
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

            String token = jwtService.generateToken(userDetails);
            return token;
        }
        return "fail";
    }
}
