package peaksoft.service;

import peaksoft.model.Company;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    void saveCompany(Company company) throws IOException;//

    void updateCompany(Company company);//

    List<Company> getAllCompanies(Long id);//

    Company getCompanyById(Long id);//

    void deleteCompany(Long id);//
}
