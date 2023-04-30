package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
@Getter
@Table(name = "TB_COMPANY_ADRESS")
public class CompanyAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String address;

    private final Long number;

    private final String neighborhood;

    private final String state;

    private final String cpf;

    private final String city;
}
