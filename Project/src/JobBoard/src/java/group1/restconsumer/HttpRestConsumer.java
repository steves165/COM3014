package group1.restconsumer;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.UriBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * Class to encapsulate HTTP REST requests and responses using JSON.
 * This class acts as a facade around the RestTemplate spring library class.
 * @param <ResponseType> Type of response the consumer should be returning on a
 * request.
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
public class HttpRestConsumer<ResponseType> {

    /**
     * RestTemplate object to issue the request, part of the spring library.
     */
    private RestTemplate restTemplate;

    /**
     * Factory for creating the HTTP requests, part of the spring library.
     */
    private HttpComponentsClientHttpRequestFactory factory;

    /**
     * Dictionary of variables to pass with the URL.
     */
    private Map<String, String> vars;

    /**
     * Class definition for our response type so the ResponseType can be passed
     * into getForObject of the RestTemplate object.
     */
    private Class<ResponseType> responseType;

    /**
     * Constructor for the REST consumer, creates all the required handlers for
     * the requests.
     * @param responseType Class definition for the type of response we are expecting
     * back. E.g. MyResponse.class
     */
    public HttpRestConsumer(Class<ResponseType> responseType) {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        factory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(factory);

        vars = new HashMap<String, String>();

        this.responseType = responseType;
    }

    /**
     * Add a string variable to the request.
     * @param key Request variable key.
     * @param value Request variable value.
     */
    public void addUrlVar(String key, String value) {
        vars.put(key, value);
    }

    /**
     * Execute the HTTP url using a GET request with the variables setup using
     * the addUrlVar method and cast the response to the type specified by
     * ResponseType.
     * @param url HTTP link to execute on.
     * @return JSON Response serialised to the type given by ResponseType.
     */
    public ResponseType executeGet(String url) {
        Object response = restTemplate.getForObject(getUri(url), responseType);
        return responseType.cast(response);
    }

    /**
     * Execute the HTTP url with a POST request with the variables setup using
     * the addUrlVar method and cast the response to the type specified by
     * ResponseType.
     *
     * The HTTP post sends a null request however the variables added using
     * addUrlVar are added to the URL.
     * @param url HTTP link to execute on.
     * @return JSON Response serialised to the type given by ResponseType.
     */
    public ResponseType executePost(String url) {
        URI uri = getUri(url);
        Object response = restTemplate.postForObject(uri, null, responseType);
        return responseType.cast(response);
    }

    /**
     * Build the URI to use based on the query parameters added by the addUrlVar
     * method.
     * @param url Base URL to add the parameters to.
     * @return URI with the extra query parameters.
     */
    private URI getUri(String url) {
        UriBuilder builder = UriBuilder.fromUri(url);

        for (Map.Entry<String, String> entry : vars.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        return builder.build();
    }
}
