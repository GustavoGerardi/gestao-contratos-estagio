package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Getter
@Data
@Table(name = "TB_COMPANIES")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_address_id")
    private CompanyAddress companyAddress;

    private String cnpj;

    private String telephone;

    private String email;

    private String businessName;

    private String partnershipNumber;

    public CompanyEntity() {}

    public CompanyEntity(CompanyAddress companyAddress, String cnpj, String telephone, String email, String businessName, String partnershipNumber) {
        this.companyAddress = companyAddress;
        this.cnpj = cnpj;
        this.telephone = telephone;
        this.email = email;
        this.businessName = businessName;
        this.partnershipNumber = partnershipNumber;
    }
}
