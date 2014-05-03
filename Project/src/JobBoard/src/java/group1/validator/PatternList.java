/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group1.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.validation.Payload;

/**
 * Annotation to validate a list of strings given a regular expression pattern.
 * @author dan
 */
@Documented
@Constraint(validatedBy = {PatternListValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
@ReportAsSingleViolation
@NotNull
public @interface PatternList {

    /**
     * Regular expression to validate all the elements in the list against
     * @return Regular expression to use as a string.
     */
    String regexp() default "";

    /**
     * Message to report when the pattern validation fails.
     * @return
     */
    String message() default "";

    /**
     * Required internally by the Java EE framework.
     * @return
     */
    Class<?>[] groups() default {};
    
    /**
     * Required internally by the Java EE framework.
     * @return
     */
    Class<? extends Payload>[] payload() default {};
}
