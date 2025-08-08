package com.learning.payment.service.application.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Customer {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
    private Double balance;
}
