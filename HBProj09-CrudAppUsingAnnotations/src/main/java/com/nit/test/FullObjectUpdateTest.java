package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Movie;
import com.nit.utility.HibernateUtil;

public class FullObjectUpdateTest {

	public static void main(String[] args) {
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			//Begin transaction
			tx = ses.beginTransaction();
			//Prepare entity object
			Movie movie = new Movie();
			movie.setMid(1001);  //must be existing id
			movie.setMname("Pushpa: The Rise");  //new value
			movie.setHeroName("Allu Arjun");  //new value
			movie.setBudget(800.0f);  //new value
			//Instruction to update object
			ses.update(movie);
			tx.commit();
			System.out.println("Full object is updated");
		}//try
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Full object is not updated");
			}
			he.printStackTrace();
		}
	}//main
}//class