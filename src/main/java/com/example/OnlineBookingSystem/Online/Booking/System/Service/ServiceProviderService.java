package com.example.OnlineBookingSystem.Online.Booking.System.Service;

import com.example.OnlineBookingSystem.Online.Booking.System.Exception.UserNotFoundException;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.Availability;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.ServiceProvider;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.Users;
import com.example.OnlineBookingSystem.Online.Booking.System.Repository.UserRepository;
import com.example.OnlineBookingSystem.Online.Booking.System.Repository.AvailabilityRepository;
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
public class ServiceProviderService {

    @Autowired
    private UserRepository adminRepository;

    @Autowired
    private ServiceProviderRepository providerRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;


    public Availability createAvailability(String email, Availability availability) {
        Optional<Users> existingUser = adminRepository.findByEmail(email);
        Optional<ServiceProvider> existingProvider = providerRepository.findByUser(existingUser.get());

        if(existingProvider.isEmpty()){
            throw new UserNotFoundException("Provider is not Available");
        }
        availability.setServiceProvider(existingProvider.get());
        return availabilityRepository.save(availability);
    }

    public List<Availability> getAllAvailability() {
        return availabilityRepository.findAll();
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
