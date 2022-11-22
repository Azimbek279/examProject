package peaksoft.repository;

import peaksoft.model.Company;

import java.util.List;

public interface CompanyRepository {

    void saveCompany(Company company);//

    void updateCompany(Company company);//

    List<Company> getAllCompanies();//

    Company getCompanyById(Long id);//

    void deleteCompany(Long id);//
}
