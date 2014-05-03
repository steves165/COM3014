/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group1.validator;

import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * Validator to validate the PatternList validation constraint on a list of strings.
 * @author ds00148
 */
@Component
public class PatternListValidator implements ConstraintValidator<PatternList, List<String>>{

    /**
     * Annotation object with our constraint.
     */
    private PatternList constraint;

    /***
     * Initialise the validator with the given constraint, used by the Java EE
     * framework.
     * @param constraintAnnotation Annotation object to use.
     */
    @Override
    public void initialize(PatternList constraintAnnotation) {
        constraint = constraintAnnotation;
    }

    /**
     * Validate the list using the given pattern of the annotation. Note that this
     * validator will reject any strings which are null causing the validation
     * to fail.
     * @param value List of strings to validate.
     * @param context Validation context
     * @return True if every item in the list is valid, false otherwise.
     */
    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        Iterator<String> it = value.iterator();
        while(it.hasNext()) {
            String s = it.next();

            if (s == null) {
                // A null element counts as a validation failure
                context.buildConstraintViolationWithTemplate(constraint.message());
                return false;
            }

            if(!s.matches(constraint.regexp())) {
                context.buildConstraintViolationWithTemplate(constraint.message());
                return false;
            }
        }
        return true;
    }

}
