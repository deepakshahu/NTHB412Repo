package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nit.entity.Product;

public class SaveOrUpdateObjectTest {

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
			//Begin Tx
			tx = ses.beginTransaction();

			//Prepare object for entity class
			Product prod = new Product();
			prod.setPid(1013);
			prod.setPname("Side Table");
			prod.setPrice(700.5f);
			prod.setQty(2.0f);
			//instruction to saveOrUpdate object
			ses.saveOrUpdate(prod);

			//commit the tx
			tx.commit();
			System.out.println("Object is saved or updated");
		}
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved or updated");
			}
			he.printStackTrace();
		}
	}//main
}//class