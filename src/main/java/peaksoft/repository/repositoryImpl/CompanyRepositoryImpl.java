package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCompany(Company company) throws IOException{
        if (company.getCompanyName().toLowerCase().length()>0 && company.getLocatedCountry().toLowerCase().length()>0) {
            for (Character i : company.getCompanyName().toLowerCase().toCharArray()) {
                if (!Character.isLetter(i)) {
                    throw new IOException("no numbers in company name");
                }
            }
            for (Character i : company.getLocatedCountry().toLowerCase().toCharArray()) {
                if (!Character.isLetter(i)) {
                    throw new IOException("no numbers in company located country");
                }
            }
        }
        entityManager.persist(company);
        System.out.println("1");
    }

    @Override
    public void updateCompany(Company company) {
        entityManager.merge(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return entityManager.createQuery("from Company",Company.class).getResultList();
    }

    @Override
    public Company getCompanyById(Long id) {
        return entityManager.find(Company.class,id);
    }

    @Override
    public void deleteCompany(Long id) {
        entityManager.remove(entityManager.find(Company.class, id));
    }
}
