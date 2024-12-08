package com.fargaislam.firstJobApp.company.impl;

import com.fargaislam.firstJobApp.company.ComapanyRepository;
import com.fargaislam.firstJobApp.company.Company;
import com.fargaislam.firstJobApp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private ComapanyRepository comapanyRepository;

    public CompanyServiceImpl(ComapanyRepository comapanyRepository) {
        this.comapanyRepository = comapanyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return comapanyRepository.findAll();
    }

    @Override
    public Boolean updateCompany(Company updatedCompany,Long id) {
        Optional<Company> optionalCompany=comapanyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company company=optionalCompany.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            company.setJobs(updatedCompany.getJobs());
            comapanyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        comapanyRepository.save(company);
    }

    @Override
    public Boolean deleteCompanyById(Long id) {
        if(comapanyRepository.existsById(id)){
            comapanyRepository.deleteById(id);
            return true;
        }
        return false;


    }

    @Override
    public Company getCompanyById(Long id) {

       return comapanyRepository.findById(id).orElse(null);
    }
}
