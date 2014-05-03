package group1.repository;

import group1.bean.Application;

/**
 * Interface for the application storage.
 *
 * @author andyaltwasser
 */
public interface IApplicationRepo {

    /**
     * Return all the application which a user can view from the records
     *
     * @param username Username of the applications to retrieve.
     * @return Iterable list of all the applications which the user can view.
     */
    public Iterable<Application> findForUser(String username);

    /**
     * Save a application to the repository. If the application already exists
     * it should be updated with the values from the entity otherwise a new
     * record should be inserted and the application identifier updated.
     *
     * @param app Application entity to insert.
     */
    public void saveApp(Application app);
}
