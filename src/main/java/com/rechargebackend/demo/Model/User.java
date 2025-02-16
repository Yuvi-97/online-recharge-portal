package com.rechargebackend.demo.Model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String firstName;
    private String lastname;
    private String phoneNumber;
    private String address;
}