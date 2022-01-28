package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Movie;
import com.nit.utility.HibernateUtil;

public class LoadAndDeleteObjectTest {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			//Load object
			Movie movie = ses.get(Movie.class, 1003);
			if(movie==null) {
				System.out.println("Record not found to delete");
				return;
			}
			else {
				//Begin Transaction
				tx = ses.beginTransaction();
				//Instruction to delete object
				ses.delete(movie);
				tx.commit();
				System.out.println("Object is deleted");
			}//else
		}//try
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not deleted");
			}
			he.printStackTrace();
		}//catch
	}//main
}//class