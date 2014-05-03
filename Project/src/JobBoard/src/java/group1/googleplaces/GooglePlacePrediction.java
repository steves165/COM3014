package group1.googleplaces;

/**
 * Class to encapsulate a place auto-complete from the result from Google
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
public class GooglePlacePrediction {

    /**
     * Description field in JSON, contains the actual auto-compete suggestion.
     * E.g. Paris, France
     */
    private String description;

    /**
     * Internal Google identifier, ignored.
     */
    private String id;

    /**
     * Internal Google reference, ignored.
     */
    private String reference;

    /**
     * Array of substrings that were matched, ignored.
     */
    private Object[] matched_substrings;

    /**
     * Google search terms for the location, ignored.
     */
    private Object[] terms;

    /**
     * Types that this place covers, ignored.
     */
    private String[] types;

    /**
     * Getter for the description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description.
     * @param description String to set the description to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the id.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the id.
     * @param id String to set the id to
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the reference.
     * @return reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Setter for the reference.
     * @param reference String to set reference to.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Get the matched substrings as an Object array, shouldn't be
     * used until it returns something valid. Hence the Deprecated flag.
     * @return Object array of the matched substrings, a cloned copy so changes
     * do not affect this instance. An empty array is returned if matched_substrings
     * is null.
     * @deprecated Return type should be resolved if this method needs to be
     * used.
     */
    @Deprecated
    public Object[] getMatched_substrings() {
        if (matched_substrings != null) {
            return matched_substrings.clone();
        }
        return new Object[0];
    }

    /**
     * Set the matched_substring array which is a shallow copy of the parameter.
     * @param matched_substrings Object to set the parameters to.
     * @deprecated matched_substrings type should be resolved if this method
     * needs to be used.
     */
    @Deprecated
    public void setMatched_substrings(Object[] matched_substrings) {
        this.matched_substrings = matched_substrings.clone();
    }

    /**
     * Get the terms as an Object array, shouldn't be
     * used until it returns something valid. Hence the Deprecated flag.
     * @return Object array of the terms, a cloned copy so changes
     * do not affect this instance. A empty array is returned if terms is null.
     * @deprecated Return type should be resolved if this method needs to be
     * used.
     */
    public Object[] getTerms() {
        if (terms != null) {
            return terms.clone();
        }
        return new Object[0];
    }

    /**
     * Set the terms array which is a shallow copy of the parameter.
     * @param terms Object to set the parameters to.
     * @deprecated terms type should be resolved if this method
     * needs to be used.
     */
    @Deprecated
    public void setTerms(Object[] terms) {
        this.terms = terms.clone();
    }

    /**
     * Get the types of location this result is.
     * @return Shallow copy of the types array or an empty array if types is null.
     */
    public String[] getTypes() {
        if (types != null) {
            return types.clone();
        }
        return new String[0];
    }

    /**
     * Set the types of location this result is. The parameter is shallow
     * copied into the types field.
     * @param types Types to set the location as.
     */
    public void setTypes(String[] types) {
        this.types = types.clone();
    }


}
