package com.example.demo.dto.request;

import com.example.demo.entities.CompanyAddress;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyData {

    private CompanyAddress companyAddress;

    private String cnpj;

    private final String telephone;

    private final String email;

    private final String businessName;

    private final String partnershipNumber;
}
