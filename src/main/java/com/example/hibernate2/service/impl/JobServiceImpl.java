package com.example.hibernate2.service.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.example.hibernate2.model.Job;
import com.example.hibernate2.service.JobService;
import com.example.hibernate2.util.HibernateUtil;

@Service
public class JobServiceImpl implements JobService {

	 public void insertJob(Job job) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();

	    /*        String hql = "INSERT INTO Job (description, status, title) " +
	                "SELECT description, status, title FROM Job";
	            
	            
	            Query query = session.createQuery(hql);
	           
	            int result = query.executeUpdate();
	            System.out.println("Rows affected: " + result);

	            // commit transaction
	            transaction.commit();
	            
	            */
	        session.save(job);
	        } catch (Exception e) {
	           
	            e.printStackTrace();
	        }
	    }

	    public void updateJob(Job job,long id) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();

	            // save the Job object
	            String hql = "UPDATE Job set description = :description,status= :status,title= :title " + "WHERE id = :id";
	            Query query = session.createQuery(hql);
	            query.setParameter("description", job.getDescription());
	            query.setParameter("status", job.getStatus());
	            query.setParameter("title", job.getTitle());
	            query.setParameter("id", id);
	            int result = query.executeUpdate();
	            System.out.println("Rows affected: " + result);

	            // commit transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }
	    
	    public void deleteJob(long id) {

	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();

	          
	            Job job = session.get(Job.class, id);
	            if (job != null) {
	                String hql = "DELETE FROM Job " + "WHERE id = :Id";
	                Query query = session.createQuery(hql);
	                query.setParameter("Id", id);
	                int result = query.executeUpdate();
	                System.out.println("Rows affected: " + result);
	            }
	            
	         // commit transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }

	    public Job getJob(long id) {

	        Transaction transaction = null;
	        Job job = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();

	         // get an student object
	            String hql = " FROM Job J WHERE J.id = :jId";
	            Query query = session.createQuery(hql);
	            query.setParameter("jId", id);
	            List results = query.getResultList();

	            if (results != null && !results.isEmpty()) {
	                job = (Job) results.get(0);
	            }
	            // commit transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        return job;
	    }

	    public List < Job > getJobs() {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            return session.createQuery("from Job", Job.class).list();
	        }
	    }
}
