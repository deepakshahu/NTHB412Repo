package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nit.entity.Product;

public class PersistObjectTest {

	public static void main(String[] args) {
		
		//BootStrap/Activate the Hibernate
		Configuration cfg = new Configuration();
		
		//Specify the Hibernate configuration file name and location
		cfg.configure("com/nit/cfgs/hibernate.cfg.xml");
		
		//Build SessionFactory having all services specified in cfg file and mapping file
		SessionFactory factory = cfg.buildSessionFactory();
		
		//Create Session object
		Session ses = factory.openSession();
		
		//Create Transaction object
		Transaction tx = null;
		try {
			//Begin Transaction
			tx = ses.beginTransaction();  //internally calls con.setAutoCommit(false) to disable autocommit mode on DB s/w
			
			//Prepare entity object
			Product prod = new Product();
			//prod.setPid(345);
			prod.setPname("Dinning Table");
			prod.setPrice(20000.24f);
			prod.setQty(1.0f);
			
			//Save object
			ses.persist(prod);  //Gives persistence instruction to hibernate to save object (insert object data as the record)
			
			tx.commit();  //internally calls con.commit() method to make inserting executing result permanent
			System.out.println("Object is saved Record is inserted");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			tx.rollback();  //internally calls con.rollback() method to rollback the results of query execution'
			System.out.println("Object is not saved Record is not inserted");
		}
		
		//Close session object
		ses.close();
		//Close session factory object
		factory.close();
		
	}//main
}//class