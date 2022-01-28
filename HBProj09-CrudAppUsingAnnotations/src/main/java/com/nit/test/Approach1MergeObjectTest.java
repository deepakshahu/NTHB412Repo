package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Movie;
import com.nit.utility.HibernateUtil;

public class Approach1MergeObjectTest {

	public static void main(String[] args) {
		//get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){  //java9
			//Begin Transaction
			tx = ses.beginTransaction();
			//Prepare entity object
			Movie movie = new Movie();
			movie.setMid(1003);
			movie.setMname("Antim: The Final Truth");
			movie.setHeroName("Salman Khan");
			movie.setBudget(500.0f);
			//Instruction to merge object
			Movie movie1 = (Movie) ses.merge(movie);
			tx.commit();
			System.out.println("Given object data : "+movie+" hashCode : "+movie.hashCode());
			System.out.println("Recieved object data : "+movie1+" hashCode : "+movie1.hashCode());
			System.out.println("Object is saved or updated");
		}
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()!=null || tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved or not updated");
			}
			he.printStackTrace();
		}
	}//main
}//class