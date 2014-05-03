package group1.helpers;

import java.util.ArrayList;

/**
 * Helper class to encapsulate the list of test data for salary auto complete tests
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
public class SalaryParameterList extends ArrayList<Object[]> {
    /**
     * Serialisation UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Insert a salary auto complete test into the list
     * @param entry String entry text
     * @param expected Salary integers to be returned
     */
    public void insert(String entry, int ... expected) {
        super.add(new Object[] {entry, expected});
    }
 }
