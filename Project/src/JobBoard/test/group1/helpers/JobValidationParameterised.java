package group1.helpers;

import group1.bean.Job;
import group1.test.JobValidationTest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Helper class which can be inherited by JUnit tests which need to be
 * run on all string fields of Job. The Paramaterized runner should be used.
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
@Ignore
public class JobValidationParameterised {

    /**
     * Validation helper.
     */
    private ValidationWrapper<Job> validator;

    /**
     * Test string we are using.
     */
    private String testData;

   /**
     * Use reflection to get the property setter and invoke it with the test
     * data on a valid job in order to not have the same assert in multiple
     * methods.
     * @param setterName Method name of the setter to use.
     */
    private void assertHelper(String setterName) {
        try {
            Method setter = Job.class.getMethod(setterName, String.class);
            Job job = JobValidationTest.validJob();
            setter.invoke(job, testData);
            assertThat("No error returned when `" + testData + "' set using setter `" + setterName + "' of job entity",
                            validator.validate(job).size(), greaterThan(0));
        } catch (NoSuchMethodException ex) {
            fail(ex.toString());
        } catch (SecurityException ex) {
            fail(ex.toString());
        } catch (IllegalArgumentException ex) {
            fail(ex.toString());
        } catch (InvocationTargetException ex) {
            fail(ex.toString());
        } catch (IllegalAccessException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Constructor for the parameterised test. Tests to assert that errors are
     * thrown for blank data.
     * @param testData Test string to use.
     */
    public JobValidationParameterised(String testData) {
       this.testData = testData;
       this.validator = new ValidationWrapper<Job>();
    }

    /**
     * Test that blank entries on the title field are rejected.
     */
    @Test
    public void testTitle() {
        assertHelper("setTitle");
    }
    
    /**
     * Test that blank entries on the department field are rejected.
     */
    @Test
    public void testDepartment() {
        assertHelper("setDepartment");
    }

    /**
     * Test that blank entries on the description field are rejected.
     */
    @Test
    public void testDescription() {
        assertHelper("setDescription");
    }

    /**
     * Test that blank entries on the location field are rejected.
     */
    @Test
    public void testLocation() {
        assertHelper("setLocation");
    }

    /**
     * Test that blank entries on the contract field are rejected.
     */
    @Test
    public void testContract() {
        assertHelper("setContract");
    }
}
