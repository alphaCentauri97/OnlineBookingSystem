package com.example.OnlineBookingSystem.Online.Booking.System.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.apache.catalina.User;

import java.util.List;

@Entity
@Data
public class ServiceProvider {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long Id;

        @OneToOne
        @JoinColumn(name = "user_id", nullable = false)
        private Users user;

        @NotNull(message = "category should not be null")
        private String category;

        @NotNull(message = "location should not be null")
        private String location;

        @NotNull(message = "experience should not be null")
        private Integer experience;

        @NotBlank( message = "description should not be null")
        @Column(columnDefinition = "TEXT")
        private String description;

        @JsonIgnore
        @OneToMany(mappedBy = "serviceProvider", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Availability> availabilitySlots;

        // Service provider's appointments
        @JsonIgnore
        @OneToMany(mappedBy = "serviceProvider", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Appointment> appointments;

}
