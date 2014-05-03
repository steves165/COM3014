package group1.service;

import group1.bean.SalarySearchItem;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.inject.Named;
import org.springframework.stereotype.Service;

/**
 * Decorator to SearchService to add auto complete functionality for salaries.
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
@Service
@Named
public class SalarySearchService {
    /**
     * Lowest number we should auto-complete to, note that if this is modified
     * unit tests will have to be modified.
     */
    private int lowerLimit = 5000;

    /**
     * Maximum number we should auto-complete to, note that if this is modified
     * unit tests will have to be modified.
     */
    private int upperLimit = 500000;

    /**
     * Method executed where we should try to auto-complete the entry text.
     * @param entry Entry text to try and auto-complete
     * @return List of possible auto-completed search items.
     */
    public Collection<SalarySearchItem> autoComplete(String entry) {
        Collection<SalarySearchItem> returnList = new ArrayList<SalarySearchItem>();

        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        String thousandsSeperator = String.valueOf(symbols.getGroupingSeparator());

        String integerString = entry;

        // Remove the first character if it is the symbol
        String currencySymbol = symbols.getCurrencySymbol();
        if (integerString.startsWith(currencySymbol)) {
            integerString = integerString.substring(1);
        }

        // Try to guess the integer value from a parseable string
        Integer number = IntegerHelper.tryParse(integerString);

        if (number == null && integerString.contains(thousandsSeperator)) {
            // The integer was not parseable but contained a thousands seperator
            // try to extrapolate what the user means.
            integerString = thousandsComplete(integerString, thousandsSeperator);

            // Re parse the new string
            integerString = integerString.replace(thousandsSeperator, "");
            number = IntegerHelper.tryParse(integerString);
            if (number != null && number > 0) {
                // Only add the value if the integer is parseable
                returnList.add(new SalarySearchItem(number.intValue()));
            }
        } else if (number != null && number > 0) {
            // WARNING: Netbeans reports the above null check is not required,
            // it is required! A unit test will fail if this is removed.

            // We can parse the number and the number is not negative
            int intval = number.intValue();

            // Multiply the number by ten until we are above the lower limit
            int smallVal = intval;
            while (smallVal <= lowerLimit) {
                smallVal *= 10;
            }
            returnList.add(new SalarySearchItem(smallVal));

            // Multiply the smaller value by 10 and add it if it is less than
            // the upper limit
            int highVal = smallVal * 10;
            if (highVal <= upperLimit) {
                returnList.add(new SalarySearchItem(highVal));
            }
        }
        
        return Collections.unmodifiableCollection(returnList);
    }

    /**
     * Try to complete a entered string which has an incomplete thousand
     * seperator in the string.
     * @param entry Text to try to complete to valid number entry
     * @param thousandsSeperator Thousand seperator to look for
     * @return A string padded with 0's until the thousands are correct.
     */
    private String thousandsComplete(String entry, String thousandsSeperator) {
        StringBuilder integerString = new StringBuilder(entry);
        int commaIndex = integerString.lastIndexOf(thousandsSeperator);
        int addZeros = 4 - (integerString.length() - commaIndex);
        if (addZeros > 0 && addZeros <= 3) {
            for(int i = 0; i < addZeros; i++) {
                integerString.append('0');
            }
        }
        return integerString.toString();
    }
}
