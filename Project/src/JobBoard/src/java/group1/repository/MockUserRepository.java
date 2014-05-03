package group1.repository;

import group1.bean.User;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * Provide an in-memory user list for authentication.
 * @author dan
 */
@Repository
public class MockUserRepository implements IUserRepository {

    /**
     * User map from username to an actual user record.
     */
    private static Map<String, User> records = new HashMap<String, User>();

    /**
     * Static property to denote if we have already initialised the records
     * with default data.
     */
    private static boolean isInitialised = false;

    /**
     * Default constructor to create the repository. Currently adds a single
     * user "com3014" with the password "123456".
     */
    public MockUserRepository() {
        if (!isInitialised) {
            isInitialised = true;
            // The static record map has not been initalised with example data.

            // Ideally the DAO would not depend on a password encoder.
            // The service should handle it, but we are only adding dummy data.
            String username = "com3014";
            StandardPasswordEncoder encoder = new StandardPasswordEncoder();
            String hash = encoder.encode("123456");
            records.put(username, new User(username, hash));

        }
    }

    /**
     * Get a single user record from the map. Returns a user object if the
     * username exists, otherwise null is returned.
     * @param username Unique username of the user to return.
     * @return User record for the username or null if the user does not exist.
     */
    @Override
    public User getUser(String username) {
        if (records.containsKey(username)) {
            return records.get(username);
        }
        return null;
    }

    /**
     * Create a new user into the repository.
     * @param user User entry to create.
     */
    @Override
    public void createUser(User user) {
        records.put(user.getUsername(), user);
    }
}
