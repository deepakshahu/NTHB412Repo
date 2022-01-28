package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Movie;
import com.nit.utility.HibernateUtil;

public class PersistObjectTest {

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
			movie.setMid(1001);
			movie.setMname("Pushpa: The Rise");
			movie.setHeroName("Allu Arjun");
			movie.setBudget(700.0f);
			//Save object
			ses.persist(movie);
			tx.commit();
			System.out.println("Object is saved");
		}//try
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved");
			}
			he.printStackTrace();
		}
	}//main
}//class