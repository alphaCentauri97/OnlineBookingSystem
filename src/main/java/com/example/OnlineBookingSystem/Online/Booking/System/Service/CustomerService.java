package com.example.OnlineBookingSystem.Online.Booking.System.Service;

import com.example.OnlineBookingSystem.Online.Booking.System.Exception.ResourceNotFoundException;
import com.example.OnlineBookingSystem.Online.Booking.System.Exception.UserNotFoundException;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.*;
import com.example.OnlineBookingSystem.Online.Booking.System.Repository.UserRepository;
import com.example.OnlineBookingSystem.Online.Booking.System.Repository.AppointmentRepository;
import com.example.OnlineBookingSystem.Online.Booking.System.Repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ServiceProviderRepository providerRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(String customerEmail, String providerEmail, Appointment appointment) {
        Optional<Users> existingCustomer = userRepository.findByEmail(customerEmail);
        Optional<Users> existingProvider = userRepository.findByEmail(providerEmail);

        Optional<ServiceProvider> provider = providerRepository.findByUser(existingProvider.get());

        if (existingCustomer.isEmpty() && existingProvider.isEmpty()){
            throw new UserNotFoundException("Users are not found");
        }
        appointment.setCustomer(existingCustomer.get());
        appointment.setServiceProvider(provider.get());

        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long appointmentId, String customerMail, AppointmentStatus status) {

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new ResourceNotFoundException("Appointment not found"));

        Optional<Users> customer = Optional.ofNullable(userRepository.findByEmail(customerMail).orElseThrow(()
                -> new UserNotFoundException("User not found")));

        Users existing_customer = customer.get();

        if(existing_customer.getRole() == Role.CUSTOMER && status != AppointmentStatus.CANCELED){
             throw new UserNotFoundException("Only admin can update data");
        }
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllApointments() {
        return appointmentRepository.findAll();
    }
}
