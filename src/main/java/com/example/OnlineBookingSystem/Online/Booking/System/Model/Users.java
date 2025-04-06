package com.example.OnlineBookingSystem.Online.Booking.System.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.UserTransaction;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;


@Entity
@Data
public class Users  {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @NotNull(message = "First name should not be null")
        private String firstname;

        @NotNull(message = "Last name should not be null")
        private String lastname;

        @NotNull(message = "Email should not be null")
        @Column(unique = true)
        private String email;

        @NotNull(message = "Password should not be null")
        private String password;

        @NotNull(message = "Role should not be null")
        @Enumerated(EnumType.STRING)
        private Role role;

        private boolean isVerified;

        @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
        @JsonIgnore
        private List<Appointment> appointments;

}
