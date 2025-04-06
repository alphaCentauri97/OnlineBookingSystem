package com.example.OnlineBookingSystem.Online.Booking.System.Service;

import com.example.OnlineBookingSystem.Online.Booking.System.Exception.UserNotFoundException;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.Availability;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.Role;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.ServiceProvider;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.Users;
import com.example.OnlineBookingSystem.Online.Booking.System.Repository.AdminRepository;
import com.example.OnlineBookingSystem.Online.Booking.System.Repository.AvailabilityRepository;
import com.example.OnlineBookingSystem.Online.Booking.System.Repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProviderService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ServiceProviderRepository providerRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;


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
}
