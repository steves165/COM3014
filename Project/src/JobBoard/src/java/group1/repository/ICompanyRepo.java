package group1.repository;

import group1.bean.Company;

/**
 * Interface for storing the company records.
 *
 * @author Steves165
 */
public interface ICompanyRepo {

    /**
     * Get a company by its ID, can return null if the id does not exist.
     *
     * @param id Company ID to retrieve.
     * @return Company entity or null if the company does not exist.
     */
    public Company getCompany(int id);

    /**
     * Saves the Company to the repository, if the company already exists then
     * it should update the company.
     *
     * @param comp Company entity to insert.
     */
    public void saveComp(Company comp);

    /**
     * Find a list of companies where their name begins with the given input
     * string.
     *
     * @param name Start of the name to match against.
     * @return List of companies which match the criteria.
     */
    public Iterable<Company> findCompanyByName(String name);
}
