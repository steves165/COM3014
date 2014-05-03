package group1.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import group1.repository.ICompanyRepo;
import group1.validator.PatternList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Java entity class to represent a job stored within the
 * web application.
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
public class Job {

    /**
     * Company repository to allow for lazy loading of the company
     * associated with the job.
     */
    @JsonIgnore
    private ICompanyRepo companyRepo;

    /**
     * Default job constructor required by Spring to serialise/de-serialise the
     * entities.
     */
    public Job() {
    }

    /**
     * Constructor to fully populate the job description.
     * @param title Job title.
     * @param company_id Integer ID of the company to associate the job with.
     * @param department Department in the company.
     * @param description Job description text.
     * @param contract Contract type of the job.
     * @param requiredSkills List of required skills.
     * @param desiredSkills List of desired skills.
     * @param location Location where the job is located.
     * @param salary Salary figure, must be > 0.
     */
    public Job(String title, int company_id, String department, String description, String[] requiredSkills, String[] desiredSkills, String location, double salary, String contract) {
        this.id = 0; // Job has no id until it is put into the database
        this.title = title;
        this.companyId = company_id;
        this.department = department;
        this.description = description;
        this.contract = contract;
        this.setSalary(salary);
        this.location = location;
        this.requiredSkills.addAll(Arrays.asList(requiredSkills));
        this.desiredSkills.addAll(Arrays.asList(desiredSkills));
    }

    /**
     * Unique identifier for the job posting.
     */
    private int id;

    /**
     * Job posting title.
     */
    @NotBlank
    @Pattern(regexp="^[a-zA-Z ]+$", message = "Characters in the Title field must be Letters only.")
    private String title;

    /**
     * Company title
     */
    private int companyId;

    /**
     * Department of company.
     */
    @NotBlank
    @Pattern(regexp="^[a-zA-Z ]+$", message = "Characters in the Department field must be Letters only.")
    private String department;

    /**
     * Description of the job.
     */
    @NotBlank
    @Pattern(regexp="^[a-zA-Z 0-9 ]+$", message = "Characters in the Description field must be Letters and Numbers.")
    private String description;

    /**
     * Location of the job.
     */
    @NotBlank
    @Pattern(regexp="^[a-zA-Z 0-9 ]+$", message = "Characters in the Location field must be Letters and Numbers.")
    private String location;

    /**
     * Contract of the job, no error message on pattern provided as this is used
     * internally and the default error shouldn't be shown to users
     * unless they are purposely modifying the form data.
     */
    @NotBlank
    private String contract;

    /**
     * Salary of the job.
     */
    private double salary;


    /**
     * Required Skills of the job.
     */
    @NotEmpty
    @PatternList(regexp="^[a-zA-Z 0-9]+$", message = "Element in required skills list is not valid. Must contain at least one letter or number.")
    private List<String> requiredSkills = new ArrayList<String>();

    /**
     * Desired Skills of the job
     */
    @NotEmpty
    @PatternList(regexp="^[a-zA-Z 0-9]+$", message = "Element in desired skills list is not valid. Must contain at least one letter or number.")
    private List<String> desiredSkills = new ArrayList<String>();

    /**
     * Id of the user who created this job.
     */
    private String createUser;

    /**
     * Set the associated company.
     * @param companyId Associated company ID.
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * Set the department where the job is located in the company.
     * @param department Department name
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Set the job description text.
     * @param description Job description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the location that the job is based.
     * @param location String location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Add a skill to the desired skills list.
     * @param skill Skill to add as a string.
     */
    public void addDesiredSkill(String skill) {
        this.desiredSkills.add(skill);
    }
    /**
     * Add a skill to the required skills list.
     * @param skill Skill to add as a string.
     */
    public void addRequiredSkill(String skill) {
        this.requiredSkills.add(skill);
    }

    /**
     * Get the contract type of the job posting as a string
     * @return Contract type as a string.
     */
    public String getContract() {
        return contract;
    }

    /**
     * Set the contract type of the job
     * @param contr Contract string to set.
     */
    public void setContract(String contr) {
        this.contract = contr;
    }
    /**
     * Set the salary of the Job. Validates that the
     * salary is > 0. Declared as final so that subclasses cannot
     * modify the behaviour of this method thus altering what the
     * constructor does.
     * @param salary Salary to set
     */
    public final void setSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary not > 0");
        }
        this.salary = salary;
    }

    /**
     * Getter method for the Description.
     * @return Description String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for the Department.
     * @return Department String.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Getter method for the Required Skills. Note that this exposes the
     * internal list (dangerously) for spring to be able to add to the list
     * using a reference. This could cause issues if this list is modified in
     * two threads at the same time.
     * @return required skills
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    // Warning suppressed as Spring relies on a reference to update the skills
    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    /**
     * Getter method for the Desired Skills. Note that this exposes the
     * internal list (dangerously) for spring to be able to add to the list
     * using a reference. This could cause issues if this list is modified in
     * two threads at the same time.
     * @return Desired skills
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    // Warning suppressed as Spring relies on a reference to update the skills
    public List<String> getDesiredSkills() {
        return desiredSkills;
    }

    /**
     * Getter method for the Location.
     * @return Location String.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter method for the Salary.
     * @return Salary double.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Getter for the company id.
     * @return Company identifier.
     */
    public int getCompanyId() {
        return companyId;
    }
    /**
     * Getter method for the jobId field.
     * @return jobId unique integer
     */
    public int getId() {
        return id;
    }

    /**
     * Set the jobId field
     * @param Id Unique id for the job posting.
     */
    public void setId(int Id) {
        this.id = Id;
    }

    /**
     * Title getter for the job.
     * @return title field value
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the job title.
     * @param title Title string to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Override toString method, this is used for testing but the view should be
     * responsible for displaying the Job in a nice fashion.
     * @return The job as a string
     */
    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", title=" + title + ", company=" + companyId + ", department=" + department + ", description=" + description + ", location=" + location + ", Contract=" + contract + ", salary=" + salary + ", requiredSkills=" + requiredSkills + ", desiredSkills=" + desiredSkills + '}';
    }

    /**
     * Get the actual company entity related to this job, returns null if
     * the company id does not exist.
     * @return Company entity for this job.
     */
    public Company getCompany() {
        return companyRepo.getCompany(companyId);
    }

    /**
     * Set the repository used by the getCompany method to retrieve the company.
     * This cannot be injected as we invoke the constructor manually rather than
     * relying on spring to create an instance for us.
     * @param repo Repository to access the company entities through.
     */
    public void setCompanyRepo(ICompanyRepo repo) {
        companyRepo = repo;
    }

    /**
     * Get the user who created the job, for authentication purposes.
     * @return Username which created the job.
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * Set the user who created the job.
     * @param createUser Username of the user who created the job.
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
