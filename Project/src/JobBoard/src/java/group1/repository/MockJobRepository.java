package group1.repository;

import group1.bean.JobSearchCriteria;
import group1.bean.Job;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;

/**
 * In memory repository with a static list to hold the job records.
 *
 * This is just for testing until we get a proper database setup.
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
@Repository
public class MockJobRepository implements IJobRepository {

    /**
     * Static list of job records, marked as static so the list can be updated
     * across requests.
     */
    private static Map<Integer, Job> records = new HashMap<Integer, Job>();

    /**
     * Static boolean to denote if we have initialised the static list with
     * example data.
     */
    private static boolean initialised = false;

    /**
     * Injection of the company data accessor to associate example jobs with.
     */
    @Inject
    private ICompanyRepo companyRepo;

    /**
     * Spring post construction hook to make sure that company repo is injected
     * and we can use this instance.
     */
    @PostConstruct
    private void postConstruct() {
        if (initialised) {
            return;
        }
        initialised = true;

        MockDataEntry.insertJobs(this, companyRepo);
    }

    /**
     * Save the job entity to the mock records. If the job does not have an id
     * then an id is created, otherwise the existing record in the records list
     * is updated with the new job.
     *
     * @param job Job entity to insert.
     */
    @Override
    public void saveJob(Job job) {
        if (job.getId() == 0) {
            // Job has no id create one
            job.setId(records.size());
        }
        
        records.put(job.getId(), job);
    }

    /**
     * Search the jobs list for the jobs which match the criteria.
     *
     * @param criteria Job search criteria.
     * @return List of jobs which match the criteria.
     */
    @Override
    public Iterable<Job> findJob(JobSearchCriteria criteria) {
        Iterator<Job> it = records.values().iterator();
        List<Job> results = new ArrayList<Job>();

        while (it.hasNext()) {
            Job j = it.next();

            if (criteria.matches(j)) {
                j.setCompanyRepo(companyRepo);
                results.add(j);
            }
        }

        return Collections.unmodifiableList(results);
    }

    /**
     * Get a job from the repository by ID, if the ID does not exist then null
     * is returned. Makes sure that the job record has a company repository that
     * can be used to retrieve the details about the related company.
     *
     * @param id Id of the job to return.
     * @return The Job entity or null if the Id does not exist.
     */
    @Override
    public Job getJob(int id) {
        Job j = records.get(id);
        if (j != null) {
            j.setCompanyRepo(companyRepo);
        }
        return j;
    }
}
