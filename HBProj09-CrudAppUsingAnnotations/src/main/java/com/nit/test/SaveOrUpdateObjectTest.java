package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Movie;
import com.nit.utility.HibernateUtil;

public class SaveOrUpdateObjectTest {

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
			movie.setMid(1003);
			movie.setMname("Antim: The Final Truth");
			movie.setHeroName("Salman Khan");
			movie.setBudget(700.0f);
			//Instruction to saveOrUpdate object
			ses.saveOrUpdate(movie);
			tx.commit();
			System.out.println("Object is saved or updated");
		}//try
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved or updated");
			}
			he.printStackTrace();
		}//catch
	}//main
}//class