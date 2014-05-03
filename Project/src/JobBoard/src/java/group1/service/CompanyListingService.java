/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group1.service;

import group1.bean.Company;
import group1.repository.ICompanyRepo;
import java.util.Locale;
import javax.inject.Inject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Service to return the matched Companys for a
 * given search.
 *
 * @author Steves165
 */
@Service
@Primary
public class CompanyListingService {

    /**
     * Implementation to access the company's which exist in the system.
     */
    private ICompanyRepo compRepo;

    /**
     * Constructor for the service
     * @param compRepo Repository to access the database
     */
    @Inject
    public CompanyListingService(ICompanyRepo compRepo) {
        this.compRepo = compRepo;
    }
    
    /**
     * Get the company object for the given company id.
     * @param id Identifier of the company to retrieve.
     * @return Company object, or null if the company id does not exist.
     */
    public Company getCompany(int id) {
        return this.compRepo.getCompany(id);
    }

    /**
     * Saves a company to the database
     * @param comp Company to save.
     */
    public void saveComp(Company comp) {
        this.compRepo.saveComp(comp);
    }

    /**
     * Search the repository for companies who's names start with the given input
     * string.
     * @param name Start of the companies name to search for.
     * @return List of companies whos names start with the string in name.
     */
    public Iterable<Company> searchName(String name) {
        return this.compRepo.findCompanyByName(name.toLowerCase(Locale.getDefault()));
    }
}
