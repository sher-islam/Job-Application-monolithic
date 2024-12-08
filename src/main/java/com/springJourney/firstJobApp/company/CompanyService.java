package com.fargaislam.firstJobApp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Boolean updateCompany(Company updatedCompany,Long id);
    void createCompany(Company company);
    Boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);

}
