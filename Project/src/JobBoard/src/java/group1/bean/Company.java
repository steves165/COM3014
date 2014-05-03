/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group1.bean;

import group1.validator.PatternList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * Entity class to provide a company record.
 *
 * @author Steves165
 */
public class Company {

    /**
     * Unique id for the company record, only used internally an is not user
     * editable.
     */
    private int id;

    /**
     * String of the company's full name.
     */
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z 0-9 /./,]+$", message = "Characters in the Company Name field must be letters, numbers or a full stop/comma.")
    private String companyName;

    /**
     * List of locations which the company has offices.
     */
    @NotEmpty
    @PatternList(regexp = "^[a-zA-Z 0-9 /,/./-]+$", message = "Element in locations list is not valid. Must contain at least one letter or number.")
    private List<String> locations = new ArrayList<String>();

    /**
     * Industry which the company is part of, free type string.
     */
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Characters in the Industry field must be Letters only.")
    private String industry;

    /**
     * Free type description of the company's scale.
     */
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Characters in the Scale field must be letters only.")
    private String scale;

    /**
     * String with the company's web site address.
     */
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z 0-9 /.// ]+$", message = "Characters in the website field must be letters, numbers, fullstops or forward slahses.")
    private String website;

    /**
     * Email address of a basic contact for the company applications.
     */
    @NotBlank
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Characters in the Email field must be letters, numbers, fullstops or @.")
    private String contactEmail;

    /**
     * Telephone number of a basic contact for the company applications.
     */
    @NotBlank
    @Pattern(regexp = "^[0-9- /-/+]+$", message = "Characters in the Telephone field must be numbers, dashes or plus's.")
    private String contactTel;

    /**
     * Postal address of a basic contact for the company applications.
     */
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z 0-9 /, ]+$", message = "Characters in the Address field must be letters, numbers or commas.")
    private String contactAddress;

    /**
     * Raw byte array of a file representing the companies logo.
     */
    private byte[] image;

    /**
     * Multipart file used by spring to upload the logo.
     */
    private MultipartFile logo;

    /**
     * Username which created this job in order to restrict access to
     * applications to the user who created the job posting.
     */
    private String createUser;

    /**
     * Explicit default constructor to create a blank company.
     */
    public Company() {
    }

    /**
     * Constructor used for creating test companies in the mock repositories.
     * @param companyName
     * @param industry
     * @param scale
     * @param website
     * @param contactEmail
     * @param contactTel
     * @param contactAddress
     * @param locations
     * @param logo
     * @throws IOException
     */
    public Company(String companyName, String industry, String scale, String website, String contactEmail, String contactTel, String contactAddress, String[] locations, MultipartFile logo) throws IOException {
        this.id = 0;
        this.companyName = companyName;
        this.industry = industry;
        this.scale = scale;
        this.website = website;
        this.contactEmail = contactEmail;
        this.contactTel = contactTel;
        this.contactAddress = contactAddress;
        this.locations.addAll(Arrays.asList(locations));
        this.image = logo.getBytes();
    }

    /**
     * Get the unique identifier for the company.
     *
     * @return Value in the id field.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the unique identifier for the company.
     *
     * @param id Unique numeric identifier for the company.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the companies full name.
     *
     * @return Value of the companyName field.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Set the full name of the company.
     *
     * @param companyName Full name of the company.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Get a raw pointer to the internal location list data structure. This is
     * required by the spring framework, care must be taken when using this
     * outside of the spring framework.
     *
     * @return Raw object pointer to the locations list.
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public List<String> getLocations() {
        return locations;
    }

    /**
     * Add a string location to the location list, used for entering the example
     * data into the mock repository.
     *
     * @param location String location to add.
     */
    public void addLocation(String location) {
        this.locations.add(location);
    }

    /**
     * Get the industry which is entered against the company.
     *
     * @return Value of the industry field.
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * Set the industry which the company is part of as a free form string.
     *
     * @param industry Industry to associate the company with.
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * Get the entered scale of the company.
     *
     * @return Value of the scale field.
     */
    public String getScale() {
        return scale;
    }

    /**
     * Set the scale of the company as a free form string,
     *
     * @param scale Scale to enter against the company.
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * Get the web site URL for the company.
     *
     * @return Value of the website field.
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Set the company's website address.
     *
     * @param website Full website url of the company's homepage.
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Get the contact email address for applications.
     *
     * @return Value of the contactEmail field.
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Set the contact email address.
     *
     * @param contactEmail Email address to set for the company contact.
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Get the applications contact number for the company.
     *
     * @return Value of the contactTel field.
     */
    public String getContactTel() {
        return contactTel;
    }

    /**
     * Set the contact telephone number for the company.
     *
     * @param contactTel Contact telephone number to set.
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    /**
     * Get the postal address of the company.
     *
     * @return Value of the contractAddres field.
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * Set the contact address of the company as a free string.
     * @param contactAddress Postal address of the company.
     */
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    /**
     * Get the image bytes for the logo.
     *
     * @return Image bytes for the logo can be null or of zero length.
     * getHasImage should be called first to see if this will return a valid
     * result.
     */
    public byte[] getImage() {
        if (image == null) {
            return new byte[0];
        }
        return image.clone();
    }

    /**
     * Set the image bytes for the company logo.
     *
     * @param file Bytes to set for the image data.
     */
    public void setImage(byte[] file) {
        if (file == null) {
            return;
        }
        this.image = file.clone();
    }

    /**
     * Set the byte array from uploaded multipart file for the image.
     *
     * @param file Multipart file to set the byte array to
     * @throws IOException If the multipart reading fails.
     */
    public void setLogo(MultipartFile file) throws IOException {
        logo = file;
        this.setImage(file.getBytes());
    }

    /**
     * Get the multipart uploaded logo.
     *
     * @return logo
     */
    public MultipartFile getLogo() {
        return logo;
    }

    /**
     * Get the username of the user which created this job, used for
     * authentication.
     *
     * @return Username of the user who created the job.
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * Set the username of the user who created the job.
     *
     * @param createUser Username of who created the job.
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * Return if there is valid image data for the logo
     *
     * @return True if image is not equal to null and the length is greater than
     * zero.
     */
    public boolean getHasImage() {
        return (this.image != null && this.image.length > 0);
    }
}
