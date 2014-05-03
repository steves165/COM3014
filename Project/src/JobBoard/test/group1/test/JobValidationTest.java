package group1.test;

import group1.bean.Job;
import group1.helpers.ValidationWrapper;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.springframework.validation.ObjectError;

/**
 * Unit test class for testing the Spring binding validation on a Job bean.
 * Complements the JobBlankStringTest test cases.
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
public class JobValidationTest {

    /**
     * Spring validator to use.
     */
    private ValidationWrapper<Job> validator = new ValidationWrapper<Job>();

    /**
     * Helper method to execute Spring validation on a Job.
     * @param job Job to validate.
     * @return List of validation errors.
     */
    protected List<ObjectError> validate(Job job) {
        return validator.validate(job);
    }

    /**
     * Helper to return a completely valid job which can be modified.
     * @return A new valid job.
     */
    public static Job validJob() {
        Job job = new Job("Java Developer", 0, "IT",
                "Java programmer required for developing web applications",
                new String[] { "Java", "Oracle" }, new String[] { "Linux" },
                "Surrey", 20000, "Contract");

        // Sanity check to make sure the job is actually valid
        ValidationWrapper<Job> validator = new ValidationWrapper<Job>();
        List<ObjectError> errors = validator.validate(job);
        assertEquals("The 'valid' job returns validation errors, modify the job in validJob() if the validation criteria is correct." + errors.toString(),
                0, errors.size());

        return job;
    }

    /**
     * Simple test to make sure that > 0 errors are returned for a blank job.
     */
    @Test
    public void blankInvalid() {
        Job job = new Job();
        List<ObjectError> error = validate(job);
        assertThat(error.size(), greaterThan(0));
    }

    /**
     * Make sure that HTML tags are not allowed in the title, specifically
     * against cross site scripting attacks.
     */
    @Test
    public void titleDisallowHtml() {
        Job job = validJob();

        job.setTitle("<h1>Software developer</h1>");
        List<ObjectError> error = validate(job);
        assertEquals(1, error.size());

        job.setTitle("<script src=http://mrbad.com/xss.js></script>");
        error = validate(job);
        assertEquals(1, error.size());

        job.setTitle("<img src=http://mrbad.com/xss.js></script>");
        error = validate(job);
        assertEquals(1, error.size());

        job.setTitle("<img src=http://mrbad.com/xss.js></script>");
        error = validate(job);
        assertEquals(1, error.size());
    }
    /*@Test
    public void titleDisallowHtml() {
        Job job = validJob();
        job.setTitle("<h1>Software developer</h1>");
        List<ObjectError> error = validate(job);
        assertEquals(0, error.size());
    }*/
}