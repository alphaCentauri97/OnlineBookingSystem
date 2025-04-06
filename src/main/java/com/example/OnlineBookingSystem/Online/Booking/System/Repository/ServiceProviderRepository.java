package com.example.OnlineBookingSystem.Online.Booking.System.Repository;

import com.example.OnlineBookingSystem.Online.Booking.System.Model.ServiceProvider;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider,Long> {

    Optional<ServiceProvider> findByUser(Users users);
}
