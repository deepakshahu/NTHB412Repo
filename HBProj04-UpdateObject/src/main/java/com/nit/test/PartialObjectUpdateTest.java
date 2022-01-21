package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nit.entity.Product;

public class PartialObjectUpdateTest {

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

			//load object
			Product prod = ses.get(Product.class, 1001);
			if(prod==null) {
				System.out.println("Record not found");
				return;
			}
			else {
				//Begin Tx
				tx = ses.beginTransaction();

				//modify the object partially
				prod.setPrice(8547.78f);  //new value
				prod.setQty(1.0f);  //new value

				//instruction to update object
				ses.update(prod);

				//commit the tx
				tx.commit();
				System.out.println("Partial object is updated");
			}//else
		}//try
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not updated");
			}
			he.printStackTrace();
		}//catch
	}//main
}//class