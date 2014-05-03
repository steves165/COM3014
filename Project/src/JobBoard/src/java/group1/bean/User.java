package group1.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Provides an entity to encapsulate a users login.
 *
 * @author dan
 */
public class User implements UserDetails {

    /**
     * Unique serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Username of the account.
     */
    @NotEmpty
    private String username;

    /**
     * Password which is hashed to authenticate the account.
     */
    @NotEmpty
    private String password;

    /**
     * Default constructor to create a new blank user.
     */
    public User() {
    }

    /**
     * Create a new user entity which can log into the system.
     *
     * @param username Unique username for the account.
     * @param hashed_password Hashed password which is already salted.
     */
    public User(String username, String hashed_password) {
        this.username = username;
        this.password = hashed_password;
    }

    /**
     * Get the password of the user, this will return the hashed password.
     *
     * @return Hashed password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user, must be a hashed password.
     *
     * @param hashed_password Hashed password used to authenticate the account.
     */
    public void setPassword(String hashed_password) {
        this.password = hashed_password;
    }

    /**
     * Set the unique username of the account.
     *
     * @param username Username of the account.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the username of the account.
     *
     * @return The username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * @see UserDetails.isAccountNonExpired()
     * @return Always true, the account has not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @see UserDetails.isAccountNonLocked()
     * @return Always true, the account has not been locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @see UserDetails.isCredentialsNonExpired()
     * @return Always true, the accounts credentials have not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @see UserDetails.isEnabled()
     * @return Always true, the account is always enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * @see UserDetails.getAuthorities()
     * @return An empty list, we do not use this feature.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        auths.add(new GrantedAuthorityImpl("ROLE_USER"));
        return auths;
    }
}
