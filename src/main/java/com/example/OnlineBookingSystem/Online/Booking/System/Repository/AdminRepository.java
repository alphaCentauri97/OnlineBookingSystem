package com.example.OnlineBookingSystem.Online.Booking.System.Repository;


import com.example.OnlineBookingSystem.Online.Booking.System.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Users,Long> {

   Optional<Users> findByEmail(String email);

}
