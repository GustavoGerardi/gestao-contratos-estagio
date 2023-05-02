package com.example.demo.services;

import com.example.demo.dto.request.CompanyData;
import com.example.demo.entities.CompanyAddress;
import com.example.demo.entities.CompanyEntity;
import com.example.demo.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity createCompany(CompanyData companyData) {

        CompanyAddress companyAddress = new CompanyAddress(
        companyData.getCompanyAddress().getAddress(),
        companyData.getCompanyAddress().getNeighborhood(),
        companyData.getCompanyAddress().getCep(),
        companyData.getCompanyAddress().getCity(),
        companyData.getCompanyAddress().getState());


        CompanyEntity company = new CompanyEntity(
                companyAddress,
                companyData.getCnpj(),
                companyData.getTelephone(),
                companyData.getEmail(),
                companyData.getBusinessName(),
                companyData.getPartnershipNumber());

        return companyRepository.save(company);
    }

    public CompanyEntity getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }

    public List<CompanyEntity> getAllCompanies() {
        return companyRepository.findAll();
    }
}
