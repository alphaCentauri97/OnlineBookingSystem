package com.example.OnlineBookingSystem.Online.Booking.System.Controller;

import com.example.OnlineBookingSystem.Online.Booking.System.Model.Appointment;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.AppointmentStatus;
import com.example.OnlineBookingSystem.Online.Booking.System.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/appointment")
    public ResponseEntity<Appointment> createAppointment(@RequestParam String customer_email,
                                                         @RequestParam String provider_email, @RequestBody Appointment appointment) {

        return ResponseEntity.ok(customerService.createAppointment(customer_email,provider_email,appointment));
    }

    @PutMapping("/appointment")
    public ResponseEntity<Appointment> updateAppointment(@RequestParam Long appointment_id,
                                                        @RequestParam AppointmentStatus status,
                                                         @RequestParam String customer_mail){

        return ResponseEntity.ok(customerService.updateAppointment(appointment_id,customer_mail,status));

    }

    @GetMapping("/appoitnment")
    public List<Appointment> getAppointments(){
        return customerService.getAllApointments();
    }
}
