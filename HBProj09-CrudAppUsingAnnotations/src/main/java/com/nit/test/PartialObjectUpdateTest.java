package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Movie;
import com.nit.utility.HibernateUtil;

public class PartialObjectUpdateTest {

	public static void main(String[] args) {
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			Movie movie = ses.get(Movie.class, 1001);
			if(movie==null) {
				System.out.println("Record not found");
				return;
			}
			else {
				//Begin Transaction
				tx = ses.beginTransaction();
				//modify the object partially
				movie.setBudget(4000.0f);  //new value
				//instruction to update object
				ses.update(movie);
				//commit the tx
				tx.commit();
				System.out.println("Partial object is updated");
			}//else
		}//try
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Partial Object is not updated");
			}
			he.printStackTrace();
		}//catch
	}//main
}//class