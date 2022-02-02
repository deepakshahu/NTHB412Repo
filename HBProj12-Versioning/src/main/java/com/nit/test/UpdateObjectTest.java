package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.CallerTune;
import com.nit.utility.HibernateUtil;

public class UpdateObjectTest {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			//Prepare object
			CallerTune tune = ses.get(CallerTune.class, 1);
			if(tune==null) {
				System.out.println("Record not found");
				return;
			}
			else {
				//begin transaction
				tx = ses.beginTransaction();
				//Update object
				tune.setTuneName("oo antava ..");
				ses.update(tune);
				tx.commit();
				System.out.println("Object is updated for :: "+tune.getUpdationCount()+" times");
			}//else
		}//try
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()!=null  || tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not updated");
			}//if
			he.printStackTrace();
		}//catch
	}//main
}//class