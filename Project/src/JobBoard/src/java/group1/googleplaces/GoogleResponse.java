package group1.googleplaces;

/**
 * Class to wrap up Google auto complete responses.
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 * @param <T> Type which each element in the prediction list should be marshaled as.
 */
public class GoogleResponse<T> {
    /**
     * Status returned by the query
     * OK, ZERO_RESULTS, OVER_QUERY_LIMIT, REQUEST_DENIED, INVALID_REQUEST
     */
    private String status;

    /**
     * Array of the predictions of type T
     */
    private T[] predictions;

    /**
     * Get the status code from the query.
     * @return Status code.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status code, used during marshaling.
     * @param status Code to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the predictions from the query
     * @return The predictions as a shallow copied array or a empty array if
     * predictions is null.
     */
    @SuppressWarnings("unchecked")
    public T[] getPredictions() {
        if (predictions != null) {
            return predictions.clone();
        }
        // Ugly cast to return a empty array, due to java limits with generics
        return (T[])new Object[0];
    }

    /**
     * Set the predictions from the query as a shallow copy of the parameter.
     * @param predictions Prediction array to set.
     */
    public void setPredictions(T[] predictions) {
        this.predictions = predictions.clone();
    }
}
