package com.niit.collaborationbackend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.DAO.JobApplicationDAO;
import com.niit.collaborationbackend.DAO.JobDAO;
import com.niit.collaborationbackend.model.Job;

@RestController
public class JobController {

	@Autowired
	Job job;
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	JobApplicationDAO jobApplicationDAO;
	
	
	//To Get All the Jobs from the Job Table
	@RequestMapping(value ="/getOpenJobs",method=RequestMethod.GET)
	public ResponseEntity<List<Job>> getOpenJobs(){
		
		List<Job> jobs = jobDAO.getOpenJobs();
		if(jobs==null){
			job.setErrorCode("404");
			job.setErrorMessage("No Jobs Were found");
			jobs.add(job);
		}
		
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	
	
	//If User click on any of the Job then To Get the particular Job From the Job Table
	@RequestMapping("/jobByID/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable("id") String jobId)
	{
		Job job = jobDAO.getJob(jobId);
		if(job==null)
		{
			job= new Job();
			job.setErrorCode("404");
			job.setErrorMessage("Error while getting job by Id = "+jobId);
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	
	
	//To Post a Job in  Job Table
	@RequestMapping(value="/postJob/",method=RequestMethod.POST)
	public ResponseEntity<Job> postJob(@RequestBody Job job)
		{
		job.setId("Job"+jobApplicationDAO.maxID());
		job.setDate(new Date());
		job.setStatus('V');//v-->Vacant  F-->Filled  P-->Pending
		if(jobDAO.saveJob(job)==false)
		{
			job.setErrorCode("404");
			job.setErrorMessage("Error while saving Job,., Please try again after sometime,.,!!,.,!!,.");
		}
		else
		{
			job.setErrorCode("200");
			job.setErrorMessage("Thankyou!! Job has beed posted Successfully,.,!!.,.!!!,.,");
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	
	//To Update Job in a Job Table
	@RequestMapping("/updatejob")
	public ResponseEntity<Job> updateJob(@RequestBody Job job){
		if(jobDAO.updateJob(job)==false){
			job.setErrorCode("404");
			job.setErrorMessage("Error while update Job,., Please try again after sometime,.,!!,.,!!,.,");
		}
		else
		{
			job.setErrorCode("200");
			job.setErrorMessage("Thankyou!! Job has been update Successfully,.,!!,.,!!,.,");
		}
		
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	
	
}
