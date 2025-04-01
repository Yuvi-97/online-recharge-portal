package com.rechargebackend.demo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "RechargePlan")
public class RechargePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String planName;
    private String planDescription;
    private int planDuration;
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
