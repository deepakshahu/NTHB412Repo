package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class SaveObjectTest {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		System.out.println("Session factory object is ready");
		//Get Session object
		Session ses = HibernateUtil.getSession();
		//Create Transaction object
		Transaction tx = null;
		try{
			//Begin Transaction
			tx = ses.beginTransaction();  //internally calls con.setAutoCommit(false) to disable autocommit mode on DB s/w
			//Prepare entity object
			Product prod = new Product();
			prod.setPname("Desk");
			prod.setPrice(7400.24f);
			prod.setQty(1.0f);
			System.out.println("Application is in sleeping state");
			Thread.sleep(30000);
			
			//Save object
			Integer idVal = (Integer) ses.save(prod);  //Gives persistence instruction to hibernate to save object (insert object data as the record)
			System.out.println("The generated id value is :: "+idVal);

			tx.commit();  //internally calls con.commit() method to make inserting executing result permanent
			System.out.println("Object is saved, record is inserted");
			System.out.println("Application is again in sleeping state");
			Thread.sleep(30000);
		}
		catch(HibernateException he) {
			he.printStackTrace();
			tx.rollback();  //internally calls con.rollback() method to rollback the results of query execution'
			System.out.println("Object is not saved, record is not inserted");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//close session object
		ses.close();
		//close session factory object
		factory.close();
		System.out.println("Session factory object is closed");
	}//main
}//class