package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_COMPANY_ADDRESS")
public class CompanyAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String neighborhood;

    private String cep;

    private String city;

    private String state;

    public CompanyAddress(String address, String neighborhood, String cep, String city, String state) {
        this.address = address;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.state = state;
    }
}