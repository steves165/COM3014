package group1.controller;

import group1.bean.Application;
import group1.bean.Job;
import group1.service.ApplicationService;
import group1.service.JobListingService;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to provide hooks for the job application process.
 * @author andyaltwasser
 */
@Controller
public class ApplicationController {

    /**
     * Service to control access to the applications in the database.
     */
    @Inject
    private ApplicationService appService;

    /**
     * Service to access the jobs to apply for.
     */
    @Inject
    private JobListingService jobService;

    /**
     * POST submit for the application.
     * @param app Application entity which was submitted.
     * @param result Validation result.
     * @return Returns the user back to the apply screen on validation errors,
     * or redirects back to search if the application was successful.
     */
    @RequestMapping(value="/initFormApp.htm", method=RequestMethod.POST)
    public String addApplication(@Valid @ModelAttribute("command") Application app, BindingResult result) {

        if (result.hasErrors()) {
                return "applyScreen";
        }
        appService.saveApp(app);

        return "redirect:search.htm";
    }

    /**
     * Provide a way for the creator of a job posting to review a list of application
     * submissions. This method restricts the returned list to applications where
     * the associated job matches the currently logged in user.
     * @return Model and View to display the applications.
     */
    @RequestMapping(value="/applicationReview.htm", method=RequestMethod.GET)
    protected ModelAndView list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Iterable<Application> app = this.appService.getApplicationsForUser(username);

        ModelAndView mv = new ModelAndView("applicationReview");
        mv.addObject("apps", app);

        return mv;
    }

    /**
     * Provide the display of a view for the user to be able to apply for a job.
     * @param id ID of the job to apply for.
     * @return ModelAndView where the application object is bound to "command" and
     * the associated job is bound to "job".
     */
    @RequestMapping(value="/applyScreen.htm", method=RequestMethod.GET)
    protected ModelAndView applyScreen(@RequestParam("id") int id) {
        Job job = jobService.getJob(id);
        Application app = new Application();
        app.setJobId(job.getId());
        ModelAndView mv = new ModelAndView("applyScreen", "command", app);

        mv.addObject("job", job);

        return mv;
    }
}
