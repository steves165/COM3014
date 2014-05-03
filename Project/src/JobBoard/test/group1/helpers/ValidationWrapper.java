/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group1.helpers;

import java.util.List;
import org.hibernate.validator.HibernateValidator;
import org.junit.Ignore;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Helper class to wrap around the Spring LocalValidatorFactoryBean to aid our
 * unit tests.
 * @param <TEntity> Entity type which we should be validating.
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
@Ignore
public class ValidationWrapper<TEntity> {

    /**
     * Spring validator to use.
     */
    private LocalValidatorFactoryBean validator;

    /**
     * Default constructor to setup the LocalValidatorFactoryBean.
     */
    public ValidationWrapper() {
        validator = new LocalValidatorFactoryBean();
        validator.setProviderClass(HibernateValidator.class);
        validator.afterPropertiesSet();
    }

    /**
     * Helper method to execute Spring validation on an entity.
     * @param entity Entity to validate.
     * @return List of validation errors.
     */
    public List<ObjectError> validate(TEntity entity) {
        BindException exceptions = new BindException(entity, "entity");
        validator.validate(entity, exceptions);
        return exceptions.getAllErrors();
    }
}
