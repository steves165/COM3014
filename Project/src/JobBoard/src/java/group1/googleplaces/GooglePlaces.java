package group1.googleplaces;

import group1.restconsumer.HttpRestConsumer;
import group1.restconsumer.ILocationApi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * REST consumer and service for the Google maps API
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
@Service
public class GooglePlaces implements ILocationApi {
    /**
     * Google places API key.
     */
    private String apiKey;

    /**
     * Base URL to access the REST service.
     */
    private static String url = "https://maps.googleapis.com/maps/api/place/autocomplete/json";

    /**
     * Object to log error messages to.
     */
    private static final Logger LOG = Logger.getLogger(GooglePlaces.class.getName());

    /**
     * Default constructor for the Google Places API. Defaults to use the API key
     * "AIzaSyDRIpgr63cst3sfUT9VbMGlCVsuonb6hu8" which is a development key with
     * a limit of 10k requests per day.
     */
    public GooglePlaces() {
        this("AIzaSyDRIpgr63cst3sfUT9VbMGlCVsuonb6hu8");
    }

    /**
     * Constructor to initialise the API with a different API key.
     * @param apiKey Api key to use when accessing the REST service.
     */
    public GooglePlaces(String apiKey) {
        this.apiKey = apiKey;

        LOG.log(Level.INFO, "Google Places API initialised with API Key: {0}", this.apiKey);
    }

    /**
     * Issue a Places API auto-complete request through JSON
     * @param entry Entry text to auto-complete.
     * @return Response from the API.
     */
    private GooglePlacePredictionResponse issueRequest(String entry) {
        HttpRestConsumer<GooglePlacePredictionResponse> consumer =
                new HttpRestConsumer<GooglePlacePredictionResponse>(GooglePlacePredictionResponse.class);
        consumer.addUrlVar("input", entry);
        consumer.addUrlVar("key", apiKey);
        consumer.addUrlVar("types", "geocode");
        consumer.addUrlVar("language", "en-gb");
        consumer.addUrlVar("sensor", "true");
        return consumer.executeGet(url);
    }

    /**
     * Get a list of possible auto-completes for the given string input, using the Google places REST Api using JSON.
     * @param entry Entry to find the auto-completes for.
     * @return A list of suggestions or an empty list if there was none.
     */
    @Override
    public List<String> getSuggestions(String entry) {

        // Don't issue blank requests, just return a blank list.
        if (entry == null || entry.trim().isEmpty()) {
            return Collections.emptyList();
        }

        // Issue the request
        GooglePlacePredictionResponse response = issueRequest(entry);

        // Check the response
        String status = response.getStatus();

        if (status.equals("OK")) {
            List<String> result = new ArrayList<String>();
            GooglePlacePrediction[] predictions = response.getPredictions();
            for (int i = 0; i < predictions.length; i++) {
                result.add(predictions[i].getDescription());
            }
            return result;
        }

        // Log that we hit an error but respond with an empty list so we don't disrupt anything using this service
        Level logLevel = Level.WARNING;
        if (status.equals("ZERO_RESULTS")) {
            // Consider zero results to be an informational message
            logLevel = Level.INFO;
        }
        LOG.log(logLevel, "Google Places API responded with status:{0} for request:{1}", new Object[]{status, entry, apiKey});

        // An error occoured or there were no results, return an empty list
        return Collections.emptyList();
    }

}

