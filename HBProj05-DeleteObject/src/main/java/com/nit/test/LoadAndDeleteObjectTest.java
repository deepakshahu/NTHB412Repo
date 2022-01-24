package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nit.entity.Product;

public class LoadAndDeleteObjectTest {

	public static void main(String[] args) {

		//BootStrap / Activate the Hibernate
		Configuration cfg = new Configuration();

		//Specify the hibernate configuration file name and location
		cfg.configure("com/nit/cfgs/hibernate.cfg.xml");

		//Build SessionFactory object
		SessionFactory factory = cfg.buildSessionFactory();

		//Create Session object
		Session ses = factory.openSession();

		Transaction tx = null;
		try(factory;ses){  //java9
			//Load object
			Product prod = ses.get(Product.class, 1012);
			if(prod==null) {
				System.out.println("Record not found to delete");
				return;
			}
			else {
				//Begin Tx
				tx = ses.beginTransaction();

				//instruction to delete object
				ses.delete(prod);

				//commit the tx
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
		}
	}//main
}//class