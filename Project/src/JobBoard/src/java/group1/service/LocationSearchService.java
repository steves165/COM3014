package group1.service;

import group1.bean.LocationSearchItem;
import group1.restconsumer.ILocationApi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.stereotype.Service;

/**
 * Class to auto-complete the users entry to a location.
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
@Service
@Named
public class LocationSearchService {

     /**
      * Service to access a location based API for the auto-complete
      */
     @Inject
     private ILocationApi locationApi;

     /**
      * Get a list of places from the Google Places API which autocomplete the
      * entered string.
      * @param entry Entered string to attempt to autocomplete.
      * @return List of possible locations which autocomplete the search text.
      */
     public Collection<LocationSearchItem> autoComplete(String entry) {
         Collection<LocationSearchItem> returnList = new ArrayList<LocationSearchItem>();

         List<String> suggestions = locationApi.getSuggestions(entry.toLowerCase(Locale.getDefault()));
         Iterator<String> it = suggestions.iterator();
         while(it.hasNext()) {
             LocationSearchItem location = new LocationSearchItem(it.next());
             returnList.add(location);
         }

         return returnList;
     }
}
