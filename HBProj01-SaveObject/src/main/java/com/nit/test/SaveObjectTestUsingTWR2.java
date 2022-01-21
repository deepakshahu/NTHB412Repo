package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nit.entity.Product;

public class SaveObjectTestUsingTWR2 {

	public static void main(String[] args) {

		//BootStrap / Activate the Hibernate
		Configuration cfg = new Configuration();

		//Specify the Hibernate configuration file name and location
		cfg.configure("com/nit/cfgs/hibernate.cfg.xml");

		//Create Transaction object
		Transaction tx = null;
		//Build SessionFactory having all services specified in cfg file and mapping file
		SessionFactory factory = cfg.buildSessionFactory();
		//Create Session object
		Session ses = factory.openSession();
		try(factory;ses){  //from Java9
			//Begin Transaction
			tx = ses.beginTransaction();  //internally calls con.setAutoCommit(false) to disable autocommit mode on DB s/w

			//Prepare entity object
			Product prod = new Product();
			prod.setPname("Chairs");
			prod.setPrice(300.57f);
			prod.setQty(5.0f);

			//Save object
			Integer idVal = (Integer) ses.save(prod);  //save obj
			System.out.println("The generated id value is :: "+idVal);
			tx.commit();
			System.out.println("Object is saved, record is inserted");
		}//The SessionFactory, Session object will be closed here automatically
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getRollbackOnly() && tx.getStatus()!=null) {
				tx.rollback();
				System.out.println("Object is not saved, record is not inserted");
			}
		}
	}//main
}//class