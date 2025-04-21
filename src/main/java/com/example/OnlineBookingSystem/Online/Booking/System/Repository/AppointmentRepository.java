package com.example.OnlineBookingSystem.Online.Booking.System.Repository;

import com.example.OnlineBookingSystem.Online.Booking.System.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
