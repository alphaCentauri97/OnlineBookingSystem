package com.example.OnlineBookingSystem.Online.Booking.System.Controller;

import com.example.OnlineBookingSystem.Online.Booking.System.Model.ServiceProvider;
import com.example.OnlineBookingSystem.Online.Booking.System.Model.Users;
import com.example.OnlineBookingSystem.Online.Booking.System.Service.AdminService;
import com.example.OnlineBookingSystem.Online.Booking.System.Service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/provider")
    public ResponseEntity<ServiceProvider> createServiceProvider(@RequestParam String email, @RequestBody ServiceProvider serviceProvider){
        ServiceProvider createdProvider = adminService.createProvider(email, serviceProvider);
        return ResponseEntity.ok(createdProvider);
    }

    @GetMapping("/provider")
    public List<ServiceProvider> getAllProviders(){
        return adminService.getAllProviders();
    }
    @PostMapping("/login")
    public String login(@RequestBody Users user)
    {
        System.out.println(user);
        return adminService.verify(user);
    }

    @PostMapping("/admin")
    public ResponseEntity<Users> createUser(@RequestBody Users users) {

        return new ResponseEntity<>(adminService.createUser(users), HttpStatus.CREATED);
    }

    @GetMapping("/admin")
    public List<Users> getData() {
        return adminService.getData();
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users updatedUser) {
        return ResponseEntity.ok(adminService.updateUser(id, updatedUser));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        adminService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }


}
