package group1.test;

import group1.googleplaces.GooglePlaces;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Google Places API auto-complete
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
public class GooglePlacesAutocompleteTest {

    /**
     * API object to test, defaults to an object with a correctly setup API key.
     */
    private GooglePlaces placesApi = new GooglePlaces();

    /**
     * Basic town auto-complete tests, just to check connectivity. These tests
     * are not designed to test if the responses from Google are all correct.
     */
    @Test
    public void townAutocomplete() {
        List<String> suggestions = placesApi.getSuggestions("Woking");
        assertEquals("Woking, United Kingdom", suggestions.get(0));

        suggestions = placesApi.getSuggestions("Wokin");
        assertEquals("Woking, United Kingdom", suggestions.get(0));

        suggestions = placesApi.getSuggestions("Guildford");
        assertEquals("Guildford, United Kingdom", suggestions.get(0));

        suggestions = placesApi.getSuggestions("Guild");
        assertEquals("Guildford, United Kingdom", suggestions.get(0));
    }

    /**
     * Simple invalid tests, blank lines and symbols.
     */
    @Test
    public void invalid() {
        List<String> suggestions = placesApi.getSuggestions("#");
        assertEquals(0, suggestions.size());

        suggestions = placesApi.getSuggestions("");
        assertEquals(0, suggestions.size());

        suggestions = placesApi.getSuggestions("       ");
        assertEquals(0, suggestions.size());

        suggestions = placesApi.getSuggestions("@");
        assertEquals(0, suggestions.size());
    }

    /**
     * Test that an invalid API key only returns an empty list and the application
     * can continue.
     */
    @Test
    public void invalidApi() {
        placesApi = new GooglePlaces("1234");

        List<String> suggestions = placesApi.getSuggestions("Wokin");
        assertEquals(0, suggestions.size());
    }
}