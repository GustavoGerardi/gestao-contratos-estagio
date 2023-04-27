package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "companies")
@Getter
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final Long companyAddressId;

    private final String cnpj;

    private final String contact;

    private final String email;

    private final String businessName;

    private final String partnershipNumber;
}
