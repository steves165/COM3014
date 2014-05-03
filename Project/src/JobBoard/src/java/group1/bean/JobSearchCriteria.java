package group1.bean;

import java.util.Iterator;
import java.util.Locale;

/**
 * Entity class to hold the criteria for searching for jobs and to specify
 * how to match a job entity against the criteria.
 * @author ds00148
 */
public class JobSearchCriteria {

    /**
     * Freehand string criteria list to match against, in an OR fashion.
     */
    private String term;

    /**
     * Minimum salary of a job.
     */
    private Integer min_salary;

    /**
     * Constructor for the criteria.
     *
     * @param term String term to which one of the string fields of the
     * job must contain in order to be returned. Performed in a case insensitive
     * manor. If this term is null or blank then it is ignored.
     *
     * @param min_salary Minimum salary of the jobs to return. This is a
     * greater than or equal to match and can be null to ignore this criteria.
     * A job is only returned if it contains the string defined in term AND the
     * salary criteria is fulfilled.
     */
    public JobSearchCriteria(String term, Integer min_salary) {
        this.term = term;
        this.min_salary = min_salary;
    }

    /**
     * Very basic string matching to see if any of the stringCriteria
     * words exist in the string.
     * @param str String to see if the criteria equals.
     * @return True if the string criteria matches, false otherwise.
     */
    private boolean containsStringCriteria(String str) {
        if (str == null) {
            // Don't try to match a null string, otherwise we will get
            // null pointer exception.
            return false;
        }

        if (str.toLowerCase(Locale.getDefault()).contains(this.term)) {
            return true;
        }

        return false;
    }

    /**
     * Loop over an iterator of string and return if any of the items matches
     * the string criteria.
     * @param it Iterator of strings to match
     * @return True if the criteria matches, False otherwise.
     */
    private boolean listStringContains(Iterator<String> it) {
        while(it.hasNext()) {
            String s = it.next();

            if (containsStringCriteria(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Perform the string field matches on the job.
     * @param job job entity to match the string criteria against.
     * @return True if the job matches the criteria false otherwise.
     */
    private boolean stringMatches(Job job) {
        // Do not perform these matches if we have no string to match
        if (this.term == null || this.term.trim().equals(""))
        {
            return false;
        }

        if (containsStringCriteria(job.getTitle())) {
            return true;
        }

        if (containsStringCriteria(job.getContract())) {
            return true;
        }

        if (containsStringCriteria(job.getDepartment())) {
            return true;
        }

        if (containsStringCriteria(job.getDescription())) {
            return true;
        }

        if (containsStringCriteria(job.getLocation())) {
            return true;
        }

        if (listStringContains(job.getDesiredSkills().iterator())) {
            return true;
        }

        if (listStringContains(job.getRequiredSkills().iterator())) {
            return true;
        }

        return false;
    }

    /**
     * Method to take the criteria and to return if the passed in job should
     * be returned for this criteria.
     * @param job Job to match the criteria against.
     * @return True if the job should be in the result set, false otherwise.
     */
    public boolean matches(Job job) {

        boolean stringMatch = stringMatches(job);

        // If we have no minimum salary or the string is not matched
        // return the string match result
        if (min_salary == null || !stringMatch) {
            return stringMatch;
        }

        // If we have a string match make sure the min salary is fulfilled.
        if (job.getSalary() >= min_salary) {
            return true;
        }

        return false;
    }
}
