package peaksoft.repository;

import peaksoft.model.Company;

import java.io.IOException;
import java.util.List;

public interface CompanyRepository {

    void saveCompany(Company company)throws IOException;//

    void updateCompany(Company company);//

    List<Company> getAllCompanies();//

    Company getCompanyById(Long id);//

    void deleteCompany(Long id);//
}
