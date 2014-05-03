package group1.test;

import group1.helpers.JobValidationParameterised;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Class to validate all the items which should not allow blank data to be entered.
 * Complements and depends on the JobValidationTest test cases, specifically the
 * JobValidationTest.validJob() method.
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
@RunWith(Parameterized.class)
public class JobBlankStringTest extends JobValidationParameterised {

    /**
     * Parameterised test data generator, returns a list of strings which should
     * be considered as no data being entered.
     * @return Collection of strings which should be considered as blank.
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][] {
            { null }, { "" },
            { " "  }, { "    " },
            { "\n" }, { "\r" },
            { "\t" }, { "\r\n" },
        });
    }

    /**
     * Constructor for the parameterised test. Tests to assert that errors are
     * thrown for blank data.
     * @param testData Test string to use.
     */
    public JobBlankStringTest(String testData) {
       super(testData);
    }
}