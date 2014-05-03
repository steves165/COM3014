package group1.repository;

import group1.bean.JobSearchCriteria;
import group1.bean.Job;

/**
 * Interface for accessing the Job entities within the database
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
public interface IJobRepository {

    /**
     * Get a job from the repository by ID, if the ID does not exist then null
     * is returned.
     *
     * @param id Id of the job to return.
     * @return The Job entity or null if the Id does not exist.
     */
    public Job getJob(int id);

    /**
     * Save a job to the repository. If the job already exists it should be
     * updated with the values from the entity otherwise a new record should be
     * inserted and the jobs identifier updated.
     *
     * @param job Job entity to insert.
     */
    public void saveJob(Job job);

    /**
     * Search for the jobs which match the passed in criteria in the repository.
     *
     * @param criteria Criteria to match against.
     * @return Iterable list of jobs which match the search criteria.
     */
    public Iterable<Job> findJob(JobSearchCriteria criteria);
}
