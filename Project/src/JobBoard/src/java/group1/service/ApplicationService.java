package group1.service;

import group1.bean.Application;
import group1.repository.IApplicationRepo;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 * Service to provide access to the job application repository.
 * @author andyaltwasser
 */
@Service
public class ApplicationService {

    /**
     * Implementation to access the jobs which exist in the system.
     */
    @Inject
    private IApplicationRepo appRepository;

    /**
     * Get a list of applications which are associated with the user.
     *
     * @param username User to restrict the returned applications to.
     * @return Iterable list of applications
     */
    public Iterable<Application> getApplicationsForUser(String username) {
        return this.appRepository.findForUser(username);
    }

    /**
     * Save a application into the database handling any key creation.
     *
     * @param app Application to save.
     */
    public void saveApp(Application app) {
        this.appRepository.saveApp(app);
    }
}
