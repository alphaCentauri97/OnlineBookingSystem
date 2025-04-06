package com.example.OnlineBookingSystem.Online.Booking.System.Controller;

import com.example.OnlineBookingSystem.Online.Booking.System.Model.Availability;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.ServiceProvider;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.Users;
import com.example.OnlineBookingSystem.Online.Booking.System.Service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService providerService;

    @PostMapping("/availability")
    public ResponseEntity<Availability> createProviderAvailability(@RequestParam String email, @RequestBody Availability availability){
        Availability serviceAvailability = providerService.createAvailability(email, availability);
        return ResponseEntity.ok(serviceAvailability);
    }

    @GetMapping("/availability")
    public List<Availability> getAllAvailability(){
        return providerService.getAllAvailability();
    }
}
