package group1.controller;

import group1.bean.User;
import group1.service.UserService;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to handle user maintenance and authentication.
 * @author ds00148
 */
@Controller
public class UserController {
    /**
     * Service for the business logic of handling users.
     */
    @Inject
    private UserService userService;

    /**
     * Controller request to show the registration form for a user.
     * @return Model and view for the register form.
     */
    @RequestMapping(value="/register.htm")
    protected ModelAndView register() {
        ModelAndView mv = new ModelAndView("register", "command", new User());
        return mv;
    }

    /**
     * Perform a user creation when the registration form has been submitted, if
     * there are errors with the registration then the user is taken back to the
     * registration form.
     * @param user User entity to create.
     * @param result Validation results from the binding.
     * @return Either a redirection back to the registration form, or a redirect to the
     * job listing page.
     */
    @RequestMapping(value="/initRegisterForm.htm", method=RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute("command") User user,
                             BindingResult result) {
        if (result.hasErrors()) {
                return "register";
        }
        if (userService.getUser(user.getUsername()) != null) {
            result.addError(new ObjectError("command", "Username already exists"));
            return "register";
        }
        userService.createUser(user);
        return "redirect:search.htm";
    }

    /**
     * Mapping when a users credentials are incorrect and the login fails.
     * @return loginfailed jsp page.
     */
    @RequestMapping(value="/loginfailed.htm")
    protected String loginFailed() {
        return "loginfailed";
    }

    /**
     * Mapping to show the login form to the user.
     * @return "login" which is the JSP template for the login form.
     */
    @RequestMapping(value="/login.htm")
    protected String login() {
        return "login";
    }
}
