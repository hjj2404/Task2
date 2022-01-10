package com.example.hibernate2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernate2.model.Job;
import com.example.hibernate2.service.JobService;


@RestController
@RequestMapping("/api/jobs")
public class JobController {

	private JobService jobService;

	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}
	
	/*
	@PostMapping()
	public ResponseEntity<Job> saveJob(@RequestBody Job job){
		return new ResponseEntity<Job>(jobService.insertJob(job),HttpStatus.CREATED);
		
	}*/
	
	@PostMapping()
	public ResponseEntity<String> saveJob(@RequestBody Job job){
		jobService.insertJob(job);
		return new ResponseEntity<String>("Records inserted",HttpStatus.CREATED);
		
	}
	
	// getting records
		@GetMapping()
		public List<Job> getAllRecords(){
			return jobService.getJobs();
		}
		
		//get by id
		@GetMapping("{id}")
		public ResponseEntity<Job> getJobById(@PathVariable("id") long jobid){
			return new ResponseEntity<Job>(jobService.getJob(jobid), HttpStatus.OK);
		}
		
		//update records
		@PutMapping("{id}")
		public ResponseEntity<String> updateJob(@PathVariable("id") long id1,@RequestBody Job job){
			jobService.updateJob(job,id1);
			return new ResponseEntity<String>("Records updated!!",HttpStatus.OK);
		}
		
		//delete records
		@DeleteMapping("{id}")
		public ResponseEntity<String> deleteJob(@PathVariable("id") long id) {
			jobService.deleteJob(id);
			return new ResponseEntity<String>("Record deleted successfully!", HttpStatus.OK);
		}
	
}
