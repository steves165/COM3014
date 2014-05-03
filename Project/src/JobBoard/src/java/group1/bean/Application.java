package group1.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity class to encapsulate a job application.
 *
 * @author andyaltwasser
 */
public class Application {

    /**
     * Unique identifier for the application.
     */
    private int id;

    /**
     * First name entry for the person applying for the job.
     */
    @NotBlank
    @Pattern(regexp="^[a-zA-Z ]+$", message = "Characters within first name must contain letters only.")
    private String firstName;

    /**
     * Last name entry for the person applying for the job.
     */
    @NotBlank
    @Pattern(regexp="^[a-zA-Z ]+$", message = "Characters within last name must contain letters only.")
    private String lastName;

    /**
     * Contact telephone number for the person applying for the job.
     */
    @NotBlank
    @Pattern(regexp="^[0-9- /-/+]+$", message = "Characters in the Telephone field must be numbers, dashes or plus's.")
    private String phoneNumber;

    /**
     * Contact email address for the job applicant.
     */
    @NotBlank
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Characters in the Email field must be letters, numbers, fullstops or @.")
    private String email;

    /**
     * Job ID associated with the application.
     */
    @NotNull
    private Integer jobId;

    /**
     * Constructor to easily create a test entity for mock data.
     * @param id Unique id for the job.
     * @param firstName Applicants first name.
     * @param lastName Applicants last name.
     * @param phoneNumber Applicants contact phone number.
     * @param email Applicants email.
     * @param jobId Identifier of the job which the application is associated with.
     */
    public Application(Integer id, String firstName, String lastName, String phoneNumber, String email, Integer jobId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.jobId = jobId;
    }

    /**
     * Explicit default constructor to create a new blank application.
     */
    public Application() {
    }

    /**
     * Get the unique identifier for the application.
     * @return The ID of the application.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the unique identifier for the application
     * @param id New ID for the application.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the first name of the applicant.
     * @return First name entered on the application.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of the applicant.
     * @param firstName Name entered on the application form.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of the applicant.
     * @return Last name entered on the application.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of the applicant.
     * @param lastName Name entered on the application form.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the contact telephone number of the applicant.
     * @return Applicants contact number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the telephone number of the applicant.
     * @param phoneNumber Entered phone number for the applicant.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the contact email address for the applicant.
     * @return Email address of the applicant.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the contact email address of the applicant.
     * @param email Email address of the applicant.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the job ID which this application is for.
     * @return Integer ID of the associated job, can be
     * null, although an application without a job ID should
     * be considered invalid.
     */
    public Integer getJobId() {
        return jobId;
    }

    /**
     * Set the job which the application is for.
     * @param jobId Job identifier which the application is associated with.
     */
    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
