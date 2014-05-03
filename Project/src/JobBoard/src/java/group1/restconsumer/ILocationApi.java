package group1.restconsumer;

import java.util.List;

/**
 * Interface for APIs which can provide us with location suggestions for a given input.
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
public interface ILocationApi {
    /**
     * Get a list of possible auto-completes for the given string input.
     * @param entry Entry to find the auto-completes for.
     * @return A list of suggestions or an empty list if there was none.
     */
    public List<String> getSuggestions(String entry);
}
