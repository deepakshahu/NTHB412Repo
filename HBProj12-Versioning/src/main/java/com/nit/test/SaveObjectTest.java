package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.CallerTune;
import com.nit.utility.HibernateUtil;

public class SaveObjectTest {

	public static void main(String[] args) {
		
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			//begin transaction
			tx = ses.beginTransaction();
			//Prepare object
			CallerTune tune = new CallerTune();
			tune.setTuneName("Srivalli");
			tune.setMovieName("Pushpa:The Rise");
			//Save object
			ses.save(tune);
			tx.commit();
			System.out.println("Object is saved");
		}
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()!=null  || tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved");
			}//if
			he.printStackTrace();
		}//catch
	}//main
}//class