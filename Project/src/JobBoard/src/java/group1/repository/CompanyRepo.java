package group1.repository;

import group1.bean.Company;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;

/**
 * Mock repository to provide encapsulation of company records.
 *
 * @author Steves165
 */
@Repository
public class CompanyRepo implements ICompanyRepo {

    /**
     * Hash map of company records with unique IDs.
     */
    private static Map<Integer, Company> records = new HashMap<Integer, Company>();

    /**
     * Static flag to denote if we have already initalised the test data.
     */
    private static boolean initialised = false;

    /**
     * Injection of our class which is responsible for inserting the test data.
     */
    @Inject
    private MockDataEntry mockDataEntry;

    /**
     * Post construct hook from spring so we get the mockDataEntry injected and
     * can use it to insert the test data.
     */
    @PostConstruct
    private void postConstruct() {
        if (initialised) {
            return;
        }
        initialised = true;
        mockDataEntry.insertCompanies(this);
    }

    /**
     * Get the company record for the given ID.
     *
     * @param id Identifier of the company.
     * @return Company entity or null if the ID does not exist.
     */
    @Override
    public Company getCompany(int id) {
        return records.get(id);
    }

    /**
     * Save a company entity to the database and create an id if the company
     * does not have one. Overwrites the existing record if the id already
     * exists.
     *
     * @param comp Company entity to insert.
     */
    @Override
    public void saveComp(Company comp) {

        if (comp.getId() == 0) {
            // Company has no id create one.
            comp.setId(records.size() + 1);
        }
        records.put(comp.getId(), comp);
    }

    /**
     * Find the companies in the record list where the company name starts with
     * the given input string.
     *
     * @param name Name to search for.
     * @return List of companies whos name starts with the name input.
     */
    @Override
    public Iterable<Company> findCompanyByName(String name) {
        Iterator<Company> it = records.values().iterator();
        List<Company> results = new ArrayList<Company>();

        while (it.hasNext()) {
            Company c = it.next();
            if (c.getCompanyName().toLowerCase(Locale.getDefault()).startsWith(name)) {
                results.add(c);
            }
        }

        return Collections.unmodifiableList(results);
    }
}
