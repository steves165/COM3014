package group1.service;

import group1.bean.Job;
import group1.repository.IJobRepository;
import group1.bean.JobSearchCriteria;
import javax.inject.Inject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Service to return the matched jobs for a
 * given search criteria.
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
@Service
@Primary
public class JobListingService {

    /**
     * Implementation to access the jobs which exist in the system.
     */
    @Inject
    private IJobRepository jobRepository;

    /**
     * Save a job into the database handling any key creation.
     * @param job Job to save.
     */
    public void saveJob(Job job) {
        this.jobRepository.saveJob(job);
    }

    /**
     * Create the job search criteria object and pass it to the data access
     * layer and return a list of jobs which match the criteria.
     * @param criteria Object which defines the search criteria.
     * @return List of jobs which match the criteria.
     */
    public Iterable<Job> searchJobs(JobSearchCriteria criteria) {
        return this.jobRepository.findJob(criteria);
    }

    /**
     * Get a job from the repository using a given ID, returns null if the
     * ID does not exist.
     * @param id Identifier of the job to retrieve.
     * @return Job entity or null if the company does not exist.
     */
    public Job getJob(int id) {
        return this.jobRepository.getJob(id);
    }
}
