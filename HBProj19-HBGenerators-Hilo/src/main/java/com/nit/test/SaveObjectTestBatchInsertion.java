package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class SaveObjectTestBatchInsertion {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();

		//Get Session Object
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try{
			for(int i=1; i<=10; i++) {
				//Begin Tx
				tx = ses.beginTransaction();

				//Prepare object for entity class
				Product prod = new Product();
				prod.setPname("Spoon");
				prod.setPrice(800.5f);
				prod.setQty(42.0f);
				//instruction to save object
				Integer idVal = (Integer) ses.save(prod);
				System.out.println("The generated id value is :: "+idVal);

				//commit the tx
				tx.commit();
				System.out.println("Object is saved");
			}
		}
		catch(HibernateException he) {
			he.printStackTrace();
			tx.rollback();
			System.out.println("Object is not saved");
		}
		finally {
			ses.close();
			factory.close();
		}//finally
	}//main
}//class