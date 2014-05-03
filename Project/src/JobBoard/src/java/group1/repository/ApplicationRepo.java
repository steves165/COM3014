package group1.repository;

import group1.bean.Application;
import group1.bean.Job;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;

/**
 * Mock repository to provide storage for applications.
 *
 * @author andyaltwasser
 */
@Repository
public class ApplicationRepo implements IApplicationRepo {

    /**
     * Hash map of static applications which exist in the system.
     */
    private static Map<Integer, Application> records = new HashMap<Integer, Application>();

    /**
     * Provide access to the job listing so we can see which user should be
     * allowed to view an application.
     */
    @Inject
    private IJobRepository jobRepository;

    /**
     * Save an application to the record list creating an id if required.
     *
     * @param app Application object to save.
     */
    @Override
    public void saveApp(Application app) {
        if (app.getId() == 0) {
            // Application has no id create one.
            app.setId(records.size() + 1);
        }
        records.put(app.getId(), app);
    }

    /**
     * Find all of the applications which a user can access for the given
     * username. The user should only be able to access applications for jobs
     * which they have created.
     *
     * @param user Username to check that the application job was created with.
     * @return List of applications which the user is authorised to view.
     */
    @Override
    public Iterable<Application> findForUser(String user) {
        List<Application> applications = new ArrayList<Application>();

        Iterator<Application> it = records.values().iterator();
        while (it.hasNext()) {
            Application app = it.next();

            Job job = jobRepository.getJob(app.getJobId());

            if (job != null && job.getCreateUser().equals(user)) {
                applications.add(app);
            }
        }

        return Collections.unmodifiableCollection(applications);
    }
}
