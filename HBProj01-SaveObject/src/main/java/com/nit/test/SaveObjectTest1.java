package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nit.entity.Product;

public class SaveObjectTest1 {

	public static void main(String[] args) {

		//BootStrap / Activate the Hibernate
		Configuration cfg = new Configuration();

		//Specify the Hibernate configuration file name and location
		cfg.configure("com/nit/cfgs/hibernate.cfg.xml");

		//Build SessionFactory having all services specified in cfg file and mapping file
		SessionFactory factory = cfg.buildSessionFactory();

		//Create Session object
		Session ses = factory.openSession();

		//Create Transaction object
		Transaction tx = null;
		boolean flag = false;
		try {
			//Begin Transaction
			tx = ses.beginTransaction();  //internally calls con.setAutoCommit(false) to disable autocommit mode on DB s/w

			//Prepare entity object
			Product prod = new Product();
			prod.setPname("Bed");
			prod.setPrice(50000.74f);
			prod.setQty(2.0f);

			//Save object
			Integer idVal = (Integer) ses.save(prod);  //Gives persistence instruction to hibernate to save object (insert object data as the record)
			System.out.println("The generated id value is :: "+idVal);
			flag = true;
		}
		catch(HibernateException he) {
			he.printStackTrace();
			flag = false;
		}
		finally {
			if(flag) {
				tx.commit();  //internally calls con.commit() method to make inserting executing result permanent
				System.out.println("Object is saved, record is inserted");				
			}
			else {
				tx.rollback();  //internally calls con.rollback() method to rollback the results of query execution'
				System.out.println("Object is not saved, record is not inserted");
			}
			//Close session objects
			try {
				if(ses!=null)
					ses.close();				
			}
			catch(HibernateException he) {
				he.printStackTrace();
			}
			try {
				if(factory!=null)
					factory.close();				
			}
			catch(HibernateException he) {
				he.printStackTrace();
			}
		}//finally
	}//main
}//class