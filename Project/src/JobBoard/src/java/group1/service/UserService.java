package group1.service;

import group1.bean.User;
import group1.repository.IUserRepository;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Provide a service to register and authenticate users accounts on the job
 * board.
 * @author ds00148
 */
@Service
public class UserService implements UserDetailsService {

    /**
     * Data accessor for the users.
     */
    @Inject
    private IUserRepository dao;

    /**
     * Inject the object to hash the password of the user.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Method required by spring-security to get the users details for authentication.
     * @param username Unique username to retrieve the details for.
     * @return User object from the database for the given username, never null.
     * @throws UsernameNotFoundException If the username could not be found in the database.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found " + username);
        }
        return user;
    }

    /**
     * Method to get the actual user entity stored by the data access layer.
     * This method will return null if the user does not exist.
     * @param username Username identifier of the user to retrieve.
     * @return User entity or null if the user does not exist.
     */
    public User getUser(String username) {
        return dao.getUser(username);
    }

    /**
     * Create a completely new user given the user entity. The user
     * object should have the password in plaintext and this method
     * will hash the password before adding the user to the data access
     * layer.
     * @param user User entity to add to the data access layer.
     */
    public void createUser(User user) {
        // Overwrite the passed
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.createUser(user);
    }
}
