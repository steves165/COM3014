package group1.repository;

import group1.bean.User;

/**
 * Interface for accessing user records.
 * @author dan
 */
public interface IUserRepository {

    /**
     * Get the user record for the given username in the database.
     * @param username Username of the user to retrieve.
     * @return User record or null if the username does not exist.
     */
    public User getUser(String username);

    /**
     * Create a user entity in the database.
     * @param user User to create.
     */
    public void createUser(User user);
}
