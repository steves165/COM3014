package group1.api;

import group1.bean.Job;
import group1.bean.JobSearchCriteria;
import group1.service.JobListingService;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller to provide an API for accessing jobs in a
 * RESTful way. The prefix of all the API calls is /job
 * depending on how this controller is bound the /job may
 * be prefixed with another pattern depending on how the
 * spring dispatcher is bound to the controller.
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
@Controller
@RequestMapping("/job")
public class JobRestController {

    /**
     * Service to access the job listings.
     */
    @Inject
    private JobListingService jobService;

    /**
     * API call to search for jobs. Search criteria are provided as GET variables
     * on the URL.
     *
     * @param term String term to match against any string field within the job.
     * @param salary Minimum salary of the returned jobs.
     *
     * @return List of jobs which match the criteria.
     */
    @RequestMapping(value="search", method = RequestMethod.GET)
    public Iterable<Job> search(@RequestParam("term") String term, @RequestParam("salary") Integer salary) {
        JobSearchCriteria criteria = new JobSearchCriteria(term, salary);
        Iterable<Job> results = jobService.searchJobs(criteria);
        return results; 
    }


}
