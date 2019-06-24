package org.launchcode.controllers;

import org.launchcode.models.Job;
import org.launchcode.models.JobField;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET) ///job?id=X //@RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String index(Model model, int id) { //grabs id from html file
        model.addAttribute("job", jobData.findById(id));
        //Job newJob = jobData.findById(id);


        // TODO #1 - get the Job with the given ID and pass it into the view

        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
        int jobId = 0;

             if (errors.hasErrors()) {
             return "new-job";
             }

         //if valid create a new Job and add it to the jobData data store.

         //create a new Job
         //instantiate/initialize
         // Job(String aName, Employer aEmployer, Location aLocation,
         //               PositionType aPositionType, CoreCompetency aSkill)
         //may use IDs

         //Job newJob = new Job(jobForm.getName(),jobForm.getEmployerId(),jobForm.getLocationId(), jobForm.getPositionTypeId(), jobForm.getCoreCompetencyId());
        Job newJob = new Job(jobForm.getName(), jobData.getEmployers().findById(jobForm.getEmployerId()), jobData.getLocations().findById(jobForm.getLocationId()), jobData.getPositionTypes().findById(jobForm.getPositionTypeId()), jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId()));
        //Create the new Job.^ If you don't write 'new' it will overlay the current instance of a Job object.

        //Apple the getId method to the newJob object.
        //jobId = newJob.getId();

         //JobData data = new JobData();

         //JobData.getInstance().add(newJob);
        jobData.add(newJob); //HELL FUCKIN' YEAH!
        //return "job?id={newJob.getId()}"; //return "redirect:";
        //System.out.println("Print line statement");
         //return "job-detail"; //return "redirect:";
        return "redirect:/job/?id=" + newJob.getId();
        //Here you are using redirect: to go to a webpage.


        //return ""; //remember to comment out.



    }
}
