package group1.test;

import group1.helpers.SalaryParameterList;
import group1.bean.SalarySearchItem;
import group1.service.SalarySearchService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Parameterised test class to test the auto complete functionality of the
 * salary search service.
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
@RunWith(Parameterized.class)
public class SalarySearchServiceAutocompleteTest {
    /**
     * Service to test against
     */
    private SalarySearchService service;

    /**
     * String entry the user has entered
     */
    private String entry;

    /**
     * Expected return items
     */
    private List<SalarySearchItem> expected;

    /**
     * constructor for the paramertised test
     * @param entry String entry of the user entry
     * @param expectedResults Variable number of arguments with the expected salary results
     * as integers.
     */
    public SalarySearchServiceAutocompleteTest(String entry, int ... expectedResults) {
        this.service = new SalarySearchService();
        this.entry = entry;
        this.expected = new ArrayList<SalarySearchItem>();

        // Convert the integers to expected salary search terms
        for(int i = 0; i < expectedResults.length; i++) {
            this.expected.add(new SalarySearchItem(Integer.valueOf(expectedResults[i])));
        }
    }

    /**
     * Method to return a list of test parameters to insert into the constructor.
     * @return Collection of Object[].
     */
    @Parameters(name="{0}") // Feature of JUnit >= 4.11, so we get meaningful test names
    public static Collection<Object[]> parameters() {
        SalaryParameterList testData = new SalaryParameterList();

        testData.insert("1", 10000, 100000);
        testData.insert("10", 10000, 100000);
        testData.insert("10,", 10000);
        testData.insert("10,0", 10000);
        testData.insert("100,", 100000);
        // Fully complete entry
        testData.insert("10,000", 10000);

        testData.insert("12", 12000, 120000);
        testData.insert("127", 12700, 127000);
        testData.insert("127,", 127000);
        testData.insert("15", 15000, 150000);
        testData.insert("8", 8000, 80000);
        testData.insert("1234", 12340, 123400);
        testData.insert("2534", 25340, 253400);
        testData.insert("012", 12000, 120000);

        // Fail cases should return nothing
        testData.insert("-12");
        testData.insert("abc");

        // Quick loop to insert all of the above test cases
        // with a £ sign
        SalaryParameterList testDataSymbol = new SalaryParameterList();
        Iterator<Object[]> iterator = testData.iterator();
        while(iterator.hasNext()) {
            // Cloned otherwise we just modify the existing test
            Object[] next = iterator.next().clone();
            next[0] = "£" + next[0];
            testDataSymbol.add(next);
        }

        testData.addAll(testDataSymbol);

        return testData;
    }

    /**
     * Test method to run the test data through the salary search service
     */
    @Test
    public void testAutoComplete() {
        Collection<SalarySearchItem> ret = service.autoComplete(entry);

        // Convert the collection into a list as Junit assertEquals
        // does not work correctly for unmodifable collections.
        List<SalarySearchItem> retList = new ArrayList<SalarySearchItem>(ret);

        assertEquals(expected, retList);
    }
}

