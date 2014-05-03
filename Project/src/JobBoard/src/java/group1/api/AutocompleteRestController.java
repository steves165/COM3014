package group1.api;

import group1.bean.Company;
import group1.bean.LocationSearchItem;
import group1.bean.SalarySearchItem;
import group1.service.CompanyListingService;
import group1.service.LocationSearchService;
import group1.service.SalarySearchService;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller to handle REST API requests for the auto-complete.
 * @author dan
 */
@Controller
@RequestMapping("/autocomplete")
public class AutocompleteRestController {

    /**
     * Service to provide salary auto complete.
     */
    @Inject
    private SalarySearchService salarySearchService;

    /**
     * Service to access the company list to provide the company
     * search for auto complete.
     */
    @Inject
    private CompanyListingService companyService;

    /**
     * Service to provide location auto complete.
     */
    @Inject
    private LocationSearchService locationService;

    /**
     * Get a list of suggested inputs for the salary search input. Used via
     * /api/autocomplete/salary/{entry}.
     *
     * @param entry Path variable with the entered data.
     * @return List of search suggestions.
     */
    @RequestMapping(value="salary/{entry}", method=RequestMethod.GET)
    public Iterable<SalarySearchItem> salarySuggestions(@PathVariable String entry) {
        return salarySearchService.autoComplete(entry);
    }

    /**
     * Get a list of suggested companies to select for a given search input.
     * Used via /api/autocomplete/company/{entry}.
     *
     * @param entry Start of company name to match.
     * @return List of company's which start with entry.
     */
    @RequestMapping(value="company/{entry}", method=RequestMethod.GET)
    public Iterable<Company> companySuggestions(@PathVariable String entry) {
        return companyService.searchName(entry);
    }

    /**
     * Get a list of suggested places for a string input. Used via
     * the /api/autocomplete/location/{entry} url.
     *
     * @param entry Input text to match a place against using the Google Places API.
     * @return List of places which could auto complete for the entry.s
     */
    @RequestMapping(value="location/{entry}", method=RequestMethod.GET)
    public Iterable<LocationSearchItem> locationSuggestions(@PathVariable String entry) {
        return locationService.autoComplete(entry);
    }
}
