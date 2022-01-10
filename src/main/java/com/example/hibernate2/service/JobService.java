package com.example.hibernate2.service;



import java.util.List;
import com.example.hibernate2.model.Job;



public interface JobService {
	
	
	 void insertJob(Job job);
	 void updateJob(Job job,long id);
	 void deleteJob(long id);
	 Job getJob(long id);
     List < Job > getJobs();
}
